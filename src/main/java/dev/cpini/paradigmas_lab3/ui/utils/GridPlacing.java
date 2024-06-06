package dev.cpini.paradigmas_lab3.ui.utils;

import javax.swing.*;
import java.awt.*;

/**
 * Utilidades para la creación de paneles que usan {@link GridBagLayout}.
 * Extensión de {@link GridBagConstraints} con métodos que facilitan su uso.
 * Los objetos se deben tratar como inmutables.
 * Algunos elementos fueron eliminados, ya que no se utilizaron.
 */
public class GridPlacing extends GridBagConstraints {
    public static GridPlacing create() {
        return new GridPlacing();
    }

    public static GridPlacing create(int x, int y) {
        return create().withPos(x, y);
    }

    public static GridPlacing create(int x, int y, int width, int height) {
        return create(x, y).withSize(width, height);
    }

    public GridPlacing withPos(int x, int y) {
        GridPlacing placing = clone();
        placing.gridx = x;
        placing.gridy = y;
        return placing;
    }

    public GridPlacing withSize(int width, int height) {
        GridPlacing placing = clone();
        placing.gridwidth = width;
        placing.gridheight = height;
        return placing;
    }

    public GridPlacing withFill(int fill) {
        GridPlacing placing = clone();
        placing.fill = fill;
        return placing;
    }

    public GridPlacing withInsets(int top, int left, int bottom, int right) {
        GridPlacing placing = clone();
        placing.insets = new Insets(top, left, bottom, right);
        return placing;
    }

    public GridPlacing withAnchor(int anchor) {
        GridPlacing placing = clone();
        placing.anchor = anchor;
        return placing;
    }

    public GridPlacing withWeight(double weightX, double weightY) {
        GridPlacing placing = clone();
        placing.weightx = weightX;
        placing.weighty = weightY;
        return placing;
    }

    @Override
    public GridPlacing clone() {
        GridPlacing clone = (GridPlacing) super.clone();
        clone.insets = (Insets) insets.clone();
        return clone;
    }

    public static class Fill {
        public static final int NONE = GridPlacing.NONE;
        public static final int HORIZONTAL = GridPlacing.HORIZONTAL;
        public static final int BOTH = GridPlacing.BOTH;
    }

    public static class Anchor {
        public static final int PAGE_START = GridPlacing.PAGE_START;
        public static final int LINE_START = GridPlacing.LINE_START;
        public static final int CENTER = GridPlacing.CENTER;
    }

    public static class Orientation {
        public static final int HORIZONTAL = JSeparator.HORIZONTAL;
    }
}
