package com.example.c4q.capstone.database.model.events;


class VenueAgeRestriction {
    private boolean no_restriction;
    private boolean over_18;
    private boolean over_21;

    public boolean isNo_restriction() {
        return no_restriction;
    }

    public void setNo_restriction(boolean no_restriction) {
        this.no_restriction = no_restriction;
    }

    public boolean isOver_18() {
        return over_18;
    }

    public void setOver_18(boolean over_18) {
        this.over_18 = over_18;
    }

    public boolean isOver_21() {
        return over_21;
    }

    public void setOver_21(boolean over_21) {
        this.over_21 = over_21;
    }
}
