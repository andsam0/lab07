package it.unibo.nestedenum;

import java.util.Comparator;
import java.util.Locale;
import java.util.Objects;

/**
 * Implementation of {@link MonthSorter}.
 */
public final class MonthSorterNested implements MonthSorter {
    
    public enum Month{
        JANUARY(31),
        FEBRUARY(28),
        MARCH(31),
        APRIL(30),
        MAY(31),
        JUNE(30),
        JULY(31),
        AUGUST(31),
        SEPTEMBER(30),
        OCTOBER(31),
        NOVEMBER(30),
        DECEMBER(31);

        private int monthDays;

        private Month(int days){
            this.monthDays = days;
        }

        private int getDays(){
            return this.monthDays;
        }

        public static Month fromString(String passedMonth){
            Month matchedMonth = null;
            for ( Month currentMonth : Month.values()) {
                if(currentMonth.toString().contains(passedMonth.toUpperCase())){
                    if(matchedMonth != null){
                        throw new IllegalArgumentException("Ambiguous argument passed: " + passedMonth + " matches with both " 
                        + matchedMonth + " and " + currentMonth + "\n");
                    }
                    matchedMonth = currentMonth;
                }                
            }
            if(matchedMonth == null){
                throw new IllegalArgumentException("Your passed argument doesn't match any month\n");
            }
            return matchedMonth;
        }
    }
    @Override
    public Comparator<String> sortByDays() {
        return new Comparator<String>(){
            @Override
            public int compare(String arg0, String arg1) {
                return Integer.compare(Month.fromString(arg0).getDays(), Month.fromString(arg1).getDays());
            }
        };
    }

    @Override
    public Comparator<String> sortByOrder() {
        return new Comparator<String>(){
            @Override
            public int compare(String arg0, String arg1) {
                return Integer.compare(Month.fromString(arg0).ordinal(), Month.fromString(arg1).ordinal());
            }
        };    
    }
}
