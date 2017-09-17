package com.gajdulewicz.intprep;

import java.time.DateTimeException;
import java.time.LocalDate;
import java.util.function.Consumer;

public class DateIterator {

    public static void iterate(Date from, Date to, Consumer<Date> c) {
        Date curr = from;
        while (curr.isBefore(to)) {
            c.accept(curr);
            curr = curr.next();
        }
    }

    static boolean isValid(Date d) {
        try {
            final LocalDate ld = LocalDate.of(d.year, d.month, d.day);
            return ld.getYear() != 0;
        } catch (DateTimeException dte) {
            return false;
        }
    }

    public static class Date {
        public final int year;
        public final int month;
        public final int day;

        public Date(int year, int month, int day) throws DateTimeException {
            this.year = year;
            this.month = month;
            this.day = day;
            if (!isValid(this)) {
                throw new DateTimeException("");
            }
        }

        public boolean isBefore(Date other) {
            if (this.year < other.year) return true;
            if (this.year > other.year) return false;
            if (this.month < other.month) return true;
            if (this.month > other.month) return false;
            if (this.day < other.day) return true;
            if (this.day > other.day) return false;
            return false;
        }


        public Date next() {
            try {
                return new Date(year, month, day + 1);
            } catch (DateTimeException y) {
                try {
                    return new Date(year, month + 1, 1);
                } catch (DateTimeException m) {
                    try {
                        return new Date(year + 1, 1, 1);
                    } catch (DateTimeException dte) {
                        return new Date(1, 1, 1);
                    }
                }
            }
        }

        @Override
        public boolean equals(Object o) {
            if (this == o) return true;
            if (o == null || getClass() != o.getClass()) return false;

            Date date = (Date) o;

            if (year != date.year) return false;
            if (month != date.month) return false;
            return day == date.day;
        }

        @Override
        public int hashCode() {
            int result = year;
            result = 31 * result + month;
            result = 31 * result + day;
            return result;
        }
    }
}
