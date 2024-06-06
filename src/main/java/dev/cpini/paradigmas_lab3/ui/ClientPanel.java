package dev.cpini.paradigmas_lab3.ui;

import dev.cpini.paradigmas_lab3.Admin;
import dev.cpini.paradigmas_lab3.System;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.KeyTextField;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import javax.swing.text.DefaultCaret;
import java.awt.*;
import java.awt.event.AdjustmentEvent;
import java.awt.event.AdjustmentListener;

/**
 * Panel de vista de cliente.
 * Muestra el historial de conversación del usuario y permite enviar mensajes.
 * Permite simular diálogo, cambiar a {@link AdminPanel} (solo si el usuario es administrador) y cerrar sesión.
 */
public class ClientPanel extends UIPanel {
    private final JTextArea historyArea;
    private final KeyTextField messageField;

    public ClientPanel(UI ui) {
        super(ui);
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int y = 0;
        System system = ui.getCurrentSystem();

        //Título
        JLabel titleLabel = Utils.createSubitle(system.getName());
        add(titleLabel, GridPlacing.create(0, y++, 2, 1).withInsets(0, 0, 10, 0));

        //Chat
        historyArea = new JTextArea();
        if (system.getChatHistories().containsKey(system.getLoggedUser()))
            historyArea.setText(system.getChatHistories().get(system.getLoggedUser()).toString());
        else historyArea.setText("Envíe un mensaje para iniciar la conversación");
        historyArea.setEditable(false);
        ((DefaultCaret) historyArea.getCaret()).setUpdatePolicy(DefaultCaret.ALWAYS_UPDATE);
        JScrollPane historyScroll = new JScrollPane(historyArea);
        historyScroll.getVerticalScrollBar().addAdjustmentListener(new AdjustmentListener() {
            int lastMax;

            @Override
            public void adjustmentValueChanged(AdjustmentEvent event) {
                if (event.getAdjustable().getMaximum() != lastMax) {
                    event.getAdjustable().setValue(event.getAdjustable().getMaximum());
                    lastMax = event.getAdjustable().getMaximum();
                }
            }
        });
        add(historyScroll,
                GridPlacing.create(0, y++, 2, 1).withFill(GridPlacing.Fill.BOTH).withAnchor(GridPlacing.Anchor.CENTER)
                        .withWeight(1, 1));

        //Mensaje
        messageField = new KeyTextField();
        messageField.addKeyListener(event -> sendMessage());
        add(messageField, GridPlacing.create(0, y).withFill(GridPlacing.Fill.HORIZONTAL).withWeight(1, 0));

        //Enviar mensaje
        JButton sendButton = new JButton("Enviar");
        sendButton.addActionListener(event -> sendMessage());
        add(sendButton, GridPlacing.create(1, y++).withFill(GridPlacing.Fill.HORIZONTAL));

        //Botones
        JPanel buttonPanel = new JPanel(new FlowLayout());

        //Simular diálogo
        JButton simulateButton = new JButton("Simular diálogo");
        simulateButton.addActionListener(event -> new SimulateDialog().setVisible(true));
        buttonPanel.add(simulateButton);

        //Editar sistema (solo admin)
        if (system.getLoggedUser() instanceof Admin) {
            JButton editButton = new JButton("Editar sistema");
            editButton.addActionListener(event -> ui.showAdmin());
            buttonPanel.add(editButton);
        }

        //Cerrar sesión
        JButton logoutButton = new JButton("Cerrar sesión");
        logoutButton.addActionListener(event -> {
            system.systemLogout();
            ui.showMainMenu();
        });
        buttonPanel.add(logoutButton);

        add(buttonPanel, GridPlacing.create(0, y, 2, 1).withInsets(5, 0, 0, 0).withAnchor(GridPlacing.Anchor.CENTER));
    }

    private void updateHistory() {
        System system = getUi().getCurrentSystem();
        historyArea.setText(system.getChatHistories().get(system.getLoggedUser()).toString());
    }

    private void sendMessage() {
        try {
            System system = getUi().getCurrentSystem();
            system.systemTalk(messageField.getText());
            messageField.setText(null);
            updateHistory();
        } catch (IllegalArgumentException e) {
            Utils.showErrorDialog(this, e.getMessage());
        } catch (Exception e) {
            Utils.showErrorDialog(this, "El sistema está mal configurado. Error: " + e);
        }
    }

    private class SimulateDialog extends JDialog {
        public SimulateDialog() {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 200);
            setLocationRelativeTo(getOwner());
            setModal(true);

            //Panel
            JPanel panel = new JPanel(new GridBagLayout());
            panel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
            add(panel);

            int y = 0;
            GridPlacing placing = GridPlacing.create(0, 0, 2, 1).withFill(GridPlacing.Fill.HORIZONTAL)
                    .withAnchor(GridPlacing.Anchor.LINE_START).withWeight(1, 0);

            //Título
            JLabel titleLabel = Utils.createSubitle("Simular diálogo");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 10, 0));

            //Interacciones máximas
            panel.add(new JLabel("Interacciones máximas"), placing.withPos(0, y++));

            //Campo interacciones máximas
            JTextField maxIntField = new JTextField();
            panel.add(maxIntField, placing.withPos(0, y++));

            //Semilla
            panel.add(new JLabel("Semilla"), placing.withPos(0, y++));

            //Campo semilla
            JTextField seedField = new JTextField();
            panel.add(seedField, placing.withPos(0, y++));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Aceptar");
            acceptButton.addActionListener(event -> {
                if (Utils.checkEmptyTexts(this, maxIntField, seedField)) return;
                if (Utils.checkNumberTexts(this, maxIntField, seedField)) return;

                getUi().getCurrentSystem()
                        .systemSimulate(Integer.parseInt(maxIntField.getText()), Integer.parseInt(seedField.getText()));
                updateHistory();
                dispose();
            });
            buttonPanel.add(acceptButton);

            //Cancelar
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(event -> dispose());
            buttonPanel.add(cancelButton);

            panel.add(buttonPanel, placing.withPos(0, y));
        }
    }
}
