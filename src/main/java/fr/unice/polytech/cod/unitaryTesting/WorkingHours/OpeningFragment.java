package fr.unice.polytech.cod.unitaryTesting.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class OpeningFragment {

    private DayOfWeek day;
    private LocalTime opening;
    private LocalTime closing;

    public OpeningFragment(DayOfWeek day, LocalTime opening, LocalTime closing) throws RuntimeException {
        this.day = day;

        if(opening.isAfter(closing)){
            throw new RuntimeException("Opening time must be after closing time");
        }
        this.opening = opening;
        this.closing = closing;
    }

    DayOfWeek getDay() {
        return day;
    }

    LocalTime getOpening() {
        return opening;
    }

    LocalTime getClosing() {
        return closing;
    }
}