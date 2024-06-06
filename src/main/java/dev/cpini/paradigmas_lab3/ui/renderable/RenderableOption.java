package dev.cpini.paradigmas_lab3.ui.renderable;

import dev.cpini.paradigmas_lab3.Option;
import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;

public class RenderableOption extends Renderable<Option> {
    public RenderableOption(Option object) {
        super(object);
    }

    @Override
    public int getId() {
        return getObject().getCode();
    }

    @Override
    public String getName() {
        return getObject().getMessage();
    }

    @Override
    public void showAttributes(UIPanel panel) {
        int y = 0;
        Option option = getObject();
        GridPlacing labelPlacing = labelPlacing();
        GridPlacing fieldPlacing = fieldPlacing();

        //Nombre/mensaje
        panel.add(new JLabel("Nombre/mensaje"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(option.getMessage()), fieldPlacing.withPos(0, y++));

        //Chatbot
        panel.add(new JLabel("Chatbot"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(String.valueOf(option.getChatbotCodeLink())), fieldPlacing.withPos(0, y++));

        //Flujo inicial
        panel.add(new JLabel("Flujo inicial"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(String.valueOf(option.getInitialFlowCodeLink())), fieldPlacing.withPos(0, y++));

        //Palabras clave
        panel.add(new JLabel("Palabras clave"), labelPlacing.withPos(0, y++));
        panel.add(Utils.displayArea(String.join("\n", option.getKeywords())),
                fieldPlacing.withPos(0, y).withAnchor(GridPlacing.Anchor.PAGE_START).withWeight(1, 1));
    }
}
