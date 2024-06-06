package dev.cpini.paradigmas_lab3.ui.renderable;

import dev.cpini.paradigmas_lab3.Chatbot;
import dev.cpini.paradigmas_lab3.System;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class RenderableSystem extends Renderable<System> {
    public RenderableSystem(System object) {
        super(object);
    }

    @Override
    public int getId() {
        return -1;
    }

    @Override
    public String getName() {
        return getObject().getName();
    }

    @Override
    public void showAttributes(UIPanel panel) {
        int y = 0;
        System system = getObject();
        GridPlacing labelPlacing = labelPlacing();
        GridPlacing fieldPlacing = fieldPlacing();

        //Nombre
        panel.add(new JLabel("Nombre"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(system.getName()), fieldPlacing.withPos(0, y++));

        //Chatbot inicial
        panel.add(new JLabel("Chatbot inicial"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(String.valueOf(system.getInitialChatbotCodeLink())), fieldPlacing.withPos(0, y++));

        //Añadir chatbot
        JButton addChatbotButton = new JButton("Añadir chatbot");
        addChatbotButton.addActionListener(event -> new ChatbotCreationDialog(panel).setVisible(true));
        panel.add(addChatbotButton,
                labelPlacing.withPos(0, y).withFill(GridPlacing.Fill.NONE).withAnchor(GridPlacing.Anchor.PAGE_START)
                        .withWeight(1, 1));
    }

    private class ChatbotCreationDialog extends JDialog {
        public ChatbotCreationDialog(UIPanel parentPanel) {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 320);
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
            JLabel titleLabel = Utils.createSubitle("Crear un chatbot");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 10, 0));

            //Código
            panel.add(new JLabel("Código"), placing.withPos(0, y++));

            //Campo código
            JTextField codeField = new JTextField();
            panel.add(codeField, placing.withPos(0, y++));

            //Nombre
            panel.add(new JLabel("Nombre"), placing.withPos(0, y++));

            //Campo nombre
            JTextField nameField = new JTextField();
            panel.add(nameField, placing.withPos(0, y++));

            //Mensaje de bienvenida
            panel.add(new JLabel("Mensaje de bienvenida"), placing.withPos(0, y++));

            //Campo mensaje de bienvenida
            JTextArea welcomeMsjField = new JTextArea();
            panel.add(new JScrollPane(welcomeMsjField),
                    placing.withPos(0, y++).withFill(GridPlacing.Fill.BOTH).withWeight(1, 1));

            //Flujo inicial
            panel.add(new JLabel("Flujo inicial"), placing.withPos(0, y++));

            //Campo flujo inicial
            JTextField startFlowField = new JTextField();
            panel.add(startFlowField, placing.withPos(0, y++));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Crear");
            acceptButton.addActionListener(event -> {
                if (Utils.checkEmptyTexts(this, codeField, nameField, welcomeMsjField, startFlowField)) return;
                if (Utils.checkNumberTexts(this, codeField, startFlowField)) return;

                try {
                    Chatbot chatbot = new Chatbot(Integer.parseInt(codeField.getText()), nameField.getText(),
                            welcomeMsjField.getText(), Integer.parseInt(startFlowField.getText()));
                    getObject().systemAddChatbot(chatbot);
                    parentPanel.getUi().showAdmin();
                    dispose();
                } catch (IllegalArgumentException e) {
                    Utils.showErrorDialog(this, e.getMessage());
                }
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
