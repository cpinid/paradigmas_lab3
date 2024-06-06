package dev.cpini.paradigmas_lab3.ui.utils;

import javax.swing.*;
import javax.swing.text.JTextComponent;
import java.awt.*;

/**
 * Utilidades para la creación de interfaces de usuario.
 */
public class Utils {
    public static JLabel createLabelWithSize(String text, float size) {
        JLabel label = new JLabel(text);
        label.setFont(label.getFont().deriveFont(size));
        return label;
    }

    public static JLabel createTitle(String text) {
        JLabel label = createLabelWithSize(text, 32);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static JLabel createSubitle(String text) {
        JLabel label = createLabelWithSize(text, 24);
        label.setHorizontalAlignment(SwingConstants.CENTER);
        return label;
    }

    public static JTextArea displayArea(String text) {
        JTextArea field = new JTextArea(text);
        field.setEditable(false);
        field.setBorder(BorderFactory.createEtchedBorder());
        return field;
    }

    public static boolean checkEmptyTexts(Component parent, JTextComponent... components) {
        for (JTextComponent component : components)
            if (component.getText() == null || component.getText().isBlank()) {
                JOptionPane.showMessageDialog(parent, "Los campos no pueden estar vacíos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
        return false;
    }

    public static boolean checkNumberTexts(Component parent, JTextField... fields) {
        for (JTextField field : fields)
            if (field.getText() != null) try {
                if (Integer.parseInt(field.getText()) < 0) throw new NumberFormatException();
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(parent,
                        "Los campos numéricos debe contener solo números enteros no negativos", "Error",
                        JOptionPane.ERROR_MESSAGE);
                return true;
            }
        return false;
    }

    public static void showErrorDialog(Component parent, String message) {
        JOptionPane.showMessageDialog(parent, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
}
