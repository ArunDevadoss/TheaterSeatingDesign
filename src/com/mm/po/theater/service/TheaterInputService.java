package com.mm.po.theater.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import com.mm.po.theater.constant.TheaterConstants;
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

	private List<TheaterRequestDTO> theaterRequests = new ArrayList<>();
	private List<TheaterSectionDTO> theaterSections = new ArrayList<>();

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
		getTheaterLayoutRequestInfo(input);
		if (null != theaterRequests && theaterRequests.size() > 0 && null != theaterSections
				&& theaterSections.size() > 0) {
			theaterSeatingService.processTicketRequests(theaterRequests, theaterSections);
		} else {
			System.out.println(TheaterConstants.UNABLE_TO_PROCESS);
		}

	}

	/**
	 * 
	 * @param input
	 * @return
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	private void getTheaterLayoutRequestInfo(final Scanner input)
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String line;
		boolean isLayoutCompleted = false;

		final AtomicInteger rowCount = new AtomicInteger(0);

		while ((line = input.nextLine()) != null && !line.equals(TheaterConstants.END_OF_REQUEST)) {

			if (line.length() != 0 && !isLayoutCompleted) {

				transformTheaterLayout(line, theaterSections, rowCount);

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

		input.close();

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
		TheaterRequestDTO theaterRequest;
		try {
			theaterRequest = new TheaterRequestDTO();
			theaterRequest.setPersonName(requests.get(0).trim());
			theaterRequest.setNoOfTickets(Integer.valueOf(requests.get(1).trim()));
			theaterRequests.add(theaterRequest);

		} catch (NumberFormatException nfe) {
			throw new InvalidTicketNumberException(TheaterConstants.INVALID_TICKET_NUMBER);
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
			final AtomicInteger rowCount) throws InvalidTheaterRequestException, InvalidTheaterLayoutException {
		final AtomicInteger sectionCount = new AtomicInteger(0);

		final List<String> sections = Stream.of(line.split(TheaterConstants.BLACK_SPACE)).collect(Collectors.toList());

		for (final String section : sections) {
			TheaterSectionDTO theaterSection;

			try {
				theaterSection = new TheaterSectionDTO();

				theaterSection.setRowNumber(rowCount.get() + 1);
				theaterSection.setSectionNumber(sectionCount.get() + 1);
				theaterSection.setAvailableSeats(Integer.valueOf(section.trim()));
				theaterSections.add(theaterSection);
			} catch (NumberFormatException nfe) {
				throw new InvalidTheaterLayoutException(TheaterConstants.INVALID_THEATER_LAYOUT);
			}
			sectionCount.getAndIncrement();
		}
		rowCount.getAndIncrement();
	}

}
