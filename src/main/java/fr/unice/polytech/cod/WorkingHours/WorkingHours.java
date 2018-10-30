package fr.unice.polytech.cod.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class WorkingHours {

	List<OpeningFragment> openingFragments = new ArrayList<>();


	// constructor without initial list
	WorkingHours() {
	}

    // ok (util function)
	private boolean inBetween(LocalTime actual, LocalTime from, LocalTime to){
        return !actual.isBefore(from) && actual.isBefore(to);  // inclusive beginning but exclusive ending (half-open)
    }

    // ok
	boolean addOpeningFragement(OpeningFragment openingFragment) {
	    for(OpeningFragment of : this.openingFragments){
	        if(of.getDay().equals(openingFragment.getDay())){
	            if(inBetween(openingFragment.getOpening(), of.getOpening(), of.getClosing())){
	                return false;
                }
                this.openingFragments.add(openingFragment);
                return true;
            }
        }
        this.openingFragments.add(openingFragment);
        return true;
	}

	// ok
	boolean deleteOpeningFragement(DayOfWeek day, LocalTime from, LocalTime to) {
        for(OpeningFragment of : this.openingFragments){
            if(of.getDay().equals(day) && of.getOpening().equals(from) && of.getClosing().equals(to)){
                this.openingFragments.remove(of);
                return true;
            }
        }
        return false;
	}

	// ok
	boolean isOpenOn(DayOfWeek day, LocalTime at) {
	    for (OpeningFragment of : this.openingFragments){
	        if(day.equals(of.getDay()) && (inBetween(at, of.getOpening(), of.getClosing()))){
	            return true;
            }
        }
		return false;
	}

}