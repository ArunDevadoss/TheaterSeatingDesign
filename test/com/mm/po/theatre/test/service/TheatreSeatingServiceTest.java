package com.mm.po.theatre.test.service;

import static org.junit.Assert.assertEquals;

import java.util.ArrayList;
import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.mm.po.theater.dto.TheaterLayoutDTO;
import com.mm.po.theater.dto.TheaterRequestDTO;
import com.mm.po.theater.dto.TheaterSectionDTO;
import com.mm.po.theater.service.TheaterSeatingService;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheatreSeatingServiceTest {

	/**
	 * 
	 */
	private TheaterSeatingService theaterSeatingService;

	/**
	 * 
	 */
	private TheaterLayoutDTO theaterLayout;
	/**
	 * 
	 */
	private List<TheaterSectionDTO> theaterSections;
	/**
	 * 
	 */
	private List<TheaterRequestDTO> theaterRequests;

	@Before
	public void setUp() {
		theaterSeatingService = new TheaterSeatingService();
		theaterLayout = new TheaterLayoutDTO();
		theaterSections = new ArrayList<>();
		theaterRequests = new ArrayList<>();
	}

	@After
	public void tearDown() {
		theaterSeatingService = null;
		theaterLayout = null;
		theaterSections = null;
	}

	/**
	 * 
	 */
	@Test
	public void test_TheaterTotalCapacity() {

		String rawLayout = new StringBuilder("6 6").append(System.lineSeparator()).append("3 5 5 3")
				.append(System.lineSeparator()).append("2 8 8 2").append(System.lineSeparator()).append("6 6")
				.toString();

		TheaterLayoutDTO theaterLayout = theaterSeatingService.transformTheaterLayout(rawLayout);

		assertEquals(60, theaterLayout.getSections().stream().mapToInt(o -> o.getAvailableSeats()).sum());
	}

	@Test(expected = NumberFormatException.class)
	public void test_TheaterLayout_NumberFormatException() {

		String rawLayout = new StringBuilder("6 6Ar").append(System.lineSeparator()).append("3 5 5 3").toString();
		theaterSeatingService.transformTheaterLayout(rawLayout);
	}

	/**
	 * 
	 */
	@Test
	public void test_NoOfTicketRequests() {

		String request = new StringBuilder("Smith 2").append(System.lineSeparator()).append("Jones 5")
				.append(System.lineSeparator()).append("Davis 6").append(System.lineSeparator()).append("Wilson 100")
				.toString();

		assertEquals(4, theaterSeatingService.transformTheaterRequests(request).size());
	}

	@Test(expected = NumberFormatException.class)
	public void test_TicketRequests_NumberFormatException() {

		String request = new StringBuilder("Smith 2A").append(System.lineSeparator()).append("Jones 5").toString();

		theaterSeatingService.transformTheaterRequests(request);
	}

	/**
	 * 
	 */
	@Test
	public void test_NameAndNoOfTicketRequests() {

		String request = new StringBuilder("Smith 2").append(System.lineSeparator()).append("Jones 5")
				.append(System.lineSeparator()).append("Davis 6").append(System.lineSeparator()).append("Wilson 100")
				.toString();
		List<TheaterRequestDTO> theaterRequests = new ArrayList<>();

		constructTheaterRequestObject("Smith", 2, theaterRequests);
		constructTheaterRequestObject("Jones", 5, theaterRequests);
		constructTheaterRequestObject("Davis", 6, theaterRequests);
		constructTheaterRequestObject("Wilson", 100, theaterRequests);

		Assert.assertEquals(theaterRequests, theaterSeatingService.transformTheaterRequests(request));

	}

	/**
	 * 
	 */
	@Test
	public void test_processTicketRequests() {

		// Row 1
		constructTheaterSection(1, 1, 6, theaterSections);
		constructTheaterSection(2, 1, 6, theaterSections);
		// Row 2
		constructTheaterSection(1, 2, 3, theaterSections);
		constructTheaterSection(2, 2, 5, theaterSections);
		constructTheaterSection(3, 2, 3, theaterSections);
		constructTheaterSection(4, 2, 3, theaterSections);
		// Row 3

		constructTheaterSection(1, 3, 4, theaterSections);
		constructTheaterSection(2, 3, 6, theaterSections);
		constructTheaterSection(3, 3, 6, theaterSections);
		constructTheaterSection(4, 3, 4, theaterSections);

		// Row 4

		constructTheaterSection(1, 4, 2, theaterSections);
		constructTheaterSection(2, 4, 8, theaterSections);
		constructTheaterSection(3, 4, 8, theaterSections);
		constructTheaterSection(4, 4, 2, theaterSections);

		// Row 5
		constructTheaterSection(1, 5, 6, theaterSections);
		constructTheaterSection(2, 5, 6, theaterSections);

		theaterLayout.setSections(theaterSections);

		constructTheaterRequestObject("Smith", 2, theaterRequests);
		constructTheaterRequestObject("Jones", 5, theaterRequests);
		constructTheaterRequestObject("Davis", 6, theaterRequests);
		constructTheaterRequestObject("Wilson", 100, theaterRequests);
		constructTheaterRequestObject("Johnson", 3, theaterRequests);
		constructTheaterRequestObject("Williams", 4, theaterRequests);
		constructTheaterRequestObject("Brown", 8, theaterRequests);
		constructTheaterRequestObject("Miller", 12, theaterRequests);

		theaterSeatingService.processTicketRequests(theaterLayout, theaterRequests);

	}

	/**
	 * 
	 * @param sectionNumber
	 * @param rowNumber
	 * @param availableSeats
	 * @param theaterSections
	 */
	private void constructTheaterSection(final int sectionNumber, final int rowNumber, final int availableSeats,
			final List<TheaterSectionDTO> theaterSections) {
		TheaterSectionDTO theaterSection = new TheaterSectionDTO();
		theaterSection.setSectionNumber(sectionNumber);
		theaterSection.setRowNumber(rowNumber);
		theaterSection.setAvailableSeats(availableSeats);
		theaterSections.add(theaterSection);
	}

	/**
	 * 
	 * @param name
	 * @param noOfTickets
	 * @param theaterRequests
	 */
	private void constructTheaterRequestObject(final String name, final int noOfTickets,
			final List<TheaterRequestDTO> theaterRequests) {

		TheaterRequestDTO theaterRequest = new TheaterRequestDTO();
		theaterRequest.setName(name);
		theaterRequest.setNoOfTickets(noOfTickets);

		theaterRequests.add(theaterRequest);

	}

}
