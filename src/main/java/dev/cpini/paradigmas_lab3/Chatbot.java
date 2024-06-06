package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.IChatbot;

import java.util.ArrayList;
import java.util.List;

public class Chatbot implements IChatbot {
    private final int id;
    private final String name;
    private final String welcomeMessage;
    private final int startFlowId;
    private final List<Flow> flows;

    public Chatbot(int id, String name, String welcomeMessage, int startFlowId, List<Flow> flows) {
        this.id = id;
        this.name = name;
        this.welcomeMessage = welcomeMessage;
        this.startFlowId = startFlowId;
        this.flows = new ArrayList<>();
        for (Flow flow : flows)
            chatbotAddFlow(flow);
    }

    public Chatbot(int id, String name, String welcomeMessage, int startFlowId) {
        this(id, name, welcomeMessage, startFlowId, new ArrayList<>());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getWelcomeMessage() {
        return welcomeMessage;
    }

    @Override
    public int getStartFlowId() {
        return startFlowId;
    }

    @Override
    public List<Flow> getFlows() {
        return flows;
    }

    @Override
    public Flow getFlowById(int id) {
        List<Flow> flows = getFlows();
        for (Flow flow : flows)
            if (flow.getId() == id) return flow;
        return null;
    }

    @Override
    public void chatbotAddFlow(Flow flow) {
        List<Flow> flows = getFlows();
        if (!flows.contains(flow)) flows.add(flow);
        else throw new IllegalArgumentException("El flujo ya existe");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Chatbot) {
            Chatbot chatbot = (Chatbot) obj;
            return getId() == chatbot.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }
}
