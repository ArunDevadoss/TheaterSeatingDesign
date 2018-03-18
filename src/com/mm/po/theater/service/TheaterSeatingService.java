package com.mm.po.theater.service;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import com.mm.po.theater.constant.TheaterConstants;
import com.mm.po.theater.dto.TheaterLayoutDTO;
import com.mm.po.theater.dto.TheaterRequestDTO;
import com.mm.po.theater.dto.TheaterSectionDTO;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterSeatingService {

	/**
	 * Transform the given raw layout into TheaterLayout with Sections consists of
	 * rowNumber, sectionNumber & availableSeats
	 * 
	 * @param rawLayout
	 * @return
	 * @throws NumberFormatException
	 */
	public TheaterLayoutDTO transformTheaterLayout(final String rawLayout) throws NumberFormatException {

		final AtomicInteger rowCount = new AtomicInteger(0);

		final TheaterLayoutDTO theaterLayout = new TheaterLayoutDTO();
		final List<TheaterSectionDTO> theaterSections = new ArrayList<>();

		Stream.of(rawLayout.split(System.lineSeparator())).collect(Collectors.toList()).forEach(row -> {

			final List<String> sections = Stream.of(row.split(TheaterConstants.SPACE)).collect(Collectors.toList());
			final AtomicInteger sectionCount = new AtomicInteger(0);
			sections.forEach(section -> {
				try {
					theaterSections.add(new TheaterSectionDTO(rowCount.get() + 1, sectionCount.get() + 1,
							Integer.valueOf(section.trim())));
				} catch (NumberFormatException nfe) {
					System.err.println("Number FormatException" + nfe.getMessage());
					throw new NumberFormatException();
				}
				sectionCount.getAndIncrement();
			});
			rowCount.getAndIncrement();

		});

		theaterLayout.setSections(theaterSections);

		return theaterLayout;
	}

	/**
	 * Transform raw Ticket Request to List<TheaterRequestDTO>
	 * 
	 * @param ticketRequest
	 * @return
	 * @throws NumberFormatException
	 */
	public List<TheaterRequestDTO> transformTheaterRequests(final String ticketRequest) throws NumberFormatException {

		final List<TheaterRequestDTO> theaterRequests = new ArrayList<>();

		Stream.of(ticketRequest.split(System.lineSeparator())).collect(Collectors.toList()).forEach(request -> {

			final List<String> requests = Stream.of(request.split(TheaterConstants.SPACE)).collect(Collectors.toList());

			try {
				theaterRequests
						.add(new TheaterRequestDTO(requests.get(0).trim(), Integer.valueOf(requests.get(1).trim())));
			} catch (NumberFormatException nfe) {
				System.err.println("Number FormatException" + nfe.getMessage());
				throw new NumberFormatException();
			}

		});

		return theaterRequests;

	}

	/**
	 * Core logic to process incoming tickets
	 * 
	 * @param layout
	 * @param requests
	 */
	public void processTicketRequests(final TheaterLayoutDTO layout, final List<TheaterRequestDTO> requests) {
		final AtomicInteger rowCount = new AtomicInteger(0);
		int totalAvailableSeats = layout.getSections().stream().mapToInt(o -> o.getAvailableSeats()).sum();

		System.out.println("Seats Distribution.\n");
		for (TheaterRequestDTO theaterRequest : requests) {
			// skip the iteration when request is completed
			boolean isMaxRequest = false;
			if (theaterRequest.getNoOfTickets() > totalAvailableSeats) {
				isMaxRequest = true;
				System.out.println(theaterRequest.getName() + " " + "Sorry, we can't handle your party.");
			} else {

				for (final TheaterSectionDTO theaterSection : layout.getSections()) {
					// Full fill the request when number of tickets equals
					// available
					// seats

					if (theaterRequest.getNoOfTickets() == theaterSection.getAvailableSeats()) {

						fullfillTheaterRequest(theaterRequest, theaterSection);
						break;

					} else if (theaterRequest.getNoOfTickets() < theaterSection.getAvailableSeats()) {

						// Find first available section which for which ticket
						// can be served.
						// requestNo != -1 , seats will be available after full
						// filling the demand/request.
						// requestNo == -1 , seats can be perfectly matched with
						// the demand/request

						int requestNo = IntStream
								.range(rowCount.get() + 1,
										requests.size())
								.filter(a -> !requests.get(a).isRequestCompleted()
										&& (requests.get(a).getNoOfTickets() == (theaterSection.getAvailableSeats()
												- theaterRequest.getNoOfTickets())))
								.findFirst().orElse(-1);

						if (requestNo != -1) {

							// Seats will be still available after full filling
							// the request/demand , so that can be used for
							// other requests
							fullfillTheaterRequest(theaterRequest, theaterSection);

							break;

						} else {

							// Find the first matching section for which
							// requested tickets can be full filled
							fullfillTheaterRequest(theaterRequest, layout.getSections().stream()
									.filter(section -> section.getAvailableSeats() == theaterRequest.getNoOfTickets())
									.findFirst().get());
							break;

						}

					}

				}

			}

			// set -1 is when we need to split the party.
			if (!theaterRequest.isRequestCompleted() && !isMaxRequest) {

				System.out.println(theaterRequest.getName() + " " + "Call to split party.");

			}
			rowCount.getAndIncrement();

		}

	}

	/**
	 * 
	 * @param theaterRequest
	 * @param theaterSection
	 */
	private void fullfillTheaterRequest(final TheaterRequestDTO theaterRequest,
			final TheaterSectionDTO theaterSection) {

		theaterSection.setAvailableSeats(theaterSection.getAvailableSeats() - theaterRequest.getNoOfTickets());

		theaterRequest.setRequestCompleted(true);
		if (theaterRequest.isRequestCompleted()) {
			System.out.println(theaterRequest.getName() + " " + "Row-> " + theaterSection.getRowNumber() + " "
					+ "Section-> " + theaterSection.getSectionNumber());
		}

	}

}