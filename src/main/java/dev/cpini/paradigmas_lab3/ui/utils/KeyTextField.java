package dev.cpini.paradigmas_lab3.ui.utils;

import javax.swing.*;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.function.Consumer;

/**
 * Extensión de {@link JTextField} con método para añadir fácilmente una
 * escucha a presionar la tecla enter.
 */
public class KeyTextField extends JTextField {
    public KeyListener addKeyListener(Consumer<KeyEvent> consumer) {
        KeyListener listener = new KeyAdapter() {
            @Override
            public void keyPressed(KeyEvent event) {
                if (event.getKeyCode() == KeyEvent.VK_ENTER) consumer.accept(event);
            }
        };
        addKeyListener(listener);
        return listener;
    }
}
