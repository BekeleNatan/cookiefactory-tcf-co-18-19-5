package fr.unice.polytech.cod.WorkingHours;

import java.time.DayOfWeek;
import java.time.LocalTime;

public class WorkingHours {

	/**
	 * 
	 * @param day
	 * @param from
	 * @param to
	 */
	public OpeningTime getOpeningTime(DayOfWeek day, LocalTime from, LocalTime to) {
		// TODO - implement WorkingHour.getOpeningTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param day
	 * @param from
	 * @param to
	 */
	public boolean addOpeningTime(DayOfWeek day, LocalTime from, LocalTime to) {
		// TODO - implement WorkingHour.addOpeningTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param openingTime
	 */
	public boolean deleteOpeningTime(OpeningTime openingTime) {
		// TODO - implement WorkingHour.deleteOpeningTime
		throw new UnsupportedOperationException();
	}

	/**
	 * 
	 * @param day
	 * @param at
	 */
	public boolean isOpenOn(DayOfWeek day, LocalTime at) {
		// TODO - implement WorkingHour.isOpenOn
		throw new UnsupportedOperationException();
	}

}