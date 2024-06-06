package dev.cpini.paradigmas_lab3.ui.utils;

import dev.cpini.paradigmas_lab3.ui.UI;

import javax.swing.*;
import java.awt.*;

/**
 * Extensión de {@link JPanel} que incluye una referencia a un {@link UI}.
 */
public class UIPanel extends JPanel {
    private final UI ui;

    public UIPanel(UI ui) {
        super();
        this.ui = ui;
    }

    public UIPanel(UI ui, LayoutManager layout) {
        super(layout);
        this.ui = ui;
    }

    public UI getUi() {
        return ui;
    }
}
