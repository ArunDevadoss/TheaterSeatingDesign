package com.mm.po.theater.service;

import java.util.concurrent.atomic.AtomicInteger;
import java.util.stream.IntStream;

import com.mm.po.theater.constant.TheaterConstants;
import com.mm.po.theater.dto.TheaterDTO;
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
	public void processTicketRequests(final TheaterDTO theaterInformation) {
		final AtomicInteger rowCount = new AtomicInteger(0);

		System.out.println(TheaterConstants.SEAT_DISTRIBUTION);
		for (final TheaterRequestDTO theaterRequest : theaterInformation.getTheaterRequests()) {
			// skip the iteration when request is completed
			boolean isMaxRequest = false;
			if (theaterRequest.getNoOfTickets() > theaterInformation.getTotalAvailableCapacity()) {
				isMaxRequest = true;
				System.out.println(
						theaterRequest.getName() + TheaterConstants.BLACK_SPACE + TheaterConstants.CANNOT_HANDLE_PARTY);
			} else {

				for (final TheaterSectionDTO theaterSection : theaterInformation.getTheaterSections()) {
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
								.range(rowCount.get() + 1, theaterInformation.getTheaterRequests().size()).filter(
										a -> !theaterInformation.getTheaterRequests().get(a).isRequestCompleted()
												&& (theaterInformation.getTheaterRequests().get(a)
														.getNoOfTickets() == (theaterSection.getAvailableSeats()
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

							TheaterSectionDTO filteredTheaterSection = theaterInformation.getTheaterSections().stream()
									.filter(section -> section.getAvailableSeats() == theaterRequest.getNoOfTickets())
									.findFirst().orElse(null);
							if (null != filteredTheaterSection) {
								fullfillTheaterRequest(theaterRequest, filteredTheaterSection);
							} else {
								// If there is no matching section available, then use the current section to
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

				System.out.println(
						theaterRequest.getName() + TheaterConstants.BLACK_SPACE + TheaterConstants.SPLIT_PARTY);

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
			System.out.println(theaterRequest.getName() + TheaterConstants.BLACK_SPACE + TheaterConstants.ROW
					+ theaterSection.getRowNumber() + TheaterConstants.BLACK_SPACE + TheaterConstants.SECTION
					+ theaterSection.getSectionNumber());
		}

	}

}