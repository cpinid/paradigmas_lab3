package dev.cpini.paradigmas_lab3.ui.renderable;

import dev.cpini.paradigmas_lab3.Chatbot;
import dev.cpini.paradigmas_lab3.Flow;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import java.awt.*;

public class RenderableChatbot extends Renderable<Chatbot> {
    public RenderableChatbot(Chatbot chatbot) {
        super(chatbot);
    }

    @Override
    public int getId() {
        return getObject().getId();
    }

    @Override
    public String getName() {
        return getObject().getName();
    }

    @Override
    public void showAttributes(UIPanel panel) {
        int y = 0;
        Chatbot chatbot = getObject();
        GridPlacing labelPlacing = labelPlacing();
        GridPlacing fieldPlacing = fieldPlacing();

        //Nombre
        panel.add(new JLabel("Nombre"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(chatbot.getName()), fieldPlacing.withPos(0, y++));

        //Mensaje de bienvenida
        panel.add(new JLabel("Mensaje de bienvenida"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(chatbot.getWelcomeMessage()), fieldPlacing.withPos(0, y++));

        //Flujo inicial
        panel.add(new JLabel("Flujo inicial"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(String.valueOf(chatbot.getStartFlowId())), fieldPlacing.withPos(0, y++));

        //Añadir flujo
        JButton addFlowButton = new JButton("Añadir flujo");
        addFlowButton.addActionListener(event -> new FlowCreationDialog(panel).setVisible(true));
        panel.add(addFlowButton,
                labelPlacing.withPos(0, y).withFill(GridPlacing.Fill.NONE).withAnchor(GridPlacing.Anchor.PAGE_START)
                        .withWeight(1, 1));
    }

    private class FlowCreationDialog extends JDialog {
        public FlowCreationDialog(UIPanel parentPanel) {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 250);
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
            JLabel titleLabel = Utils.createSubitle("Crear un flujo");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 10, 0));

            //ID
            panel.add(new JLabel("ID"), placing.withPos(0, y++));

            //Campo ID
            JTextField idField = new JTextField();
            panel.add(idField, placing.withPos(0, y++));

            //Nombre/mensaje
            panel.add(new JLabel("Nombre/mensaje"), placing.withPos(0, y++));

            //Campo nombre/mensaje
            JTextArea nameMsjField = new JTextArea();
            panel.add(new JScrollPane(nameMsjField),
                    placing.withPos(0, y++).withFill(GridPlacing.Fill.BOTH).withWeight(1, 1));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Crear");
            acceptButton.addActionListener(event -> {
                if (Utils.checkEmptyTexts(this, idField, nameMsjField)) return;
                if (Utils.checkNumberTexts(this, idField)) return;

                try {
                    Flow flow = new Flow(Integer.parseInt(idField.getText()), nameMsjField.getText());
                    getObject().chatbotAddFlow(flow);
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
