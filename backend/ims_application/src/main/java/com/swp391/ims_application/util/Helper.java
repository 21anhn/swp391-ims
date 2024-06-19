package com.swp391.ims_application.util;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Random;

public class Helper {
    private static final String CHARACTERS = "ABCDEFGHIJKLMNOPQRSTUVWXYZabcdefghijklmnopqrstuvwxyz0123456789";

    public static String generatePassword() {
        Random random = new Random();
        StringBuilder password = new StringBuilder();

        for (int i = 0; i < 6; i++) {
            int randomIndex = random.nextInt(CHARACTERS.length());
            char randomChar = CHARACTERS.charAt(randomIndex);
            password.append(randomChar);
        }

        return password.toString();
    }

    public static Date parseStringToDate(String date, String time, String format) throws ParseException {
        String dateTimeString = date + " " + date;
        SimpleDateFormat sdf = new SimpleDateFormat(format);
        return sdf.parse(dateTimeString);
    }

    public static String[] splitDate(Date d, String format) {
        return new String[] {"123"};
    }
}
