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
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + noOfTickets;
		result = prime * result + ((personName == null) ? 0 : personName.hashCode());
		result = prime * result + (requestCompleted ? 1231 : 1237);
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
		TheaterRequestDTO other = (TheaterRequestDTO) obj;
		if (noOfTickets != other.noOfTickets)
			return false;
		if (personName == null) {
			if (other.personName != null)
				return false;
		} else if (!personName.equals(other.personName))
			return false;
		if (requestCompleted != other.requestCompleted)
			return false;
		return true;
	}

}
