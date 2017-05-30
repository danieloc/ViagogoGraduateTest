package gridMapElements;

import java.text.DecimalFormat;

public class Ticket {
	private int id;
	private String description;
	private double price;

	public Ticket(int id, String description, double price) {
		this.id = id;
		setDescription(description);
		setPrice(price);
	}
	public int getId() {
		return id;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public String getDescription() {
		return description;
	}
	public void setPrice(double price) {
		this.price = price;
	}
	public double getPrice(){
		return this.price;
	}
	public String getPriceInDollars(){
		DecimalFormat df = new DecimalFormat("#.00");
	    String priceFormated = df.format(this.price);
		return "$" + priceFormated;
	}
}