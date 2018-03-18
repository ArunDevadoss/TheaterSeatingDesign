package com.mm.po.theatre.test.service;

import java.util.Scanner;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import com.mm.po.theater.exception.InvalidTheaterLayoutException;
import com.mm.po.theater.exception.InvalidTheaterRequestException;
import com.mm.po.theater.exception.InvalidTicketNumberException;
import com.mm.po.theater.service.TheaterInputService;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheatreSeatingServiceTest {

	private TheaterInputService inputService;

	@Before
	public void setUp() {

		inputService = new TheaterInputService();
	}

	@After
	public void tearDown() {
		inputService = null;
	}

	/**
	 * 
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	@Test
	public void test_processTicketRequests_One()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("6 6").append(System.lineSeparator()).append("3 5 5 3")
				.append(System.lineSeparator()).append("4 6 6 4").append(System.lineSeparator()).append("2 8 8 2")
				.append(System.lineSeparator()).append("6 6").append(System.lineSeparator())
				.append(System.lineSeparator()).append("Smith 2").append(System.lineSeparator()).append("Jones 5")
				.append(System.lineSeparator()).append("Davis 6").append(System.lineSeparator()).append("Wilson 100")
				.append(System.lineSeparator()).append("Johnson 3").append(System.lineSeparator()).append("Williams 4")
				.append(System.lineSeparator()).append("Brown 8").append(System.lineSeparator()).append("Miller 12")
				.append(System.lineSeparator()).append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	/**
	 * 
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	@Test
	public void test_processTicketRequests_Two()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("6 6").append(System.lineSeparator()).append("3 5 5 3")
				.append(System.lineSeparator()).append(System.lineSeparator()).append("Smith 2")
				.append(System.lineSeparator()).append("Jones 5").append(System.lineSeparator()).append("Davis 6")
				.append(System.lineSeparator()).append("Wilson 100").append(System.lineSeparator()).append("end")
				.toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	/**
	 * 
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	@Test
	public void test_processTicketRequests_Three()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("1 6").append(System.lineSeparator()).append("3 5 5 3")
				.append(System.lineSeparator()).append(System.lineSeparator()).append("Smith 2")
				.append(System.lineSeparator()).append("Jones 100").append(System.lineSeparator()).append("Davis 3")
				.append(System.lineSeparator()).append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	/**
	 * 
	 * @throws InvalidTheaterRequestException
	 * @throws InvalidTheaterLayoutException
	 * @throws InvalidTicketNumberException
	 */
	@Test
	public void test_processTicketRequests_Four()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("2 1").append(System.lineSeparator()).append("3 1 1 1")
				.append(System.lineSeparator()).append(System.lineSeparator()).append("Smith 1")
				.append(System.lineSeparator()).append("Jones 3").append(System.lineSeparator()).append("Davis 3")
				.append(System.lineSeparator()).append("Alpha 3").append(System.lineSeparator()).append("Beta 10")
				.append(System.lineSeparator()).append(System.lineSeparator()).append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	@Test(expected = InvalidTheaterRequestException.class)
	public void test_InvalidTheaterRequest()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("6 6").append(System.lineSeparator()).append(System.lineSeparator())
				.append("Smith").append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	@Test(expected = InvalidTicketNumberException.class)
	public void test_InvalidTicketNumberException()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("6 6").append(System.lineSeparator()).append(System.lineSeparator())
				.append("Smith A").append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

	@Test(expected = InvalidTheaterLayoutException.class)
	public void test_InvalidTheaterLayout()
			throws InvalidTheaterRequestException, InvalidTheaterLayoutException, InvalidTicketNumberException {

		String rawLayout = new StringBuilder("6 A").append(System.lineSeparator()).append(System.lineSeparator())
				.append("Smith 2").append("end").toString();
		inputService.handleTheaterInput(new Scanner(rawLayout));

	}

}
