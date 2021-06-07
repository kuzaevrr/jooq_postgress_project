package com.jooq.postgress.project.jooq_postgress_project.controller;

import com.jooq.postgress.project.jooq_postgress_project.logic.LogicPaymentKPI;
import com.jooq.postgress.project.jooq_postgress_project.parser.Parser;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.pojo.KPI;
import com.jooq.postgress.project.jooq_postgress_project.service.ServiceJooq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@RestController
@RequestMapping("/api")
public class MyRestController {

    @Autowired
    private ServiceJooq service;
    @Autowired
    private Parser parser;
    @Autowired
    private LogicPaymentKPI logicPaymentKPI;

    @GetMapping("/incidents")
    public List<IncObject> showAllEmployees() {
        List<IncObject> employees = service.getAllIncidents();
        return employees;
    }

    @GetMapping("/kpi")
    public KPI resultKpi() throws ParseException {
        return new KPI(logicPaymentKPI.logicExcel(service));
    }

    @GetMapping("/incidents/{id}")
    public IncObject showAllEmployees(@PathVariable String id) {
        IncObject employees = service.getIncident(id);
        if (employees == null) {
//            throw new NoSuchEmployeeException("There is no employee " +
//                    "with ID = " + id + " in Database");
        }
        return employees;
    }

    @PostMapping("/incidents")
    public IncObject addNewEmployees(@RequestBody IncObject employee) {
        service.saveIncident(employee);
        return employee;
    }

    @PutMapping("/incidents")
    public IncObject updateEmployees(@RequestBody IncObject employee) {
        service.saveIncident(employee);
        return employee;
    }

    @DeleteMapping("/incidents/{id}")
    public String deleteEmployees(@PathVariable String id) {
        if(service.getIncident(id) == null){
//            throw new NoSuchEmployeeException("There is no employee with ID = " +
//                    id+" in Database");
        }
        service.deleteIncident(id);
        return "Employee with ID = "+id+" was deleted!";
    }
}