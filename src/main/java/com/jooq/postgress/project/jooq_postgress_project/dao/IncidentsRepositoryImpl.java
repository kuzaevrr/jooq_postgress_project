package com.jooq.postgress.project.jooq_postgress_project.dao;

import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;

import com.jooq.postgress.project.jooq_postgress_project.tables.Incidents;
import org.jooq.DSLContext;
import org.jooq.Record;
import org.jooq.Result;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

@Repository
@Transactional
public class IncidentsRepositoryImpl implements IncidentsRepository {

    @Autowired
    private DSLContext dsl;

    @Override
    public void saveAllIncidents(List<IncObject> incObjects) {
        for(IncObject incObject: incObjects){
            try {
                 dsl.insertInto(Incidents.INCIDENTS)
                        .set(Incidents.INCIDENTS.ID_INCIDENT, incObject.getIdIcident())
                        .set(Incidents.INCIDENTS.STATUS_VALUE, incObject.getStatus())
                        .set(Incidents.INCIDENTS.PRIORITY, incObject.getPriority())
                        .set(Incidents.INCIDENTS.GROUP_TP3, incObject.getGroupTP3())
                        .set(Incidents.INCIDENTS.RESPONSIBLE_TP3, incObject.getResponsibleTP3())
                        .set(Incidents.INCIDENTS.TIME_APPOINTMENT, incObject.getTimeAppointment().toLocalDateTime())
                        .set(Incidents.INCIDENTS.TIME_WORK, incObject.getTimeWork().toLocalDateTime())
                        .set(Incidents.INCIDENTS.TIME_DONE, incObject.getTimeDone().toLocalDateTime())
                        .set(Incidents.INCIDENTS.DEADLINE, incObject.getDeadline().toLocalDateTime())
                        .set(Incidents.INCIDENTS.OVERDUE, incObject.isOverdue())
                        .set(Incidents.INCIDENTS.CODE_COMPLETION, incObject.getCodeCompletion())
                        .returning(Incidents.INCIDENTS.ID_INCIDENT)
                        .fetchOne();
            }catch (Exception e){
                System.out.println("Инцидент с id "+ incObject.getIdIcident() + " уже существует");
            }
        }
    }

    @Override
    public List<IncObject> getAllIncidents() {
        List<IncObject> posts = new ArrayList<>();
        Result<Record> result = dsl.select().from(Incidents.INCIDENTS).fetch();
        for (Record r : result) {
            posts.add(getIncidentsEntity(r));
        }
        return posts;

    }

    @Override
    public void saveIncident(IncObject incident) {
        try {
                dsl.insertInto(Incidents.INCIDENTS)
                    .set(Incidents.INCIDENTS.ID_INCIDENT, incident.getIdIcident())
                    .set(Incidents.INCIDENTS.STATUS_VALUE, incident.getStatus())
                    .set(Incidents.INCIDENTS.PRIORITY, incident.getPriority())
                    .set(Incidents.INCIDENTS.GROUP_TP3, incident.getGroupTP3())
                    .set(Incidents.INCIDENTS.RESPONSIBLE_TP3, incident.getResponsibleTP3())
                    .set(Incidents.INCIDENTS.TIME_APPOINTMENT, incident.getTimeAppointment().toLocalDateTime())
                    .set(Incidents.INCIDENTS.TIME_WORK, incident.getTimeWork().toLocalDateTime())
                    .set(Incidents.INCIDENTS.TIME_DONE, incident.getTimeDone().toLocalDateTime())
                    .set(Incidents.INCIDENTS.DEADLINE, incident.getDeadline().toLocalDateTime())
                    .set(Incidents.INCIDENTS.OVERDUE, incident.isOverdue())
                    .set(Incidents.INCIDENTS.CODE_COMPLETION, incident.getCodeCompletion())
                    .returning(Incidents.INCIDENTS.ID_INCIDENT)
                    .fetchOne();
        }catch (Exception e){
            System.out.println("Инцидент с id "+ incident.getIdIcident() + " уже существует");
        }
    }

    @Override
    public IncObject getIncident(String id) {
        try {
            return Objects.requireNonNull(dsl.selectFrom(Incidents.INCIDENTS)
                    .where(Incidents.INCIDENTS.ID_INCIDENT.eq(id))
                    .fetchAny())  //здесь определяем, что мы хотим вернуть
                    .into(IncObject.class);
        }catch (NullPointerException e){
            return null;
        }
    }

    @Override
    public void deleteIncident(String id) {
        dsl.deleteFrom(Incidents.INCIDENTS)
                .where(Incidents.INCIDENTS.ID_INCIDENT.eq(id))
                .execute();
    }

    @Override
    public void deleteAll() {
        dsl.truncate(Incidents.INCIDENTS)
                .execute();
    }

    private IncObject getIncidentsEntity(Record r){
        String id_inc = r.getValue(Incidents.INCIDENTS.ID_INCIDENT, String.class);
        String status_value = r.getValue(Incidents.INCIDENTS.STATUS_VALUE, String.class);
        Integer priority = r.getValue(Incidents.INCIDENTS.PRIORITY, Integer.class);
        String group_tp3 = r.getValue(Incidents.INCIDENTS.GROUP_TP3, String.class);
        String responsible_tp3 = r.getValue(Incidents.INCIDENTS.RESPONSIBLE_TP3, String.class);
        Timestamp time_appointment = r.getValue(Incidents.INCIDENTS.TIME_APPOINTMENT, Timestamp.class);
        Timestamp time_work = r.getValue(Incidents.INCIDENTS.TIME_WORK, Timestamp.class);
        Timestamp time_done = r.getValue(Incidents.INCIDENTS.TIME_DONE, Timestamp.class);
        Timestamp deadline = r.getValue(Incidents.INCIDENTS.DEADLINE, Timestamp.class);
        Boolean overdue = r.getValue(Incidents.INCIDENTS.OVERDUE, Boolean.class);
        String code_completion = r.getValue(Incidents.INCIDENTS.CODE_COMPLETION, String.class);

        return new IncObject(id_inc, status_value, priority, group_tp3, responsible_tp3,
                time_appointment, time_work,time_done,deadline,overdue,code_completion);
    }
}
