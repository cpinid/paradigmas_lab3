package dev.cpini.paradigmas_lab3.ui.utils;

import dev.cpini.paradigmas_lab3.ui.renderable.Renderable;

import javax.swing.tree.DefaultMutableTreeNode;

/**
 * Extensión de {@link DefaultMutableTreeNode} que incluye un {@link Renderable} como
 * objeto a representar en el nodo.
 */
public class SystemTreeNode extends DefaultMutableTreeNode {
    private final Renderable<?> object;

    public SystemTreeNode(Renderable<?> object) {
        this.object = object;

        if (object.getId() < 0) setUserObject(object.getName());
        else setUserObject(String.format("[%s] %s", object.getId(), object.getName()));
    }

    public void showAttributes(UIPanel panel) {
        object.showAttributes(panel);
    }
}
