package dev.cpini.paradigmas_lab3.tda;

import dev.cpini.paradigmas_lab3.Flow;

import java.util.List;

public interface IChatbot {
    /**
     * Obtiene el id del chatbot.
     *
     * @return id
     */
    int getId();

    /**
     * Obtiene el nombre del chatbot.
     *
     * @return nombre
     */
    String getName();

    /**
     * Obtiene el mensaje de bienvenida del chatbot.
     *
     * @return mensaje de bienvenida
     */
    String getWelcomeMessage();

    /**
     * Obtiene la id del flujo inicial del chatbot.
     *
     * @return id del flujo inicial
     */
    int getStartFlowId();

    /**
     * Obtiene la lista de flujos del chatbot.
     *
     * @return lista de flujos
     */
    List<Flow> getFlows();

    /**
     * Obtiene un flujo del chatbot en base a su id.
     *
     * @param id id del flujo a buscar
     * @return flujo con id coincidente
     */
    Flow getFlowById(int id);

    /**
     * Añade un flujo al chatbot, no añade duplicados.
     *
     * @param flow flujo a añadir
     */
    void chatbotAddFlow(Flow flow);

}
