package com.mm.po.theater.dto;

/**
 * 
 * @author ArunDevadoss
 *
 */
public class TheaterSectionDTO {

	/**
	 * 
	 */
	private int rowNumber;
	/**
	 * 
	 */
	private int sectionNumber;

	/**
	 * 
	 */
	private int availableSeats;

	/**
	 * 
	 * @return
	 */
	public int getRowNumber() {
		return rowNumber;
	}

	/**
	 * 
	 * @param rowNumber
	 */
	public void setRowNumber(int rowNumber) {
		this.rowNumber = rowNumber;
	}

	/**
	 * 
	 * @return
	 */
	public int getSectionNumber() {
		return sectionNumber;
	}

	/**
	 * 
	 * @param sectionNumber
	 */
	public void setSectionNumber(int sectionNumber) {
		this.sectionNumber = sectionNumber;
	}

	/**
	 * 
	 * @return
	 */
	public int getAvailableSeats() {
		return availableSeats;
	}

	/**
	 * 
	 * @param availableSeats
	 */
	public void setAvailableSeats(int availableSeats) {
		this.availableSeats = availableSeats;
	}

}
