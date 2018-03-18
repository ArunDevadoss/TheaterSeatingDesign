package com.mm.po.theater.dto;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterRequestDTO {
	/**
	 * 
	 */
	private String personName;
	/**
	 * 
	 */
	private int noOfTickets;
	/**
	 * 
	 */
	private boolean requestCompleted;

	/**
	 * 
	 * @return
	 */
	public String getPersonName() {
		return personName;
	}

	/**
	 * 
	 * @param personName
	 */
	public void setPersonName(String personName) {
		this.personName = personName;
	}

	/**
	 * 
	 * @return
	 */
	public int getNoOfTickets() {
		return noOfTickets;
	}

	/**
	 * 
	 * @param noOfTickets
	 */
	public void setNoOfTickets(int noOfTickets) {
		this.noOfTickets = noOfTickets;
	}

	/**
	 * 
	 * @return
	 */
	public boolean isRequestCompleted() {
		return requestCompleted;
	}

	public void setRequestCompleted(boolean requestCompleted) {
		this.requestCompleted = requestCompleted;
	}

}
