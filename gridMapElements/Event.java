package gridMapElements;

import java.util.ArrayList;

public class Event {
	private static int count = 1;
	private int eventId;
	private ArrayList<Ticket> tickets;
	private int ticketId;


	public Event(){
		this.eventId = count;
		tickets = new ArrayList<Ticket>();
		ticketId = 1;
		count++;
	}
	public void addTickets(int newTicketCount, String description ,double price)
	{
		for(int i = 0; i < newTicketCount; i++) {
			this.tickets.add(new Ticket(ticketId, description, price));
			this.ticketId += 1;
		}
	}


	public String getEventId()
	{
		//This if statement checks to see if the string needs to be padded with zeros eg. 001, 002....
		if(!(this.eventId/1000 >= 1)) {
			//the string is returned with 3 decimals. 
			return String.format("%03d", this.eventId);
		}
		//If the eventId is longer than 3 decimals - then the 4/5/6.... decimal figure is returned instead.(Larger world size)
		else return String.valueOf(this.eventId);
	}

	public int getTicketsAvailable()
	{
		return this.tickets.size();
	}

	public Ticket getCheapestTicket()
	{
		if(tickets.size() > 0) {
			Ticket cheapestTicket = tickets.get(0);
			//Nothing is more expensive than infinity so it's good for getting the initial "cheapestPrice"
			double cheapestPrice = Double.POSITIVE_INFINITY;
			for(int i = 0; i < tickets.size(); i++) {
				double currentTicketPrice = tickets.get(i).getPrice();
				if(currentTicketPrice < cheapestPrice) {
					cheapestPrice = currentTicketPrice;
					cheapestTicket = tickets.get(i);
				}
			}
			return cheapestTicket;
		}
		else return null;
	}
}

