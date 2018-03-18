package com.mm.po.theater;

import com.mm.po.theater.service.TheaterInputService;

/**
 * 
 * @author transloadallocation
 *
 */

public class TheaterSeatingApplication {

	public static void main(String[] args) {

		TheaterInputService theaterInputService = new TheaterInputService();
		theaterInputService.handleTheaterInput();

	}
}
