package com.master.board.domain.models.enums;

public enum Role {
    administrator,
    project_manager,
    developer;

    public static boolean contains(String value) {
        for (Role role : Role.values()) {
            if (role.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}