package com.jooq.postgress.project.jooq_postgress_project.logic;

import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.service.ServiceJooq;
import org.springframework.stereotype.Component;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.List;

@Component(value = "logicPaymentKPI")
public class LogicPaymentKPI {

    enum Day {
        WORK_DAYS,
        NOT_WORK_DAYS
    }

    private int[] niArray = {4, 48, 72, 120};

    public double logicExcel(ServiceJooq service) throws ParseException {

        List<IncObject> incObjects = service.getAllIncidents();
        double KPI_ZPVVST = 0;
        double[] Wi = {0.4, 0.3, 0.25, 0.05};

        for (int iFor = 1; iFor <= 4; iFor++) {
            System.out.println("----------------Приоритете №" + iFor + "----------------");
            int ZPi = 0;
            int ZPiprvvst = 0;

            int Ni = 0;
            int Count = 0;

            double SumT = 0;
            double SPI_SUM;
            double NI_SUM;

            for (IncObject incObject : incObjects) {
                if (incObject.getPriority() == iFor) {//Список всех ицнидентов && incObject.getIdIcident().equals("IM186558")
                    ZPi++;

                    Ni = niArray[iFor - 1];//нормативное время за данный приоритет
                    if (incObject.isOverdue()) {

                        Count++; //часы
                        ZPiprvvst++;

                        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

                        double expectationHourTimeAppointment = (double) (format.parse(incObject.getTimeAppointment().toString().split(" ")[0] + " 20:00:00").getTime()
                                - format.parse(incObject.getTimeAppointment().toString()).getTime()) / 3600000;
                        double expectationHourTimeDeadline = (double) (format.parse(incObject.getDeadline().toString()).getTime()
                                - format.parse(incObject.getDeadline().toString().split(" ")[0] + " 08:00:00").getTime()) / 3600000;

                        double expectationTestTime = dayOff(incObject.getTimeAppointment().toString(), incObject.getDeadline().toString(), Day.WORK_DAYS) - 2;

//                        if (expectationHourTimeAppointment < 0) {
//                            expectationHourTimeAppointment = 0;
//                        }
//                        if (expectationHourTimeDeadline < 0) {
//                            expectationHourTimeDeadline = 0;
//                        }
                        //Время ожидания
//                        double expectationTime = (format.parse(incObject.getDeadline()).getTime()  //Дата Предельный срок
//                                - (format.parse(incObject.getTimeAppointment()).getTime() + (Ni * 60 * 60 * 1000)))
//                                - (dayOff(incObject.getTimeAppointment(), incObject.getDeadline(), Day.NOT_WORK_DAYS) * 24 * 3600000); //Дата открытия + Нормативное время (мс)

                        //Время ожидания
                        double expectationTime = (expectationHourTimeAppointment + (expectationTestTime * 12) + expectationHourTimeDeadline)
                                - (double) (Ni / 2);

//                        if(expectationTime < 0){
//                            expectationTime = 0;
//                        }

//                        System.out.println("Инцидент: " + incObject.getIdIcident()
//                                + "\nВремя создания: " + format.parse(incObject.getTimeAppointment())
//                                + "\nВремя предельного срока: " + format.parse(incObject.getDeadline())
//                                + "\nКоличество рабочих дней: " + dayOff(incObject.getTimeAppointment(), incObject.getDeadline(), Day.WORK_DAYS)
//                                + "\n(Первый день + дни между сроками + послежний день) - Нормативный срок = ("
//                                + (expectationHourTimeAppointment) + " + "
//                                + (expectationTestTime * 12) + " + "
//                                + (expectationHourTimeDeadline) + ") - "
//                                + (double) (Ni / 2) + " = "
//                                + expectationTime);


                        //Время просрока
//                        double timeOverdue = //округление
//                                ((format.parse(incObject.getTimeDone()).getTime() - //Дата выполнения
//                                        format.parse(incObject.getTimeAppointment()).getTime() - //Вычитаем дату создания
//                                        expectationTime) / 3600000) //Вычитаем время ожидания переводим в часы
//                                        - (dayOff(incObject.getDeadline(), incObject.getTimeDone(), Day.NOT_WORK_DAYS) * 24);  //вычитаем дни выходных с даты Предельного срока до даты выполнения


                        // т.к. в ожидание дни выходных включены
                        double hourTimeAppointment = format.parse(incObject.getTimeAppointment().toString().split(" ")[0] + " 20:00:00").getTime()
                                - format.parse(incObject.getTimeAppointment().toString()).getTime();
                        double hourTimeDone = format.parse(incObject.getTimeDone().toString()).getTime()
                                - format.parse(incObject.getTimeDone().toString().split(" ")[0] + " 08:00:00").getTime();

                        double testTime = dayOff(incObject.getTimeAppointment().toString(), incObject.getTimeDone().toString(), Day.WORK_DAYS) - 2;

//                        System.out.println("Расчет часов без учета ожидания = "
//                                + ((hourTimeAppointment / 3600000 + (testTime * 12) + hourTimeDone / 3600000) - (expectationTime / 3600000))
//                                + " " + incObject.getIdIcident());
//                        System.out.println("Количество дней = " + testTime
//                                + " " + incObject.getIdIcident());
//                        System.out.println("Дата выполнения - "+format.parse(incObject.getTimeDone()));
//                        System.out.println("Дата создания - " + format.parse(incObject.getTimeAppointment()));
//                        System.out.println("Время ожидания - " + expectationTime/3600000);
//                        System.out.println("Количество выходных дней - "+ dayOff(incObject.getTimeAppointment(), incObject.getTimeDone(),  Day.NOT_WORK_DAYS));
//                        System.out.println("timeOverdue " + timeOverdue);


                        double timeOverdue = //округление
                                ((hourTimeAppointment / 3600000 + (testTime * 12) + hourTimeDone / 3600000)); //- expectationTime

                        // -(expectationTime/ 3600000)
                        if (timeOverdue < (Ni / 2) * 5) { //? 2 или не 2
                            // Добавление времени с просроком
//                            System.out.println("Просрок без 5 раз");
                            SumT += timeOverdue; //
                        } else if (timeOverdue > (Ni / 2) * 5) { //? 2 или не 2
                            // timeOverdue полное время затраченное на решение инцидента с вычитом ожидания и выходных дней.
                            // ((timeOverdue - (Ni * 5)) * 2) дополнительное время за просрок в 5 раз
//                            System.out.println("Просрок в 5 раз");
                            if (iFor != 1) {
                                SumT += (timeOverdue + (timeOverdue - (Ni / 2 * 5)) * 2); //? 2 или не 2
                            } else {
                                SumT += (timeOverdue + (timeOverdue - (Ni * 5)) * 2); //? 2 или не 2
                            }
                        }
                    }
                }
            }
            if (SumT == 0) {
                NI_SUM = 1;
            } else {
                NI_SUM = ((Ni * Count) / SumT);
            }

            if (ZPi == 0) {
                SPI_SUM = 1;
            } else {
                SPI_SUM = ((double) (ZPi - ZPiprvvst) / ZPi);
            }

            System.out.println("-------------------------------------------------------------------");
            System.out.println("i/Приоритет = " + iFor);
            System.out.println("Wi = " + Wi[iFor - 1]);
            System.out.println("ZPi = " + ZPi);
            System.out.println("ZPiprvvst = " + ZPiprvvst);
            System.out.println("Ni = " + Ni);
            System.out.println("SumT = " + SumT);
            System.out.println("Count = " + Count);
            System.out.println("KPi++ " + (Wi[iFor - 1] * ((0.2 * SPI_SUM) + (0.8 * NI_SUM))));
            System.out.println("\n\n");
//            System.out.println("SPI_SUM (" + ZPi + " - " + ZPiprvvst + ") / " + ZPi + " = " + SPI_SUM);
//            System.out.println("NI_SUM (" + Ni + " * " + Count + ") / " + SumT + " = " + NI_SUM);
//            System.out.println("KPI++ " + (Wi[iFor - 1] * ((0.2 * SPI_SUM) + (0.8 * NI_SUM))) + "\n");
            KPI_ZPVVST += (Wi[iFor - 1] * ((0.2 * SPI_SUM) + (0.8 * NI_SUM)));
        }
        System.out.println("Всего ицнидентов = " + (incObjects.size()));
        return KPI_ZPVVST;
    }


