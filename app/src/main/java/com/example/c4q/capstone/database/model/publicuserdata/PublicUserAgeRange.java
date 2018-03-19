package com.example.c4q.capstone.database.model.publicuserdata;


class PublicUserAgeRange {
    private boolean over_18;
    private boolean over_21;

    public PublicUserAgeRange() {
    }

    public PublicUserAgeRange(boolean over_18, boolean over_21) {
        this.over_18 = over_18;
        this.over_21 = over_21;
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
