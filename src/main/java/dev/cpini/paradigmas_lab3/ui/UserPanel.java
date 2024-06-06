package dev.cpini.paradigmas_lab3.ui;

import dev.cpini.paradigmas_lab3.Admin;
import dev.cpini.paradigmas_lab3.Client;
import dev.cpini.paradigmas_lab3.System;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.KeyTextField;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de vista de usuario.
 * Permite crear un usuario e iniciar sesión.
 * Permite elegir un sistema y crear nuevos.
 * Deriva a {@link UserPanel} o {@link AdminPanel} según corresponda.
 */
public class UserPanel extends UIPanel {
    public UserPanel(UI ui) {
        super(ui);
        setLayout(new GridBagLayout());

        int y = 0;

        //Crear usuario
        JButton createButton = new JButton("Crear usuario");
        createButton.addActionListener(event -> new UserCreationDialog().setVisible(true));
        add(createButton, GridPlacing.create(0, y++).withFill(GridPlacing.Fill.HORIZONTAL).withInsets(0, 0, 10, 0));

        //Iniciar sesión
        JButton loginButton = new JButton("Iniciar sesión");
        loginButton.addActionListener(event -> new UserLoginDialog().setVisible(true));
        add(loginButton, GridPlacing.create(0, y++).withFill(GridPlacing.Fill.HORIZONTAL).withInsets(0, 0, 10, 0));

        //Sistemas
        JComboBox<String> systemCombo = new JComboBox<>(
                (ui.getSystems().stream().map(System::getName).toArray(String[]::new)));
        systemCombo.setSelectedItem(ui.getCurrentSystem().getName());
        systemCombo.addItemListener(event -> ui.setCurrentSystem(ui.getSystems().get(systemCombo.getSelectedIndex())));
        add(systemCombo, GridPlacing.create(0, y++).withFill(GridPlacing.Fill.HORIZONTAL).withInsets(0, 0, 10, 0));

        //Nuevo sistema
        JButton newSystemButton = new JButton("Nuevo sistema");
        newSystemButton.addActionListener(event -> new SystemCreationDialog().setVisible(true));
        add(newSystemButton, GridPlacing.create(0, y).withFill(GridPlacing.Fill.HORIZONTAL));
    }

    private class UserCreationDialog extends JDialog {
        private final JCheckBox adminCheckbox;
        private final KeyTextField usernameField;

        public UserCreationDialog() {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 240);
            setResizable(false);
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
            JLabel titleLabel = Utils.createSubitle("Crear un usuario");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 32, 0));

            //Nombre de usuario
            JLabel usernameLabel = new JLabel("Nombre de usuario");
            panel.add(usernameLabel, placing.withPos(0, y++));

            //Campo nombre de usuario
            usernameField = new KeyTextField();
            panel.add(usernameField, placing.withPos(0, y++));

            //Propiedades
            JLabel propertiesLabel = new JLabel("Propiedades");
            panel.add(propertiesLabel, placing.withPos(0, y++).withInsets(10, 0, 0, 0));

            //Administrador
            adminCheckbox = new JCheckBox("Administrador");
            addKeyListener(usernameField.addKeyListener(event -> createUser()));
            panel.add(adminCheckbox, placing.withPos(0, y++));

            //Separador
            panel.add(new JSeparator(GridPlacing.Orientation.HORIZONTAL),
                    placing.withPos(0, y++).withInsets(5, 0, 5, 0));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Crear");
            acceptButton.addActionListener(event -> createUser());
            buttonPanel.add(acceptButton);

            //Cancelar
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(event -> dispose());
            buttonPanel.add(cancelButton);

            panel.add(buttonPanel,
                    GridPlacing.create(0, y, 2, 1).withFill(GridPlacing.Fill.HORIZONTAL).withWeight(1, 0));
        }

        private void createUser() {
            if (Utils.checkEmptyTexts(this, usernameField)) return;

            try {
                if (adminCheckbox.isSelected()) getUi().getCurrentSystem().systemAddUser(new Admin(usernameField.getText()));
                else getUi().getCurrentSystem().systemAddUser(new Client(usernameField.getText()));
                dispose();
            } catch (IllegalArgumentException e) {
                Utils.showErrorDialog(this, e.getMessage());
            }
        }
    }

    private class UserLoginDialog extends JDialog {
        private final KeyTextField usernameField;

        public UserLoginDialog() {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 240);
            setResizable(false);
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
            JLabel titleLabel = Utils.createSubitle("Iniciar sesión");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 32, 0));

            //Nombre de usuario
            JLabel usernameLabel = new JLabel("Nombre de usuario");
            panel.add(usernameLabel, placing.withPos(0, y++));

            //Campo nombre de usuario
            usernameField = new KeyTextField();
            usernameField.addKeyListener(event -> login());
            panel.add(usernameField, placing.withPos(0, y++));

            //Separador
            panel.add(new JSeparator(GridPlacing.Orientation.HORIZONTAL),
                    placing.withPos(0, y++).withInsets(5, 0, 5, 0));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Iniciar sesión
            JButton acceptButton = new JButton("Iniciar sesión");
            acceptButton.addActionListener(event -> login());
            buttonPanel.add(acceptButton);

            //Cancelar
            JButton cancelButton = new JButton("Cancelar");
            cancelButton.addActionListener(event -> dispose());
            buttonPanel.add(cancelButton);

            panel.add(buttonPanel,
                    GridPlacing.create(0, y, 2, 1).withFill(GridPlacing.Fill.HORIZONTAL).withWeight(1, 0));
        }

        private void login() {
            if (Utils.checkEmptyTexts(this, usernameField)) return;

            try {
                getUi().getCurrentSystem().systemLogin(usernameField.getText());
                dispose();
                if (getUi().getCurrentSystem().getLoggedUser() instanceof Admin) getUi().showAdmin();
                else getUi().showClient();
            } catch (Exception e) {
                Utils.showErrorDialog(this, e.getMessage());
            }
        }
    }

    private class SystemCreationDialog extends JDialog {
        public SystemCreationDialog() {
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
            JLabel titleLabel = Utils.createSubitle("Crear un sistema");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 10, 0));

            //Nombre
            panel.add(new JLabel("Nombre"), placing.withPos(0, y++));

            //Campo nombre
            JTextField nameField = new JTextField();
            panel.add(nameField, placing.withPos(0, y++));

            //Chatbot inicial
            panel.add(new JLabel("Chatbot inicial"), placing.withPos(0, y++));

            //Campo chatbot inicial
            JTextField initialChatbotField = new JTextField();
            panel.add(initialChatbotField, placing.withPos(0, y++));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Crear");
            acceptButton.addActionListener(event -> {
                if (Utils.checkEmptyTexts(this, nameField, initialChatbotField)) return;
                if (Utils.checkNumberTexts(this, initialChatbotField)) return;

                if (getUi().getSystems().stream().anyMatch(system -> system.getName().equals(nameField.getText()))) {
                    Utils.showErrorDialog(this, "Ya existe un sistema con ese nombre");
                    return;
                }

                System system = new System(nameField.getText(), Integer.parseInt(initialChatbotField.getText()));
                getUi().addSystem(system);
                getUi().setCurrentSystem(system);
                getUi().showMainMenu();
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
