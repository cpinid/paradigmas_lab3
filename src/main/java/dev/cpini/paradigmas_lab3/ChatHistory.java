package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.IChatHistory;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

public class ChatHistory implements IChatHistory {
    private final User user;
    private final List<String> messages;
    private Flow currentFlow;

    public ChatHistory(User user, Flow flow) {
        this.user = user;
        this.messages = new ArrayList<>();
        currentFlow = flow;
    }

    @Override
    public User getUser() {
        return user;
    }

    @Override
    public List<String> getMessages() {
        return messages;
    }

    @Override
    public Flow getCurrentFlow() {
        return currentFlow;
    }

    @Override
    public void addInteraction(String message, Chatbot chatbot, Flow flow) {
        String time = LocalDateTime.now().format(DateTimeFormatter.ofPattern("HH:mm:ss dd/MM/yy"));
        getMessages().add(String.format("%s - %s: %s\n%s - %s: %s\n%s", time, getUser().toString(), message, time,
                chatbot.toString(), flow.getNameMsg(), flow));
        currentFlow = flow;
    }

    @Override
    public String toString() {
        return String.join("\n\n", getMessages());
    }
}
