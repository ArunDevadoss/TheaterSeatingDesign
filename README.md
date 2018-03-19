# TheaterSeatingDesign

Please follow the below steps to run the production ready artifact for the Theater Seating problem.

## Pre-Requisite:

The machine should have minimum **JRE 8** and above version in order to run the artifact

1)	Download the TheaterSeatingDesign.jar from the following github link.

		https://github.com/ArunDevadoss/TheaterSeatingDesign/blob/master/TheaterSeatingDesign.jar

2) Navigate to the folder where you have downloaded the jar file.

3) Execute the following command from windows/mac machine.
	
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

Note : Attached code coverage report too.

## Problem Statement:

You run a small theater and each month, you have patrons mail in requests for pre-sale tickets.  You need to process these ticket requests and either tell them where their party will sit or explain to the patron why you can't complete their order.

You have a few rules that you need to follow when you fill the orders:
1.	Fill as many orders as possible
2.	Put parties as close to the front as possible.
3.	If there are not enough seats available in the theater to handle a party, tell them "Sorry, we can't handle your party."
4.	Each party must sit in a single row in a single section.  If they won't fit, tell them "Call to split party".

Your program must parse a theater layout and a list of ticket requests and produce a list of tickets or explanations in the same order as the requests.

The theater layout is made up of 1 or more rows.  Each row is made up of 1 or more sections separated by a space.

After the theater layout, there is one empty line, followed by 1 or more theater requests.  The theater request is made up of a name followed by a space and the number of requested tickets.
