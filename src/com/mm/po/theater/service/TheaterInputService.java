package com.mm.po.theater.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mm.po.theater.constant.TheaterConstants;
import com.mm.po.theater.dto.TheaterDTO;
import com.mm.po.theater.dto.TheaterRequestDTO;
import com.mm.po.theater.dto.TheaterSectionDTO;
import com.mm.po.theater.exception.InvalidTheaterLayoutException;
import com.mm.po.theater.exception.InvalidTheaterRequestException;
import com.mm.po.theater.exception.InvalidTicketNumberException;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterInputService {

	/**
	 * 
	 * @param input
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	public void handleTheaterInput(final Scanner input)
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		TheaterRequestProcessService theaterSeatingService;

		theaterSeatingService = new TheaterRequestProcessService();
		theaterSeatingService.processTicketRequests(getTheaterLayoutRequestInfo(input));

	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	private TheaterDTO getTheaterLayoutRequestInfo(final Scanner input)
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String line;
		boolean isLayoutCompleted = false;

		final List<TheaterRequestDTO> theaterRequests = new ArrayList<>();
		final List<TheaterSectionDTO> theaterSections = new ArrayList<>();
		final AtomicInteger rowCount = new AtomicInteger(0);
		final TheaterDTO theater = new TheaterDTO();

		while ((line = input.nextLine()) != null && !line.equals(TheaterConstants.END_OF_REQUEST)) {

			if (line.length() != 0 && !isLayoutCompleted) {

				transformTheaterLayout(line, theaterSections, rowCount, theater);

			} else {

				if (line.length() != 0 && isLayoutCompleted) {

					final List<String> requests = Stream.of(line.split(TheaterConstants.BLACK_SPACE))
							.collect(Collectors.toList());
					if (null != requests && requests.size() == 2) {
						transformTheaterRequests(requests, theaterRequests);
					} else {
						throw new InvalidTheaterRequestException(TheaterConstants.INVALID_THEATER_REQUEST);
					}

				} else {
					isLayoutCompleted = true;
				}
			}

		}

		theater.setTheaterSections(theaterSections);
		theater.setTheaterRequests(theaterRequests);
		input.close();

		return theater;
	}

	/**
	 * Transform the given raw layout into TheaterLayout with Sections consists of
	 * rowNumber, sectionNumber & availableSeats
	 * 
	 * @param requests
	 * @param theaterRequests
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTicketNumberException
	 */
	private void transformTheaterRequests(final List<String> requests, final List<TheaterRequestDTO> theaterRequests)
			throws InvalidTheaterRequestException, InvalidTicketNumberException {

		try {

			theaterRequests.add(new TheaterRequestDTO(requests.get(0).trim(), Integer.valueOf(requests.get(1).trim())));

		} catch (NumberFormatException nfe) {
			throw new InvalidTicketNumberException("Please enter valid ticket request, tickets should be number");
		}
	}

	/**
	 * Transform raw Ticket Request to List<TheaterRequestDTO>
	 * 
	 * @param line
	 * @param theaterSections
	 * @param rowCount
	 * @param theater
	 * @throws InvalidTheaterLayoutException
	 */
	private void transformTheaterLayout(final String line, final List<TheaterSectionDTO> theaterSections,
			final AtomicInteger rowCount, final TheaterDTO theater)
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException {
		final AtomicInteger sectionCount = new AtomicInteger(0);

		final List<String> sections = Stream.of(line.split(TheaterConstants.BLACK_SPACE)).collect(Collectors.toList());

		for (final String section : sections) {

			try {
				theater.setTotalAvailableCapacity(
						theater.getTotalAvailableCapacity() + Integer.valueOf(section.trim()));
				theaterSections.add(new TheaterSectionDTO(rowCount.get() + 1, sectionCount.get() + 1,
						Integer.valueOf(section.trim())));
			} catch (NumberFormatException nfe) {
				throw new InvalidTheaterLayoutException("Please enter valid theater layout, accepts only numbers");
			}
			sectionCount.getAndIncrement();
		}
		rowCount.getAndIncrement();
	}

}
