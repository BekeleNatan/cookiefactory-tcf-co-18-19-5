package fr.unice.polytech.cod.store.workinghours;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingHours {
    private List<OpeningFragment> openingFragments;

    public WorkingHours() {
        openingFragments = new ArrayList<>();
    }

    // ok
    private boolean inBetween(LocalTime actual, LocalTime from, LocalTime to) {
        return !actual.isBefore(from) && actual.isBefore(to);  // inclusive beginning but exclusive ending (half-open)
    }

    // ok
    public boolean addOpeningFragement(DayOfWeek day, LocalTime from, LocalTime to) throws RuntimeException {
        OpeningFragment openingFragment = new OpeningFragment(day, from, to);
        for (OpeningFragment of : this.openingFragments) {
            if (of.getDay().equals(openingFragment.getDay())) {
                if (inBetween(openingFragment.getOpening(), of.getOpening(), of.getClosing())) {
                    throw new RuntimeException("You can't add an Opening Fragment that overlaps another one");
                }
                this.openingFragments.add(openingFragment);
                return true;
            }
        }
        this.openingFragments.add(openingFragment);
        return true;
    }

    // ok
    public boolean deleteOpeningFragement(DayOfWeek day, LocalTime from, LocalTime to) {
        for (OpeningFragment of : this.openingFragments) {
            if (of.getDay().equals(day) && of.getOpening().equals(from) && of.getClosing().equals(to)) {
                this.openingFragments.remove(of);
                return true;
            }
        }
        return false;
    }

    // ok
    public boolean isOpenOn(DayOfWeek day, LocalTime at) {
        for (OpeningFragment of : this.openingFragments) {
            if (day.equals(of.getDay()) && (inBetween(at, of.getOpening(), of.getClosing()))) {
                return true;
            }
        }
        return false;
    }

    // todo WorkingHours : adding restrictedHoursDay
    public boolean addRestrictedHoursDay(DayOfWeek aDay, OpeningFragment aOpeningFragment) {
        throw new UnsupportedOperationException();
    }

    // todo WorkingHours : delete restrictedHoursDay
    public boolean deleteRestrictedHoursDay(DayOfWeek aDay, OpeningFragment aOpeningFragment) {
        throw new UnsupportedOperationException();
    }
}