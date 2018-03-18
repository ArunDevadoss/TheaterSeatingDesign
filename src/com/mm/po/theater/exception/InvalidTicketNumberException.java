package com.mm.po.theater.exception;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class InvalidTicketNumberException extends Exception {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	public InvalidTicketNumberException(String message) {

		super(message);
	}

}
