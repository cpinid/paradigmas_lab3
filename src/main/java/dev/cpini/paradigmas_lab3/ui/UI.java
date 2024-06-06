package dev.cpini.paradigmas_lab3.ui;

import dev.cpini.paradigmas_lab3.System;

import javax.swing.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Clase de interfaz de usuario principal que maneja las ventanas.
 */
public class UI {
    private final List<System> systems;
    private final JFrame mainFrame;
    private System currentSystem;

    public UI(System system) {
        super();
        systems = new ArrayList<>();
        systems.add(system);
        mainFrame = new JFrame();
        mainFrame.setTitle("Sistema de Chatbots Paradígmicos");
        mainFrame.setDefaultCloseOperation(
                WindowConstants.EXIT_ON_CLOSE); //Si la interfaz es cerrada, el programa termina
        mainFrame.setSize(600, 600); //Tamaño hardcodeado, mis disculpas
        mainFrame.setResizable(false);
        currentSystem = system;

        showMainMenu();
    }

    public void init() {
        mainFrame.setLocationRelativeTo(null); //Centrar en la pantalla

        mainFrame.setVisible(true);
    }

    public void showMainMenu() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new MainMenuPanel(this));
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void showClient() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new ClientPanel(this));
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public void showAdmin() {
        mainFrame.getContentPane().removeAll();
        mainFrame.add(new AdminPanel(this));
        mainFrame.revalidate();
        mainFrame.repaint();
    }

    public System getCurrentSystem() {
        return currentSystem;
    }

    public void setCurrentSystem(System system) {
        currentSystem = system;
    }

    public List<System> getSystems() {
        return systems;
    }

    public void addSystem(System system) {
        systems.add(system);
    }
}
