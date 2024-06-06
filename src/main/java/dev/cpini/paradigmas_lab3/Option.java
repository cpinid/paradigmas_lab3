package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.IOption;

import java.util.List;
import java.util.stream.Collectors;

public class Option implements IOption {
    private final int code;
    private final String message;
    private final int chatbotCodeLink;
    private final int initialFlowCodeLink;
    private final List<String> keyword;

    public Option(int code, String message, int chatbotCodeLink, int initialFlowCodeLink, List<String> keyword) {
        this.code = code;
        this.message = message;
        this.chatbotCodeLink = chatbotCodeLink;
        this.initialFlowCodeLink = initialFlowCodeLink;
        this.keyword = keyword.stream().map(String::toLowerCase).collect(Collectors.toList());
    }

    @Override
    public int getCode() {
        return code;
    }

    @Override
    public String getMessage() {
        return message;
    }

    @Override
    public int getChatbotCodeLink() {
        return chatbotCodeLink;
    }

    @Override
    public int getInitialFlowCodeLink() {
        return initialFlowCodeLink;
    }

    @Override
    public List<String> getKeywords() {
        return keyword;
    }

    @Override
    public boolean messageMatches(String message) {
        return String.valueOf(getCode()).equals(message) || getKeywords().contains(message.toLowerCase());
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Option) {
            Option option = (Option) obj;
            return getCode() == option.getCode();
        }
        return false;
    }

    @Override
    public String toString() {
        return getMessage();
    }
}
