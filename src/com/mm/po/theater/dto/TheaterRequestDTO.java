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
	 */
	public TheaterRequestDTO() {

	}

	/**
	 * 
	 * @param personName
	 * @param noOfTickets
	 */
	public TheaterRequestDTO(String personName, int noOfTickets) {
		this.personName = personName;
		this.noOfTickets = noOfTickets;
	}

	/**
	 * 
	 * @return
	 */
	public String getName() {
		return personName;
	}

	/**
	 * 
	 * @param name
	 */
	public void setName(String name) {
		this.personName = name;
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

	/**
	 * 
	 * @param requestCompleted
	 */
	public void setRequestCompleted(boolean requestCompleted) {
		this.requestCompleted = requestCompleted;
	}

	@Override
	public String toString() {
		return "TheaterRequest [personName=" + personName + ", noOfTickets=" + noOfTickets + ", requestCompleted="
				+ requestCompleted + "]";
	}

}
