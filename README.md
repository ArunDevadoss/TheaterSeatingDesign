# TheaterSeatingDesign

Please follow the below steps to run the production ready artifact for the Theater Seating problem.

##Pre-Requisite:

The machine should have minimum JRE of 8 and above version in order to run the artifact

1)	Download the TheaterSeatingDesign.jar from the following github link.

		https://github.com/ArunDevadoss/TheaterSeatingDesign/blob/master/TheaterSeatingDesign.jar

2) Navigate to the folder where you have downloaded the jar file.

3) Execute the following command from windows machine
	
		java -jar TheaterSeatingDesign.jar
	
		Now it will prompt for the below message
	
		"Enter Theater Layout and Ticket requests and then enter 'end'."
	
4) Please enter your input test cases in the following order and enter 'end' at the last line to process the ticket requests.

				6 6
				3 5 5 3
				4 6 6 4
				2 8 8 2
				6 6

				Smith 2
				Jones 5
				Davis 6
				Wilson 100
				Johnson 3
				Williams 4
				Brown 8
				Miller 12
				end

5) The output will look like  
				
				Smith Row 1 Section 1
				Jones Row 2 Section 2
				Davis Row 1 Section 2
				Wilson Sorry, we can't handle your party.
				Johnson Row 2 Section 1
				Williams Row 1 Section 1
				Brown Row 4  Section 2
				Miller Call to split party.

