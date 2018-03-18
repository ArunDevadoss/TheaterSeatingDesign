package com.mm.po.theater.dto;

import java.util.List;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterDTO {

	/**
	 * 
	 */
	private int totalAvailableCapacity;

	/**
	 * 
	 */
	private List<TheaterRequestDTO> theaterRequests;
	/**
	 * 
	 */
	private List<TheaterSectionDTO> theaterSections;

	/**
	 * 
	 * @return
	 */
	public List<TheaterRequestDTO> getTheaterRequests() {
		return theaterRequests;
	}

	/**
	 * 
	 * @param theaterRequests
	 */

	public void setTheaterRequests(List<TheaterRequestDTO> theaterRequests) {
		this.theaterRequests = theaterRequests;
	}

	/**
	 * 
	 * @return
	 */
	public List<TheaterSectionDTO> getTheaterSections() {
		return theaterSections;
	}

	/**
	 * 
	 * @param theaterSections
	 */
	public void setTheaterSections(List<TheaterSectionDTO> theaterSections) {
		this.theaterSections = theaterSections;
	}

	/**
	 * 
	 * @return
	 */
	public int getTotalAvailableCapacity() {
		return totalAvailableCapacity;
	}

	/**
	 * 
	 * @param totalAvailableCapacity
	 */
	public void setTotalAvailableCapacity(int totalAvailableCapacity) {
		this.totalAvailableCapacity = totalAvailableCapacity;
	}

}
