package com.jooq.postgress.project.jooq_postgress_project.parser;

import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.service.ServiceJooq;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.Resource;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

@Component(value = "parser")
public class Parser {

    @Value(value = "classpath:excel/file.xls")
    private Resource companiesXml;


    private String stringIsBool = "✔";

    public void parseXLS(ServiceJooq service) {
        List<IncObject> listResult = new ArrayList<>();
        HSSFWorkbook wb = null;
        try {
            InputStream in  = companiesXml.getInputStream();
            wb = new HSSFWorkbook(in);
            System.out.println("Парсинг документа начался успешно!");
        } catch (IOException e) {
            e.printStackTrace();
        }

        Sheet sheet = wb.getSheetAt(0);
        Iterator<Row>  it = sheet.iterator();

        int i = 0;
        while (it.hasNext()) {
            Row row = it.next();
            if (i == 0) {
            } else {

                IncObject incObject = new IncObject(
                        String.valueOf(row.getCell(0)),
                        String.valueOf(row.getCell(1)),
                        Integer.parseInt(String.valueOf(row.getCell(2)).split(" ")[0]),
                        String.valueOf(row.getCell(3)),
                        String.valueOf(row.getCell(4)),
                        Timestamp.valueOf(transformationDate(row,5)),
                        Timestamp.valueOf(transformationDate(row,6)),
                        Timestamp.valueOf(transformationDate(row,7)),
                        Timestamp.valueOf(transformationDate(row,8)),
                        String.valueOf(row.getCell(9)).equals(stringIsBool),
                        String.valueOf(row.getCell(10))
                );
                listResult.add(incObject);
            }

            i++;
        }
        service.saveAllIncidents(listResult);
        System.out.println("Парсинг документа закончен успешно!");
    }

    private String transformationDate(Row row, int numb) {
        return "20"
                + String.valueOf(row.getCell(numb)).split(" ")[0].split("\\.")[2] + "-"
                + String.valueOf(row.getCell(numb)).split(" ")[0].split("\\.")[1] + "-"
                + String.valueOf(row.getCell(numb)).split(" ")[0].split("\\.")[0] + " "
                + String.valueOf(row.getCell(numb)).split(" ")[1] + ".000000000";
    }
}