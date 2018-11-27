package fr.unice.polytech.cod.workinghours;

import java.util.ArrayList;

public class WorkingHours {
	public Store _opens_on;
	public ArrayList<OpeningFragment> _forms = new ArrayList<OpeningFragment>();

	public boolean isOpenOn(DayOfWeek aDay, LocalTime aAt) {
		throw new UnsupportedOperationException();
	}

	public boolean addOpeningTime(DayOfWeek aDay, LocalTime aFrom, LocalTime aTo) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteOpeningTime(DayOfWeek aDay, LocalTime aFrom, LocalTime aTo) {
		throw new UnsupportedOperationException();
	}

	public boolean addRestrictedHoursDay(Day aDay, OpeningFragment aOpeningFragment) {
		throw new UnsupportedOperationException();
	}

	public boolean deleteRestrictedHoursDay(Day aDay, OpeningFragment aOpeningFragment) {
		throw new UnsupportedOperationException();
	}
}