package model;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;

public class MyDate implements Serializable {

    private int year;
    private int month;
    private int day;
    private int hour;
    private int minute;

    public MyDate(int year, int month, int day) {
        this.set(year, month, day, 0, 0);
    }

    public MyDate(int year, int month, int day, int hour, int minute) {
        this.set(year, month, day, hour, minute);
    }
    public MyDate() {
        Calendar now = GregorianCalendar.getInstance();
        this.year = now.get(Calendar.YEAR);
        this.month = now.get(Calendar.MONTH) + 1;
        this.day = now.get(Calendar.DAY_OF_MONTH);
    }

    public MyDate(Date date) {
        Calendar calendar = toCalendar(date);
        this.year = calendar.get(Calendar.YEAR);
        this.month = calendar.get(Calendar.MONTH) + 1;
        this.day = calendar.get(Calendar.DAY_OF_MONTH);
        this.hour = calendar.get(Calendar.HOUR_OF_DAY);
        this.minute = calendar.get(Calendar.MINUTE);
    }

    public int getYear() {
        return year;
    }

    public int getMonth() {
        return month;
    }

    public int getDay() {
        return day;
    }

    public int getHour() { return hour;
    }
    public int getMinute() { return minute;
    }

    public void set(int year, int month, int day, int hour, int minute) {

        this.year = year > 0 ? year : -year;

        if (month < 1)
            this.month = 1;
        else if (month > 12)
            this.month = 12;
        else
            this.month = month;

        if (day < 1)
            this.day = 1;
        else if (day > numberOfDaysInMonth())
            this.day = numberOfDaysInMonth();
        else
            this.day = day;

        if(hour > 23)
            this.hour = 23;
        if (hour < 0)
            this.hour = 0;
        else
            this.hour = hour;

        if (minute > 59)
            this.minute = 59;
        if(minute < 0)
            this.minute = 0;
        else
            this.minute = minute;
    }

    public void set(int day, String monthName, int year) {

        this.year = year > 0 ? year : -year;

        int month = convertToMonthNumber(monthName);

        if (month < 1)
            this.month = 1;
        else if (month > 12)
            this.month = 12;
        else
            this.month = month;

        if (day < 1)
            this.day = 1;
        else if (day > numberOfDaysInMonth())
            this.day = numberOfDaysInMonth();
        else
            this.day = day;
    }



    @Override
    public String toString() {
        String day = "00".substring(Integer.toString(this.day).length()) + Integer.toString(this.day);
        String month = "00".substring(Integer.toString(this.month).length()) + Integer.toString(this.month);
        String year = "0000".substring(Integer.toString(this.year).length()) + Integer.toString(this.year);

        return day + "/" + month + "/" + year + "/ " + hour + ":" + minute;
    }

    public static MyDate convertFromTimestampToMyDate(Timestamp timestamp) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(timestamp);
        return new MyDate(calendar.get(Calendar.YEAR),calendar.get(Calendar.MONTH)+1,calendar.get(Calendar.DAY_OF_MONTH), calendar.get(Calendar.HOUR_OF_DAY),calendar.get(Calendar.MINUTE));
    }
    
    public static Timestamp convertFromMyDateToTimestamp(MyDate date) {
        Calendar cal = Calendar.getInstance();
        cal.set(date.getYear(),date.getMonth()-1,date.getDay(),date.getHour(),date.getMinute(),0);
        cal.set(Calendar.MILLISECOND,000);
        return new Timestamp(cal.getTimeInMillis());
    }
    
    public boolean isLeapYear() {
        return (this.year % 4 == 0) && (this.year % 100 != 0) || (this.year % 400 == 0);
    }

    public int numberOfDaysInMonth() {
        switch (this.month) {
            case 1:
            case 3:
            case 5:
            case 7:
            case 8:
            case 10:
            case 12:
                return 31;
            case 4:
            case 6:
            case 9:
            case 11:
                return 30;
            case 2:
                if (isLeapYear())
                    return 29;
                else
                    return 29;
            default:
                return -1;
        }
    }

    public String getMonthName() {
        switch(this.month){
            case 1:
                return "January";
            case 2:
                return "February";
            case 3:
                return "March";
            case 4:
                return "April";
            case 5:
                return "May";
            case 6:
                return "June";
            case 7:
                return "July";
            case 8:
                return "August";
            case 9:
                return "September";
            case 10:
                return "October";
            case 11:
                return "November";
            case 12:
                return "December";
            default:
                return "-1";
        }
    }

    public void stepForward(int days) {
        for(int i=0;i<days;i++)
            stepForwardOneDay();
    }

    public void stepForwardOneDay() {
        if((this.day + 1) > numberOfDaysInMonth()) {
            if((this.month + 1) > 12) {
                this.day = 1;
                this.month = 1;
                this.year++;
            }else {
                this.day = 1;
                this.month++;
            }
        }else {
            this.day++;
        }
    }

    public static int convertToMonthNumber(String monthName) {
        switch(monthName){
            case "January":
                return 1;
            case "February":
                return 2;
            case "March":
                return 3;
            case "April":
                return 4;
            case "May":
                return 5;
            case "June":
                return 6;
            case "July":
                return 7;
            case "August":
                return 8;
            case "September":
                return 9;
            case "October":
                return 10;
            case "November":
                return 11;
            case "December":
                return 12;
            default:
                return -1;
        }
    }

    @Override
    public boolean equals(Object obj) {
        if(obj instanceof MyDate) {
            MyDate date2 = (MyDate) obj;
            return this.day == date2.day && this.month == date2.month && this.year == date2.year && this.hour == date2.hour && this.minute == date2.minute;
        }else return false;
    }

    public MyDate copy() {
        return new MyDate(this.year, this.month, this.day, this.hour, this.minute);
    }

    public boolean isBefore(MyDate other) {
        if (other.year < this.year)
            return false;
        else {
            if (other.month < this.month)
                return false;
            else {
                return other.month >= this.month || other.day >= this.day;
            }
        }
    }

    public int yearsBetween(MyDate other) {
        int years = 0;
        MyDate copy;
        if(this.isBefore(other))
            copy = this.copy();
        else {
            copy = other.copy();
            other = this;
        }

        while(copy.isBefore(other)) {
            copy.stepForward(365);
            if(copy.isBefore(other))
                years++;
        }
        return years;
    }

    public int daysBetween(MyDate other) {
        int days = 0;
        MyDate copy;

        if(this.isBefore(other))
            copy = this.copy();
        else {
            copy = other.copy();
            other = this;
        }

        while(copy.isBefore(other)) {
            copy.stepForwardOneDay();
            if(copy.isBefore(other))
                days++;
        }

        return days;
    }

    public static MyDate now() {
        return new MyDate();
    }

    public static Calendar toCalendar(Date date){
        Calendar cal = Calendar.getInstance();
        cal.setTime(date);
        return cal;
    }

}
