//Instead of creating a 2D array/ Matrix - I will instead use an arrayList of 'Intersection' Objects.
package gridMapElements;

public class Intersection {
	Event intersecEvent;
	int x;
	int y;
	int distance = 0;

	public Intersection(Event intersecEvent, int x, int y) {
		setIntersecEvent(intersecEvent);
		setX(x);
		setY(y);
	}
	public void setIntersecEvent(Event intersecEvent) {
		this.intersecEvent = intersecEvent;
	}
	public void setX(int x) {
		this.x = x;
	}
	public void setY(int y) {
		this.y = y;
	}
	public Event getEvent() {
		return this.intersecEvent;
	}
	public int getX() {
		return this.x;
	}
	public int getY() {
		return this.y;
	}
	public int[] getXY() {
		int[] xY = new int[2];
		xY[0] = this.x;
		xY[1] = this.y;
		return xY;
	}
	public void setDistance(int[] userCoOrdinates){
		int distanceOnXAxis = Math.abs(this.x - userCoOrdinates[0]); 
		int distanceOnYAxis = Math.abs(this.y - userCoOrdinates[1]);
		this.distance = distanceOnXAxis + distanceOnYAxis;
	}
	public int getDistance() {
		return distance;
	}
}

