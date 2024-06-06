package dev.cpini.paradigmas_lab3;

import dev.cpini.paradigmas_lab3.tda.IUser;

public class User implements IUser {
    private final String name;

    public User(String name) {
        this.name = name;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj instanceof User) {
            User user = (User) obj;
            return getName().equals(user.getName());
        }
        return false;
    }

    @Override
    public String toString() {
        return getName();
    }
}
