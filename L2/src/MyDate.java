
public class MyDate{
    int day;
    int month;
    int year;
    /**
     * Constructs a {@code MyDate} object.
     *
     * @param day     the day of the date
     * @param month   the month of the date
     * @param year    the year of the date
     */
    public MyDate(int day,int month,int year) throws IllegalArgumentException {
        if (year < 0) {
            throw new IllegalArgumentException("Year not valid");
        }
        if (month < 0 || month > 12) {
            throw new IllegalArgumentException("Month not valid");
        }
        if (month == 1 || month == 3 || month == 5 || month == 7 || month == 8 || month == 10 || month == 12) {
            if (day < 0 || day > 31) {
                throw new IllegalArgumentException("Day not valid");
            }
        } else if (month == 4 || month == 6 || month == 9 || month == 11) {
            if (day < 0 || day > 30) {
                throw new IllegalArgumentException("Day not valid");
            }
        } else if (month == 2) {
            if (year % 4 == 0 && (year % 100 != 0 || (year % 100 == 0 && year % 400 == 0))) {
                if (day < 0 || day > 29) {
                    throw new IllegalArgumentException("Day not valid");
                }
            } else {
                if (day < 0 || day > 28) {
                    throw new IllegalArgumentException("Day not valid");
                }
            }
        } else {
            throw new IllegalArgumentException("Month not valid");
        }
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Helper function to get how many days in this month
     *
     * @return  the maximum number of days in current month
     */
    private int daysInMonth() {
        if (this.month == 1 || this.month == 3 || this.month == 5 || this.month == 7 || this.month == 8 || this.month == 10 || this.month == 12) {
            return 31;
        } else if (this.month == 4 || this.month == 6 || this.month == 9 || this.month == 11) {
            return 30;
        } else if (this.year % 4 == 0 && (this.year % 100 != 0 || (this.year % 100 == 0 && this.year % 400 == 0))) {
            return 29;
        } else {
            return 28;
        }
    }

    /**
     * Advance the date by the given days
     *
     * @param days     the number of days need to be advanced
     */
    public void advance(int days) {
        if (days > 0) {
            if (this.day + days <= this.daysInMonth()){
                this.day += days;
            } else {
                days -= this.daysInMonth() - this.day + 1;
                this.day = 1;
                if (this.month == 12) {
                    this.year += 1;
                    this.month = 1;
                } else {
                    this.month += 1;
                }
                this.advance(days);
            }
        } else {
            if (this.day + days > 0){
                this.day += days;
            } else if (this.year == 0 && this.month == 1) {
                this.day = 1;
            } else {
                days += this.day - 1;
                this.day = 1;
                if (this.month == 1) {
                    this.year -= 1;
                    this.month = 12;
                } else {
                    this.month -= 1;
                }
                days += this.daysInMonth();
                this.advance(days);
            }
        }
    }

    /**
     * Get the string that reports this date in the format YYYY-MM-DD
     *
     * @return  the date string
     */
    public String toString() {
        String dayStr = String.valueOf(this.day);
        String monthStr = String.valueOf(this.month);
        String yearStr = String.valueOf(this.year);
        while (dayStr.length() < 4) {
            dayStr = "0"+dayStr;
        }
        while (monthStr.length() < 2) {
            monthStr = "0"+monthStr;
        }
        while (yearStr.length() < 2) {
            yearStr = "0"+yearStr;
        }
        return yearStr + "-" + monthStr + "-" + dayStr;
    }
}
