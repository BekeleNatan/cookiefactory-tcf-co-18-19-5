package fr.unice.polytech.cod.workinghours;

import fr.unice.polytech.cod.store.Store;

import java.time.DayOfWeek;
import java.time.LocalTime;
import java.util.ArrayList;

public class WorkingHours {
	public ArrayList<OpeningFragment> openingFragments = new ArrayList<OpeningFragment>();

	public boolean isOpenOn(DayOfWeek aDay, LocalTime aAt) {
		throw new UnsupportedOperationException();
	}

	public boolean addOpeningTime(DayOfWeek aDay, LocalTime aFrom, LocalTime aTo) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteOpeningTime(DayOfWeek aDay, LocalTime aFrom, LocalTime aTo) {
		throw new UnsupportedOperationException();
	}

	public boolean addRestrictedHoursDay(DayOfWeek aDay, OpeningFragment aOpeningFragment) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteRestrictedHoursDay(DayOfWeek aDay, OpeningFragment aOpeningFragment) {
		throw new UnsupportedOperationException();
	}
}