package gridMapElements;

import java.util.Comparator;

//https://stackoverflow.com/questions/1206073/sorting-a-collection-of-objects
//For implementing Collections.sort
public class DistanceComparator implements Comparator<Intersection>
{
	public int compare(Intersection intersection1, Intersection intersection2)
	{
		return compareDistance(intersection1.getDistance(),intersection2.getDistance());
	}
	public static int compareDistance(int a, int b) {
	    return a < b ? -1
	         : a > b ? 1
	         : 0;
	}
}
