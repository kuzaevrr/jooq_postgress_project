package com.jooq.postgress.project.jooq_postgress_project.dao.day_off;

import com.jooq.postgress.project.jooq_postgress_project.pojo.DayOff;

import java.sql.Timestamp;
import java.util.List;

public interface DayOffRepository {

    List<DayOff> getAllDayOff();
    void addDayOff(Timestamp timestampDayOff);
    void deleteDayOff(DayOff dayOff);
    DayOff getDayOff(Timestamp timestampDayOff);

}