    private int dayOff(String startDate, String endDate, Day day) {
        startDate = startDate.split(" ")[0];//+ " 00:00:00"
        endDate = endDate.split(" ")[0];// + " 23:59:59"
        SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd");// HH:mm:ss

        Calendar startCal = null;
        Calendar endCal = null;
        try {
            startCal = Calendar.getInstance();
            startCal.setTime(format.parse(startDate));
            endCal = Calendar.getInstance();
            endCal.setTime(format.parse(endDate));
        } catch (ParseException e) {
            e.printStackTrace();
        }
        int workDays = 0; //будни
        int notWorkDays = 0; //выходные
        //Return 0 if start and end are the same
//        if (startCal.getTimeInMillis() == endCal.getTimeInMillis()) {
//            return 0;
//        }
//        if (startCal.getTimeInMillis() > endCal.getTimeInMillis()) {
//            try {
//                startCal.setTime(format.parse(endDate));
//                endCal.setTime(format.parse(startDate));
//            } catch (ParseException e) {
//                e.printStackTrace();
//            }
//        }
        while (!startCal.after(endCal)) {
            int dayNumb = startCal.get(Calendar.DAY_OF_WEEK);
            if ((dayNumb != Calendar.SATURDAY) && (dayNumb != Calendar.SUNDAY)) {
//                System.out.println(startCal.getTime());
                workDays++;
            } else {
                notWorkDays++;
            }
            startCal.add(Calendar.DATE, 1);
        }
//        do {
//            startCal.add(Calendar.DAY_OF_MONTH, 1);
//            if (startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SATURDAY
//                    && startCal.get(Calendar.DAY_OF_WEEK) != Calendar.SUNDAY) {
//                ++workDays;
//            } else {
//                ++notWorkDays;
//            }
//        } while (startCal.getTimeInMillis() < endCal.getTimeInMillis());
        if (day == Day.WORK_DAYS) {
            return workDays;
        } else {
            return notWorkDays;
        }
    }
}

