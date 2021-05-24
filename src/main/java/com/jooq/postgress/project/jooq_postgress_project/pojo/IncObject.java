package com.jooq.postgress.project.jooq_postgress_project.pojo;

//import javax.persistence.*;
import java.sql.Timestamp;

//@Entity
//@Table(name = "incidents")
public class IncObject {
//    @Id
//    @GeneratedValue(strategy = GenerationType.IDENTITY)
//    @Column(name = "id_incident")
    private String id_incident; //Код инцидента
//    @Column(name = "status_value")
    private String status;//Статус
//    @Column(name = "priority")
    private int priority;//Приоритет
//    @Column(name = "group_tp3")
    private String groupTP3;//Группа ТП3
//    @Column(name = "responsible_tp3")
    private String responsibleTP3;//Ответственный ТП3

//    @Column(name = "time_appointment")
    private Timestamp timeAppointment;//Время Назначен""
//    @Column(name = "time_work")
    private Timestamp timeWork;//Время В работе""
//    @Column(name = "time_done")
    private Timestamp timeDone;//Время Выполнено""
//    @Column(name = "deadline")
    private Timestamp deadline; //Предельный срок исполнения

//    @Column(name = "overdue")
    private boolean overdue;//Просрочено
//    @Column(name = "code_completion")
    private String codeCompletion;//Код завершения

    public IncObject(String id_incident, String status, int priority, String groupTP3, String responsibleTP3,
                     Timestamp timeAppointment, Timestamp timeWork, Timestamp timeDone, Timestamp deadline,
                     boolean overdue, String codeCompletion) {
        this.id_incident = id_incident;
        this.status = status;
        this.priority = priority;
        this.groupTP3 = groupTP3;
        this.responsibleTP3 = responsibleTP3;
        this.timeAppointment = timeAppointment;
        this.timeWork = timeWork;
        this.timeDone = timeDone;
        this.deadline = deadline;
        this.overdue = overdue;
        this.codeCompletion = codeCompletion;
    }

    @Override
    public String toString() {
        return "IncObject{" +
                "id_incident='" + id_incident + '\'' +
                ", status='" + status + '\'' +
                ", priority='" + priority + '\'' +
                ", groupTP3='" + groupTP3 + '\'' +
                ", responsibleTP3='" + responsibleTP3 + '\'' +
                ", timeAppointment='" + timeAppointment + '\'' +
                ", timeWork='" + timeWork + '\'' +
                ", timeDone='" + timeDone + '\'' +
                ", deadline='" + deadline + '\'' +
                ", overdue='" + overdue + '\'' +
                ", codeCompletion='" + codeCompletion + '\'' +
                '}';
    }


    public boolean isOverdue() {
        return overdue;
    }

    public void setOverdue(boolean overdue) {
        this.overdue = overdue;
    }

    public String getIdIcident() {
        return id_incident;
    }

    public void setIdIncident(String id_incident) {
        this.id_incident = id_incident;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public int getPriority() {
        return priority;
    }

    public void setPriority(int priority) {
        this.priority = priority;
    }

    public String getGroupTP3() {
        return groupTP3;
    }

    public void setGroupTP3(String groupTP3) {
        this.groupTP3 = groupTP3;
    }

    public String getResponsibleTP3() {
        return responsibleTP3;
    }

    public void setResponsibleTP3(String responsibleTP3) {
        this.responsibleTP3 = responsibleTP3;
    }

    public Timestamp getTimeAppointment() {
        return timeAppointment;
    }

    public void setTimeAppointment(Timestamp timeAppointment) {
        this.timeAppointment = timeAppointment;
    }

    public Timestamp getTimeWork() {
        return timeWork;
    }

    public void setTimeWork(Timestamp timeWork) {
        this.timeWork = timeWork;
    }

    public Timestamp getTimeDone() {
        return timeDone;
    }

    public void setTimeDone(Timestamp timeDone) {
        this.timeDone = timeDone;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getCodeCompletion() {
        return codeCompletion;
    }

    public void setCodeCompletion(String codeCompletion) {
        this.codeCompletion = codeCompletion;
    }

    public IncObject() {
    }
}
