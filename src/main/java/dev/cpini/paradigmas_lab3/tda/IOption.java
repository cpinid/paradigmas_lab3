package dev.cpini.paradigmas_lab3.tda;

import java.util.List;

public interface IOption {
    /**
     * Obtiene código de la opción.
     *
     * @return código
     */
    int getCode();

    /**
     * Obtiene el mensaje de la opción.
     *
     * @return mensaje
     */
    String getMessage();

    /**
     * Obtiene las palabras clave de la opción.
     *
     * @return palabras clave
     */
    List<String> getKeywords();

    /**
     * Obtiene el código del chatbot de destino de la opción.
     *
     * @return código del chatbot de destino
     */
    int getChatbotCodeLink();

    /**
     * Obtiene el código del flujo inicial destino de la opción.
     *
     * @return código del flujo inicial destino
     */
    int getInitialFlowCodeLink();

    /**
     * Verifica si un mensaje coincide con la opción.
     *
     * @param message mensaje a comparar con palabras clave
     * @return true si coincide, de lo contrario false
     */
    boolean messageMatches(String message);

}
