package com.mm.po.theater.service;

import java.util.Scanner;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterInputService {

	/**
	 * 
	 */
	public void handleTheaterInput() throws NumberFormatException {

		TheaterSeatingService theaterSeatingService;
		StringBuilder layout = new StringBuilder();
		StringBuilder ticketRequest = new StringBuilder();

		System.out.println("Enter Theater Layout and Ticket requests and then enter 'end'.\n");

		getTheaterLayoutRequestInfo(layout, ticketRequest);

		try {
			theaterSeatingService = new TheaterSeatingService();
			theaterSeatingService.processTicketRequests(theaterSeatingService.transformTheaterLayout(layout.toString()),
					theaterSeatingService.transformTheaterRequests(ticketRequest.toString()));

		} catch (NumberFormatException nfe) {
			System.err.println("Number FormatException" + nfe.getMessage());
			throw new NumberFormatException(nfe.getMessage());

		}

	}

	/**
	 * 
	 * @param theaterLayout
	 * @param ticketRequest
	 */
	private void getTheaterLayoutRequestInfo(final StringBuilder theaterLayout, final StringBuilder ticketRequest) {

		String line;
		Scanner input = new Scanner(System.in);
		boolean isLayoutCompleted = false;
		while ((line = input.nextLine()) != null && !line.equals("end")) {

			if (line.length() != 0 && !isLayoutCompleted) {
				theaterLayout.append(line + System.lineSeparator());
			} else {

				if (line.length() != 0 && theaterLayout.length() != 0) {
					ticketRequest.append(line + System.lineSeparator());
				} else if (theaterLayout.length() != 0) {
					isLayoutCompleted = true;
				} else {

				}
			}

		}

		System.out.println("Please find the entered theaterLayout information -> \n" + theaterLayout.toString());
		System.out.println("Please find the entered ticketRequest information -> \n" + ticketRequest.toString());
		input.close();
	}

}
