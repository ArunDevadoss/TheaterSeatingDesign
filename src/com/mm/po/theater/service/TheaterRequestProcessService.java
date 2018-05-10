package com.mm.po.theater.service;

import java.util.List;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.mm.po.theater.constant.TheaterConstants;
import com.mm.po.theater.dto.TheaterRequestDTO;
import com.mm.po.theater.dto.TheaterSectionDTO;

/**
 * Core logic to process incoming tickets
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterRequestProcessService {

	/**
	 * 
	 * Process all the incoming threatre request based on section and available
	 * seats
	 * 
	 * @param theaterInformation
	 */
	public void processTicketRequests(final List<TheaterRequestDTO> theaterRequests,
			final List<TheaterSectionDTO> theaterSections) {
		final AtomicInteger rowCount = new AtomicInteger(0);
		int availableSeats = theaterSections.stream().mapToInt(o -> o.getAvailableSeats()).sum();

		System.out.println(TheaterConstants.SEAT_DISTRIBUTION);
		for (final TheaterRequestDTO theaterRequest : theaterRequests) {

			boolean isMaxRequest = false;
			if (theaterRequest.getNoOfTickets() > availableSeats) {
				// No of tickets is more than available seats
				isMaxRequest = true;
				System.out.println(new StringBuffer(theaterRequest.getPersonName()).append(TheaterConstants.BLACK_SPACE)
						.append(TheaterConstants.CANNOT_HANDLE_PARTY).toString());
			} else {

				for (final TheaterSectionDTO theaterSection : theaterSections) {
					// Full fill the request when number of tickets equals
					// available seats

					if (theaterRequest.getNoOfTickets() == theaterSection.getAvailableSeats()) {

						fullfillTheaterRequest(theaterRequest, theaterSection);
						break;

					} else if (theaterRequest.getNoOfTickets() < theaterSection.getAvailableSeats()) {

						// Check any left over seats can be full filled by the
						// upcoming request(i,e when requestNo != 1) once
						// current request is served.

						int requestNo = IntStream
								.range(rowCount.get() + 1, theaterRequests.size()).filter(
										a -> !theaterRequests.get(a).isRequestCompleted()
												&& (theaterRequests).get(a)
														.getNoOfTickets() == (theaterSection.getAvailableSeats()
																- theaterRequest.getNoOfTickets()))
								.findFirst().orElse(-1);

						if (requestNo != -1) {

							// If requestNo != -1 , that corresponding request
							// can be full filled with the left over seats from
							// the current request. Hence full fill
							// the current section.

							fullfillTheaterRequest(theaterRequest, theaterSection);

							break;

						} else {

							// Find the first matching section for which
							// requested tickets can be completely full filled

							TheaterSectionDTO filteredTheaterSection = theaterSections.stream()
									.filter(section -> section.getAvailableSeats() == theaterRequest.getNoOfTickets())
									.findFirst().orElse(null);
							if (null != filteredTheaterSection) {
								fullfillTheaterRequest(theaterRequest, filteredTheaterSection);
							} else {
								// If there is no matching section available,
								// then use the current section to
								// full fill the request
								fullfillTheaterRequest(theaterRequest, theaterSection);
							}

							break;

						}

					}

				}

			}

			// set -1 is when we need to split the party.
			if (!theaterRequest.isRequestCompleted() && !isMaxRequest) {

				System.out.println(new StringBuffer(theaterRequest.getPersonName()).append(TheaterConstants.BLACK_SPACE)
						.append(TheaterConstants.SPLIT_PARTY).toString());

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

			System.out.println(new StringBuffer(theaterRequest.getPersonName()).append(TheaterConstants.BLACK_SPACE)
					.append(TheaterConstants.ROW).append(theaterSection.getRowNumber())
					.append(TheaterConstants.BLACK_SPACE).append(TheaterConstants.SECTION)
					.append(theaterSection.getSectionNumber()).toString());
		}

	}

}
