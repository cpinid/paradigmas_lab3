package dev.cpini.paradigmas_lab3.ui;

import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.Utils;

import javax.swing.*;
import java.awt.*;

/**
 * Panel de vista de menú principal.
 * Incluye un {@link UserPanel}.
 */
public class MainMenuPanel extends JPanel {
    public MainMenuPanel(UI ui) {
        super();
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        int y = 0;

        //Título
        JLabel titleLabel = Utils.createTitle("Creador de sistemas de chatbots");
        add(titleLabel,
                GridPlacing.create(0, y++).withFill(GridPlacing.Fill.HORIZONTAL).withAnchor(GridPlacing.Anchor.CENTER)
                        .withWeight(1, 1));

        //Panel de usuario (inicio/registro)
        JPanel userPanel = new UserPanel(ui);
        add(userPanel, GridPlacing.create(0, y).withAnchor(GridPlacing.Anchor.PAGE_START).withWeight(1, 0.75));
    }
}
