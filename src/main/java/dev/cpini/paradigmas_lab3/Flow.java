package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.IFlow;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public class Flow implements IFlow {
    private final int id;
    private final String nameMsg;
    private final List<Option> options;

    public Flow(int id, String nameMsg, List<Option> options) {
        this.id = id;
        this.nameMsg = nameMsg;
        this.options = new ArrayList<>();
        for (Option option : options)
            flowAddOption(option);
    }

    public Flow(int id, String nameMsg) {
        this(id, nameMsg, new ArrayList<>());
    }

    @Override
    public int getId() {
        return id;
    }

    @Override
    public String getNameMsg() {
        return nameMsg;
    }

    @Override
    public List<Option> getOptions() {
        return options;
    }

    @Override
    public Option findOptionByMessage(String message) {
        for (Option option : getOptions())
            if (option.messageMatches(message)) return option;
        return null;
    }

    @Override
    public void flowAddOption(Option option) {
        List<Option> options = getOptions();
        if (!options.contains(option)) options.add(option);
        else throw new IllegalArgumentException("La opción ya existe");
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof Flow) {
            Flow flow = (Flow) obj;
            return getId() == flow.getId();
        }
        return false;
    }

    @Override
    public String toString() {
        return getOptions().stream().map(Option::toString).collect(Collectors.joining("\n"));
    }
}
