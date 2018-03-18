package com.mm.po.theater;

import java.util.Scanner;

import com.mm.po.theater.exception.InvalidTheaterLayoutException;
import com.mm.po.theater.exception.InvalidTheaterRequestException;
import com.mm.po.theater.exception.InvalidTicketNumberException;
import com.mm.po.theater.service.TheaterInputService;

/**
 * 
 * @author Arun Devadoss
 *
 */

public class TheaterSeatingApplication {

	public static void main(String[] args)
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		System.out.println("Enter Theater Layout and Ticket requests and then enter 'end'.\n");
		Scanner input = new Scanner(System.in);
		TheaterInputService theaterInputService = new TheaterInputService();
		theaterInputService.handleTheaterInput(input);

	}
}
