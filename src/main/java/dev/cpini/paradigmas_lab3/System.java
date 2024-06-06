package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.ISystem;

import java.util.*;

public class System implements ISystem {
    private static final User NO_LOGGED_USER = null;
    private static final String INITIAL_MESSAGE = "Hola";
    private final String name;
    private final int initialChatbotCodeLink;
    private final List<Chatbot> chatbots;
    private final List<User> users;
    private final Map<User, ChatHistory> chatHistories;
    private User loggedUser;

    public System(String name, int initialChatbotCodeLink, List<Chatbot> chatbots) {
        this.name = name;
        this.initialChatbotCodeLink = initialChatbotCodeLink;
        this.chatbots = new ArrayList<>();
        for (Chatbot chatbot : chatbots)
            systemAddChatbot(chatbot);
        users = new ArrayList<>();
        chatHistories = new HashMap<>();
        loggedUser = NO_LOGGED_USER;
    }

    public System(String name, int initialChatbotCodeLink) {
        this(name, initialChatbotCodeLink, new ArrayList<>());
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public int getInitialChatbotCodeLink() {
        return initialChatbotCodeLink;
    }

    @Override
    public List<Chatbot> getChatbots() {
        return chatbots;
    }

    @Override
    public List<User> getUsers() {
        return users;
    }

    @Override
    public Map<User, ChatHistory> getChatHistories() {
        return chatHistories;
    }

    @Override
    public User getLoggedUser() {
        return loggedUser;
    }

    @Override
    public boolean hasLoggedUser() {
        return getLoggedUser() != NO_LOGGED_USER;
    }

    @Override
    public Chatbot getChatbotById(int id) {
        List<Chatbot> chatbots = getChatbots();
        for (Chatbot chatbot : chatbots)
            if (chatbot.getId() == id) return chatbot;
        return null;
    }

    @Override
    public User getUserByName(String name) {
        List<User> users = getUsers();
        for (User user : users)
            if (user.getName().equals(name)) return user;
        return null;
    }

    @Override
    public void systemAddChatbot(Chatbot chatbot) {
        List<Chatbot> chatbots = getChatbots();
        if (!chatbots.contains(chatbot)) chatbots.add(chatbot);
        else throw new IllegalArgumentException("El chatbot ya existe");
    }

    @Override
    public void systemAddUser(User user) {
        List<User> users = getUsers();
        if (!users.contains(user)) users.add(user);
        else throw new IllegalArgumentException("Ya existe un usuario con ese nombre");
    }

    @Override
    public void systemLogin(String user) {
        if (hasLoggedUser()) throw new IllegalStateException("Ya hay un usuario con sesión iniciada");
        User actualUser = getUserByName(user);
        if (actualUser == null) throw new IllegalArgumentException("El usuario no existe");
        loggedUser = actualUser;
    }

    @Override
    public void systemLogout() {
        if (!hasLoggedUser()) return;
        loggedUser = NO_LOGGED_USER;
    }

    @Override
    public void systemTalk(String message) {
        if (!hasLoggedUser()) return;
        Map<User, ChatHistory> chatHistories = getChatHistories();
        User loggedUser = getLoggedUser();
        if (chatHistories.containsKey(loggedUser)) {
            ChatHistory chatHistory = chatHistories.get(loggedUser);
            Option option = chatHistory.getCurrentFlow().findOptionByMessage(message);
            if (option == null) throw new IllegalArgumentException("El mensaje no coincide con ninguna opción");
            Chatbot chatbot = getChatbotById(option.getChatbotCodeLink());
            chatHistory.addInteraction(message, chatbot, chatbot.getFlowById(option.getInitialFlowCodeLink()));
        } else {
            Chatbot chatbot = getChatbotById(getInitialChatbotCodeLink());
            Flow flow = chatbot.getFlowById(chatbot.getStartFlowId());
            ChatHistory chatHistory = new ChatHistory(getLoggedUser(), flow);
            chatHistory.addInteraction(message, chatbot, flow);
            chatHistories.put(loggedUser, chatHistory);
        }
    }

    @Override
    public void systemSynthesis(String user) {
        java.lang.System.out.println(getChatHistories().get(getUserByName(user)).toString());
    }

    @Override
    public void systemSimulate(int maxInteractions, int seed) {
        User user = getLoggedUser();
        Map<User, ChatHistory> chatHistories = getChatHistories();
        ChatHistory chatHistory;
        if (chatHistories.containsKey(user)) {
            chatHistory = chatHistories.get(user);
        } else {
            Chatbot chatbot = getChatbotById(getInitialChatbotCodeLink());
            Flow flow = chatbot.getFlowById(chatbot.getStartFlowId());
            chatHistory = new ChatHistory(user, flow);
            chatHistory.addInteraction(INITIAL_MESSAGE, chatbot, flow);
            chatHistories.put(user, chatHistory);
            maxInteractions--;
        }

        Random random = new Random(seed);

        for (; maxInteractions > 0; maxInteractions--) {
            List<Option> options = chatHistory.getCurrentFlow().getOptions();
            Option option = options.get(random.nextInt(options.size()));
            Chatbot chatbot = getChatbotById(option.getChatbotCodeLink());
            chatHistory.addInteraction(String.valueOf(option.getCode()), chatbot,
                    chatbot.getFlowById(option.getInitialFlowCodeLink()));
        }
    }
}
