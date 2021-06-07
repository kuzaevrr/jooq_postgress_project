package com.jooq.postgress.project.jooq_postgress_project.service;

import com.jooq.postgress.project.jooq_postgress_project.pojo.DayOff;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;


public interface ServiceJooq {

    void saveAllIncidents(List<IncObject> incObjects);
    List<IncObject> getAllIncidents();
    void saveIncident(IncObject employee);
    IncObject getIncident(String id);
    void deleteIncident(String id);
    void deleteAll();

    List<DayOff> getAllDayOff();
    void addDayOff(Timestamp timestampDayOff);
    void deleteDayOff(DayOff dayOff);
    DayOff getDayOff(Timestamp timestampDayOff);

}
