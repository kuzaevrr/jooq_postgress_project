package com.jooq.postgress.project.jooq_postgress_project.pojo;

import java.sql.Timestamp;

public class DayOff {

    private Timestamp dayOff;

    public DayOff(Timestamp dayOff) {
        this.dayOff = dayOff;
    }

    public Timestamp getDayOff() {
        return dayOff;
    }

    public void setDayOff(Timestamp dayOff) {
        this.dayOff = dayOff;
    }

    @Override
    public String toString() {
        return "DayOff{" +
                "dayOff=" + dayOff +
                '}';
    }
}
