package dev.cpini.paradigmas_lab3.tda;

import dev.cpini.paradigmas_lab3.Chatbot;
import dev.cpini.paradigmas_lab3.Flow;
import dev.cpini.paradigmas_lab3.User;

import java.util.List;

public interface IChatHistory {
    /**
     * Obtiene el usuario relacionado.
     *
     * @return usuario al que le pertenece el historial
     */
    User getUser();

    /**
     * Obtiene la lista de mensajes.
     *
     * @return lista de mensajes del historial
     */
    List<String> getMessages();

    /**
     * Obtiene el flujo actual.
     *
     * @return flujo actual del historial
     */
    Flow getCurrentFlow();

    /**
     * Añade una interacción ya existente al historial.
     *
     * @param message mensaje a registrar
     * @param chatbot chatbot resultante
     * @param flow    flujo resultante
     */
    void addInteraction(String message, Chatbot chatbot, Flow flow);
}
