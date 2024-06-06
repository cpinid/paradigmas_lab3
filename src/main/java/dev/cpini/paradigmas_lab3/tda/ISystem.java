package dev.cpini.paradigmas_lab3.tda;

import dev.cpini.paradigmas_lab3.ChatHistory;
import dev.cpini.paradigmas_lab3.Chatbot;
import dev.cpini.paradigmas_lab3.User;

import java.util.List;
import java.util.Map;

public interface ISystem {
    /**
     * Obtiene el nombre del sistema.
     *
     * @return nombre
     */
    String getName();

    /**
     * Obtiene la id del chatbot inicial del sistema.
     *
     * @return id del chatbot inicial
     */
    int getInitialChatbotCodeLink();

    /**
     * Obtiene la lista de chatbots del sistema.
     *
     * @return lista de chatbots
     */
    List<Chatbot> getChatbots();

    /**
     * Obtiene la lista de usuarios del sistema.
     *
     * @return lista de usuarios
     */
    List<User> getUsers();

    /**
     * Obtiene el mapa de usuarios e historiales del sistema.
     *
     * @return mapa de historiales de conversación
     */
    Map<User, ChatHistory> getChatHistories();

    /**
     * Obtiene el usuario con sesión iniciada en el sistema.
     *
     * @return usuario con sesión iniciada
     */
    User getLoggedUser();

    /**
     * Verifica si hay una sesión iniciada en el sistema.
     *
     * @return true si hay un usuario con sesión iniciada, de lo contrario false
     */
    boolean hasLoggedUser();

    /**
     * Obtiene un chatbot del sistema en base a su id.
     *
     * @param id id del chatbot a buscar
     * @return chatbot con id coincidente
     */
    Chatbot getChatbotById(int id);

    /**
     * Obtiene un usuario en base a su nombre.
     *
     * @param name nombre del usuario a buscar
     * @return usuario con nombre coincidente
     */
    User getUserByName(String name);

    /**
     * Añade un chatbot al sistema, no añade duplicados.
     *
     * @param chatbot chatbot a añadir
     */
    void systemAddChatbot(Chatbot chatbot);

    /**
     * Añade un usuario al sistema, no añade duplicados.
     *
     * @param user usuario a añadir
     */
    void systemAddUser(User user);

    /**
     * Inicia sesión en el sistema con un nombre de usuario.
     *
     * @param user usuario a iniciar sesión
     */
    void systemLogin(String user);

    /**
     * Cierra la sesión actual del sistema.
     */
    void systemLogout();

    /**
     * Interactúa con el sistema. Requiere que un usuario haya iniciado sesión.
     *
     * @param message mensaje para interactuar
     */
    void systemTalk(String message);

    /**
     * Obtiene el historial de interacciones de un usuario del sistema.
     *
     * @param user usuario del que se obtendrá el historial
     */
    void systemSynthesis(String user);

    /**
     * Simula realizar interacciones entre chatbots del sistema.
     *
     * @param maxInteractions cantidad máxima de interacciones a realizar
     * @param seed            semilla a utilizar
     */
    void systemSimulate(int maxInteractions, int seed);

}
