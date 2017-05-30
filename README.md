# ViagogoGraduateTest
Finding the closest event on a grid map.

## Presumptions:
I made the chance of an event being at any intersection of the grid 25%
Since there could be events at any intersection and not square of the grid- Instead of the possibilities being (10x10) + (10x10), it was instead (11x11)+(11x11) -1 (-1 so that the (0,0) intersection was not counted twice)
I presumed that since, I had to find the cheapest ticket - there should be different kinds of tickets eg. Standard,Exclusive.
I presumed, that for easy extensibility in the future, OOD should be implemented.

##Storing intersections.
There was 3 options that could have been taken when storing the events. 
Option 1: 20x20 2D Matrix of Intersections. I decided this matrix was unnessessary - because the World size was (10x10)+(10x10) and not 20x20. A half of the matrix would have been wasted.
Option 2: Having two 10x10 2D Matrices would have halved the size of the saved instances from option 1. But - why would intersections with no events be stored? It was pointless. If this was a real application - Like google maps, I would not pretend that there was an event that might occur at every single intersection. Instead I would only store the locations where there were events.
Option 3: Store an arrayList of active Intersections/intersections with events. This is a way to compress the data.

## Question Answers
Question
- How might you change your program if you needed to support multiple events at the same location?
Answer:
I would change the intersection class variable from a single 'intersecEvent' to an arrayList of events. Then when I've stored the arrayList of events, I can iterate through them - and deal with multiple events per intersection. Not just one.
Question
- How would you change your program if you were working with a much larger world size?
Answer
I've dealt with the biggest factor already under the 'Storing Intersections' header. By using Option 3, I've greatly reduced the complexity of the application. 
