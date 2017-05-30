package gridMapElements;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Scanner;

public class programStart {
	
	//This is where the program starts.
	public static void main(String[] args) {
		/*There was 3 options that could have been taken when storing the events. 
		Option 1: 20x20 2D Matrix of Intersections
		I decided this matrix was unnessessary - because the World size was (10x10)+(10x10) and not 20x20. A half of the matrix would have been wasted.
		Option 2: 2x(10x10 2D Matrices) This would have halved the size of the saved instances from option 1.
		But - why would intersections with no events be stored? It was pointless. If this was a real application - Like google maps, I would not pretend
		that there was an event that might occur at every single intersection. Instead I would only store the locations where there were events.
		Option 3: Store an arrayList of active Intersections/intersections with events. It is a way of compressing the data.
		You may take the data from a shocking complexity of O(n(squared)) to something much lower.*/
		//Even though the imaginary world - each side is 10 by 10 squares- there are 11x11 intersections(I counted the edges)
		ArrayList<Intersection> activeIntersections = getIntersectionsWithEvent(11,11,false);
		//getIntersectionsWithEvent calculated the negative side of the grid separately - and is passed a boolean so that it does not calculate (0,0) twice
		activeIntersections.addAll(getIntersectionsWithEvent(-11,-11,true));
		int[] inputCoOrdinates = getInputCoOrdinates();
		ArrayList<Intersection> closestIntersections = getclosestIntersections(activeIntersections, inputCoOrdinates);
		System.out.println("Closest Events to (" + inputCoOrdinates[0] + ","+ inputCoOrdinates[1] + ")");
		for(int i = 0; i < closestIntersections.size(); i++) {
			Intersection closeIntersection = closestIntersections.get(i);
			System.out.println("Event " + closeIntersection.getEvent().getEventId() +" - " + closeIntersection.getEvent().getCheapestTicket().getPriceInDollars() + ", Distance: " + closeIntersection.getDistance() );
		}
	}

	public static ArrayList<Intersection> getclosestIntersections(ArrayList<Intersection> activeIntersections, int[] inputCoOrdinates){
		ArrayList<Intersection> closestIntersections = new ArrayList<Intersection>();
		for(int i =0; i < activeIntersections.size(); i++) {
			//Every single Intersection with an event at it is passed the inputted co-ordinates.
			activeIntersections.get(i).setDistance(inputCoOrdinates);
		}
		//The comparator interface was implemented to help sort the Intersections based on distance. https://stackoverflow.com/questions/1206073/sorting-a-collection-of-objects
		Collections.sort(activeIntersections, new DistanceComparator());
		for(int i = 0; i< 5; i++) {
			closestIntersections.add(activeIntersections.get(i));
		}
		return closestIntersections;
	}


	public static int[] getInputCoOrdinates() {
		Scanner reader = new Scanner(System.in);
		boolean correctInput = false;
		int[] coOrdinates = new int [2];
		while(!correctInput) {
			System.out.println("Enter your Co-Ordinates: " );
			String coordinates = reader.nextLine();
			if (!coordinates.matches("-?[0-9]+,-?[0-9]+")) {
				System.out.print("Input not two comma separated words. \n Please try again.");
			}
			else {
				correctInput = true;
				String[] splitCoordinates = coordinates.split(",");
				coOrdinates[0] = Integer.parseInt(splitCoordinates[0].trim());
				coOrdinates[1] = Integer.parseInt(splitCoordinates[1].trim());
			}
		}
		reader.close();
		return coOrdinates;
	}

	//This function randomly put events at intersections.
	public static ArrayList<Intersection> getIntersectionsWithEvent(int maxX, int maxY, boolean zeroZeroCalculated) {
		ArrayList<Intersection> intersectionsWithEvent = new ArrayList<Intersection>();
		int i =0;
		//Math.abs is used here because if 11 or -11 is passed to the function, it needs to work for both.
		while(Math.abs(i) < Math.abs(maxX)){
			int j = 0;
			while(Math.abs(j) < Math.abs(maxY)) {
				if(!(i == 0 && j == 0 && !zeroZeroCalculated)){
					double eventPossibility = Math.random() * 100 + 1;
					//There is a 25 percent chance of an event being present.
					if (eventPossibility <= 25 ) {
						Event randomEvent = new Event();
						//There will be between 0 and 99 "Standard" tickets created per event.
						double standardTicketPrice = (Math.random()*100) + Math.random();
						randomEvent.addTickets((int)(Math.random()*100+1), "Standard", standardTicketPrice);
						if(eventPossibility >= 15) {
							//There is a 10% chance of between 0 and 99 "Excusive" tickets being created.
							//Exclusive tickets are always more expensive then standard tickets.
							double exclusiveTicketPrice = standardTicketPrice + (Math.random()*100) + Math.random();
							randomEvent.addTickets((int)(Math.random()*100+1), "Exclusive", exclusiveTicketPrice);
						}
						intersectionsWithEvent.add(new Intersection(randomEvent, i, j));
					}
				}
				//If calculating between 11 and 0
				if(maxY > 0) {
					j++;
				}  //If calculating between -11 and 0
				else j--;
			} //If calculating between 11 and 0
			if(maxX > 0) {
				i++;
			} //If calculating between -11 and 0
			else i--;
		}
		return intersectionsWithEvent;
	}
}
