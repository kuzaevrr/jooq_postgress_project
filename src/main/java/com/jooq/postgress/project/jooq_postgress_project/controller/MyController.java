package com.jooq.postgress.project.jooq_postgress_project.controller;

import com.jooq.postgress.project.jooq_postgress_project.logic.LogicPaymentKPI;
import com.jooq.postgress.project.jooq_postgress_project.parser.Parser;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.service.ServiceJooq;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.multipart.MultipartFile;


import java.io.BufferedOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.text.ParseException;
import java.util.List;

@Controller
public class MyController {

    @Autowired
    private ServiceJooq service;
    @Autowired
    private Parser parser;
    @Autowired
    private LogicPaymentKPI logicPaymentKPI;

    @GetMapping("/api")
    public double resultKpi() throws ParseException{
        return logicPaymentKPI.logicExcel(service);
    }

    @GetMapping("/")
    public String mainMethod(Model model) throws ParseException {
        double resultKpi = logicPaymentKPI.logicExcel(service);
        model.addAttribute("kpi", resultKpi);
        return "main";
    }

    @GetMapping("/view_all_incidents")
    public String showAllEmployees(Model model) {
        List<IncObject> allEmployeesOne = service.getAllIncidents();
        model.addAttribute("allEmps", allEmployeesOne);
        return "all-incident";
    }
//
//    @PostMapping("/parser")//GET
//    public String parser() {
////        service.saveAllIncidents(allEmployeesOne);
//        return "redirect:/";
//    }

    @GetMapping("/upload")//GET
    public String uploadGet() {
//        parser.parseXLS(service);
        return "download_file";
    }

    @PostMapping("/upload")//GET
    public String uploadPost( MultipartFile file) {
        service.deleteAll();
        if (!file.isEmpty()) {
            try {
                service.deleteAll();
                byte[] bytes = file.getBytes();
                //получаем абсолютный путь к папке с приложением
                BufferedOutputStream stream =
                        new BufferedOutputStream(new FileOutputStream(
                                new File(new File("").getAbsolutePath()+"/src/main/resources/excel/"+"file.xls")));
                stream.write(bytes);
                stream.close();
                System.out.println("Вы удачно загрузили "  + " в "  + "!");
            } catch (Exception e) {
                System.out.println("Вам не удалось загрузить "  + " => " + e.getMessage());
            }
        } else {
            System.out.println("Вам не удалось загрузить "  + " потому что файл пустой.");
        }
        parser.parseXLS(service);
        return "redirect:/";
    }

    @PostMapping("/delete_all")//GET
    public String deletedAll() {
        service.deleteAll();
        return "redirect:/";
    }


//    @PostMapping("/saveEmployee")//POST
//    public String saveEmployee(@ModelAttribute("employee") IncObject employee) {
//        service.saveEmployee(employee);
//        return "redirect:/";
//    }
//
//    @GetMapping("/updateInfo")//POST
//    public String updateEmployee(@RequestParam("empId") int id, Model model) {
//        IncObject employee = service.getEmployee(id);
//        model.addAttribute("employee", employee);
//        return "employee-info";
//    }
//
//    @PostMapping("/deleteEmployee")//POST
//    public String deleteEmployee(@RequestParam("empId") int id) {
//        service.deleteEmployee(id);
//        return "redirect:/";
//    }
}
