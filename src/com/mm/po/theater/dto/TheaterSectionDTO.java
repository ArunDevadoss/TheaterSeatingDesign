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

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + availableSeats;
		result = prime * result + rowNumber;
		result = prime * result + sectionNumber;
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		TheaterSectionDTO other = (TheaterSectionDTO) obj;
		if (availableSeats != other.availableSeats)
			return false;
		if (rowNumber != other.rowNumber)
			return false;
		if (sectionNumber != other.sectionNumber)
			return false;
		return true;
	}

	@Override
	public String toString() {
		return "TheaterSectionDTO [rowNumber=" + rowNumber + ", sectionNumber=" + sectionNumber + ", availableSeats="
				+ availableSeats + "]";
	}

}
