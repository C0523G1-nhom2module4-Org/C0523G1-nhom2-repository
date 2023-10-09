package com.codegym.casestudy.utilities;

import java.text.SimpleDateFormat;
import java.util.Date;

public class DayTimeFormat {
    public static Date dateTimeFormat(String dateString) {
        Date date = null;
        try {
            date = new SimpleDateFormat("dd/MM/yyyy").parse(dateString);
        } catch (Exception e) {
            System.out.println("Error while parse string -> date: " + e.getMessage());
        }
        return date;
    }
}
