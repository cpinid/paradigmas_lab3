package dev.cpini.paradigmas_lab3.ui.renderable;

import dev.cpini.paradigmas_lab3.Flow;
import dev.cpini.paradigmas_lab3.Option;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import java.awt.*;
import java.util.List;

public class RenderableFlow extends Renderable<Flow> {
    public RenderableFlow(Flow flow) {
        super(flow);
    }

    @Override
    public int getId() {
        return getObject().getId();
    }

    @Override
    public String getName() {
        return getObject().getNameMsg().split("\n")[0];
    }

    @Override
    public void showAttributes(UIPanel panel) {
        int y = 0;
        Flow flow = getObject();
        GridPlacing labelPlacing = labelPlacing();
        GridPlacing fieldPlacing = fieldPlacing();

        //Nombre/mensaje
        panel.add(new JLabel("Nombre/mensaje"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(flow.getNameMsg()), fieldPlacing.withPos(0, y++));

        //Añadir opción
        JButton addOptionButton = new JButton("Añadir opción");
        addOptionButton.addActionListener(event -> new OptionCreationDialog(panel).setVisible(true));
        panel.add(addOptionButton,
                labelPlacing.withPos(0, y).withFill(GridPlacing.Fill.NONE).withAnchor(GridPlacing.Anchor.PAGE_START)
                        .withWeight(1, 1));
    }

    private class OptionCreationDialog extends JDialog {
        public OptionCreationDialog(UIPanel parentPanel) {
            super(JOptionPane.getRootFrame(), "");
            setSize(300, 360);
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
            JLabel titleLabel = Utils.createSubitle("Crear una opción");
            panel.add(titleLabel, placing.withPos(0, y++).withInsets(0, 0, 10, 0));

            //Código
            panel.add(new JLabel("Código"), placing.withPos(0, y++));

            //Campo código
            JTextField codeField = new JTextField();
            panel.add(codeField, placing.withPos(0, y++));

            //Mensaje
            panel.add(new JLabel("Mensaje"), placing.withPos(0, y++));

            //Campo mensaje
            JTextField messageField = new JTextField();
            panel.add(messageField, placing.withPos(0, y++));

            //Chatbot
            panel.add(new JLabel("Chatbot"), placing.withPos(0, y++));

            //Campo chatbot
            JTextField chatbotField = new JTextField();
            panel.add(chatbotField, placing.withPos(0, y++));

            //Flujo inicial
            panel.add(new JLabel("Flujo inicial"), placing.withPos(0, y++));

            //Campo flujo inicial
            JTextField initialFlowField = new JTextField();
            panel.add(initialFlowField, placing.withPos(0, y++));

            //Palabras clave
            panel.add(new JLabel("Palabras clave"), placing.withPos(0, y++));

            //Campo palabras clave
            JTextArea keywordsArea = new JTextArea();
            panel.add(new JScrollPane(keywordsArea),
                    placing.withPos(0, y++).withFill(GridPlacing.Fill.BOTH).withWeight(1, 1));

            //Botones
            JPanel buttonPanel = new JPanel(new FlowLayout());

            //Crear
            JButton acceptButton = new JButton("Crear");
            acceptButton.addActionListener(event -> {
                if (Utils.checkEmptyTexts(this, codeField, messageField, chatbotField, initialFlowField, keywordsArea))
                    return;
                if (Utils.checkNumberTexts(this, codeField, chatbotField, initialFlowField)) return;

                try {
                    Option option = new Option(Integer.parseInt(codeField.getText()), messageField.getText(),
                            Integer.parseInt(chatbotField.getText()), Integer.parseInt(initialFlowField.getText()),
                            List.of(keywordsArea.getText().split("\n")));
                    getObject().flowAddOption(option);
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
