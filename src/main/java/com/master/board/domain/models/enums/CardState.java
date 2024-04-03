package com.master.board.domain.models.enums;

public enum CardState {
    pending,
    canceled,
    completed;

    public static boolean contains(String value) {
        for (CardState state : CardState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}