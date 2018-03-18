package com.mm.po.theater.dto;

import java.util.List;

/**
 * 
 * @author Arun Devadoss
 *
 */
public class TheaterLayoutDTO {

	/**
	 * 
	 */
	private List<TheaterSectionDTO> sections;

	/**
	 * 
	 * @return
	 */
	public List<TheaterSectionDTO> getSections() {
		return sections;
	}

	/**
	 * 
	 * @param sections
	 */
	public void setSections(List<TheaterSectionDTO> sections) {
		this.sections = sections;
	}

}