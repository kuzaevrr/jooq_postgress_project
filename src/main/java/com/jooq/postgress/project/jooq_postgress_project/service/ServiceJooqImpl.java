package com.jooq.postgress.project.jooq_postgress_project.service;


import com.jooq.postgress.project.jooq_postgress_project.dao.IncidentsRepository;
import com.jooq.postgress.project.jooq_postgress_project.dao.day_off.DayOffRepository;
import com.jooq.postgress.project.jooq_postgress_project.pojo.DayOff;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.List;

@Service
public class ServiceJooqImpl implements ServiceJooq {

    @Autowired
    private IncidentsRepository incidentsRepository;
    @Autowired
    private DayOffRepository dayOffRepository;

    @Override
    public void saveAllIncidents(List<IncObject> incObjects) {
        incidentsRepository.saveAllIncidents(incObjects);
    }

    @Override
    public List<IncObject> getAllIncidents() {
        return incidentsRepository.getAllIncidents();
    }

    @Override
    public void saveIncident(IncObject incObject) {
        incidentsRepository.saveIncident(incObject);
    }

    @Override
    public IncObject getIncident(String id) {
        return incidentsRepository.getIncident(id);
    }

    @Override
    public void deleteIncident(String id) {
        incidentsRepository.deleteIncident(id);
    }

    @Override
    public void deleteAll() {
        incidentsRepository.deleteAll();
    }

    @Override
    public List<DayOff> getAllDayOff() {
        return dayOffRepository.getAllDayOff();
    }

    @Override
    public void addDayOff(Timestamp timestampDayOff) {
        dayOffRepository.addDayOff(timestampDayOff);
    }

    @Override
    public void deleteDayOff(DayOff dayOff) {
        dayOffRepository.deleteDayOff(dayOff);
    }

    @Override
    public DayOff getDayOff(Timestamp timestampDayOff) {
        return dayOffRepository.getDayOff(timestampDayOff);
    }
}
