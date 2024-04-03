package com.master.board.domain.models.enums;

public enum CardItemState {
    unassigned,
    assigned,
    in_progress,
    review,
    rejected,
    completed;

    public static boolean contains(String value) {
        for (CardItemState state : CardItemState.values()) {
            if (state.name().equalsIgnoreCase(value)) {
                return true;
            }
        }
        return false;
    }
}