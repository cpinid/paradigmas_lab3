package dev.cpini.paradigmas_lab3.ui.renderable;

import dev.cpini.paradigmas_lab3.ui.utils.GridPlacing;
import dev.cpini.paradigmas_lab3.ui.utils.UIPanel;

/**
 * Abstracción utilizable por el sistema de interfaz implementado.
 * Permite acercar una entidad a su versión visual.
 *
 * @param <T> Clase representada
 */
public abstract class Renderable<T> {
    private final T object;

    public Renderable(T object) {
        this.object = object;
    }

    /**
     * Obtiene el objeto representado por este renderable.
     *
     * @return objeto representado
     */
    protected T getObject() {
        return object;
    }

    /**
     * Obtiene la id del objeto representado por este renderable.
     *
     * @return id del objeto
     */
    public abstract int getId();

    /**
     * Obtiene el nombre del objeto representado por este renderable.
     *
     * @return nombre del objeto
     */
    public abstract String getName();

    /**
     * Muestra los atributos del objeto representado por este renderable en un panel.
     *
     * @param panel panel a utilizar
     */
    public abstract void showAttributes(UIPanel panel);

    /**
     * Utilidad para colocación de etiquetas.
     *
     * @return colocación
     */
    protected static GridPlacing labelPlacing() {
        return GridPlacing.create().withFill(GridPlacing.Fill.HORIZONTAL).withWeight(1, 0);
    }


    /**
     * Utilidad para colocación de campos.
     *
     * @return colocación
     */
    protected static GridPlacing fieldPlacing() {
        return labelPlacing().withInsets(0, 0, 5, 0);
    }
}
