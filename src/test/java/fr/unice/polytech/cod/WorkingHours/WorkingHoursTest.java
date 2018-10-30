package fr.unice.polytech.cod.WorkingHours;

import org.junit.Before;
import org.junit.Test;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

import static org.junit.Assert.*;

public class WorkingHoursTest {

    List<OpeningFragment> openingFragments = new ArrayList<>();
    WorkingHours workingHours = new WorkingHours();

    @Before
    public void initialization() throws Exception {
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.MONDAY, LocalTime.of(9,00), LocalTime.of(12,0)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.TUESDAY, LocalTime.of(9,00), LocalTime.of(12,0)));
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.WEDNESDAY, LocalTime.of(9,00), LocalTime.of(12,0)));

    }

    @Test
    public void isOpenOn()  {
        // monday it's open, from 9:00 to 12:00
        assertTrue(workingHours.isOpenOn(DayOfWeek.MONDAY, LocalTime.of(10, 10)));
        assertFalse(workingHours.isOpenOn(DayOfWeek.MONDAY, LocalTime.of(19, 10)));

        // friday it's close
        assertFalse(workingHours.isOpenOn(DayOfWeek.FRIDAY, LocalTime.of(10, 10)));
        assertFalse(workingHours.isOpenOn(DayOfWeek.FRIDAY, LocalTime.of(19, 10)));
    }

    @Test
    public void addOpeningFragment() {
        // we want to add new opening fragment on friday
        OpeningFragment newOpeningFragemnt = new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(9,0), LocalTime.of(12, 00));
        assertTrue(workingHours.addOpeningFragement(newOpeningFragemnt));

        // the manager adds (by mistake) a fragment that overlaps another openingFragment
        OpeningFragment anotherOpeningFragemnt = new OpeningFragment(DayOfWeek.FRIDAY, LocalTime.of(11,0), LocalTime.of(18, 0));
        assertFalse(workingHours.addOpeningFragement(newOpeningFragemnt));

        // the manager adds two fragments to get one continous fragement (to test half inclusive)
        OpeningFragment openingFragmentOne = new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(9,0), LocalTime.of(12, 0));
        OpeningFragment openingFragmentTwo = new OpeningFragment(DayOfWeek.SATURDAY, LocalTime.of(12,0), LocalTime.of(18, 0));


        assertTrue(workingHours.addOpeningFragement(openingFragmentOne));
        assertTrue(workingHours.addOpeningFragement(openingFragmentTwo));
        assertTrue(workingHours.isOpenOn(DayOfWeek.SATURDAY, LocalTime.of(12, 00)));
    }

    @Test
    public void deleteOpeningFragment() {
        // the manager do not work on monday morning anymore
        assertTrue(workingHours.isOpenOn(DayOfWeek.MONDAY, LocalTime.of(9,0))); // check before deleting
        assertTrue(workingHours.deleteOpeningFragement(DayOfWeek.MONDAY, LocalTime.of(9,0), LocalTime.of(12,00))); // deleting
        assertFalse(workingHours.isOpenOn(DayOfWeek.MONDAY, LocalTime.of(9,0))); // check after deleting

        // the manager will try to delete a fragment that don't exist (mistake on day)
        assertFalse(workingHours.isOpenOn(DayOfWeek.THURSDAY, LocalTime.of(9,0))); // check before deleting
        assertFalse(workingHours.deleteOpeningFragement(DayOfWeek.THURSDAY, LocalTime.of(9,0), LocalTime.of(12,00))); // deleting
        assertFalse(workingHours.isOpenOn(DayOfWeek.THURSDAY, LocalTime.of(9,0))); // check after deleting

        // the manager will try to delete a fragment that don't exist (mistake on hour)
        assertTrue(workingHours.isOpenOn(DayOfWeek.TUESDAY, LocalTime.of(9,0))); // check before deleting
        assertFalse(workingHours.deleteOpeningFragement(DayOfWeek.TUESDAY, LocalTime.of(9,0), LocalTime.of(12,12))); // deleting
        assertTrue(workingHours.isOpenOn(DayOfWeek.TUESDAY, LocalTime.of(9,0))); // check after deleting
    }

    // the manager put by mistake the opening hour at 19:00 and closing on 12:00 (he wanted to put 9:00) an exception is raised
    @Test(expected=IllegalArgumentException.class)
    public void openingAfterClosingException() {
        workingHours.addOpeningFragement(new OpeningFragment(DayOfWeek.SUNDAY, LocalTime.of(19,00), LocalTime.of(12,0)));
    }
}