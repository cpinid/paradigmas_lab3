package dev.cpini.paradigmas_lab3.tda;

import dev.cpini.paradigmas_lab3.Option;

import java.util.List;

public interface IFlow {
    /**
     * Obtiene la id del flujo.
     *
     * @return id
     */
    int getId();

    /**
     * Obtiene el nombre del flujo.
     *
     * @return nombre
     */
    String getNameMsg();

    /**
     * Obtiene la lista de opciones del flujo.
     *
     * @return lista de opciones
     */
    List<Option> getOptions();

    /**
     * Obtiene una opción del flujo que coincide con un mensaje.
     *
     * @param message mensaje de la opción a buscar
     * @return opción con mensaje coincidente
     */
    Option findOptionByMessage(String message);

    /**
     * Añade una opción al flujo, no añade duplicados.
     *
     * @param option opción a añadir
     */
    void flowAddOption(Option option);

}
