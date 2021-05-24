package com.jooq.postgress.project.jooq_postgress_project.dao;


import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


public interface IncidentsRepository {

    void saveAllIncidents(List<IncObject> incObjects);
    List<IncObject> getAllIncidents();
    void saveIncident(IncObject employee);
    IncObject getIncident(String id);
    void deleteIncident(String id);
    void deleteAll();
//public List<IncObject> findAllByName(String name);
}
