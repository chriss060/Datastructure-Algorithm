package DijkstraPath;
import java.util.HashMap;
import java.util.ArrayList;
import java.util.Arrays;


public class Edge implements Comparable<Edge> {

	public int distance;
	public String vertex;
	
	public Edge(int distance, String vertex) {
		this.distance = distance;
		this.vertex = vertex;
	}
	
	@Override
	public int compareTo(Edge edge) {
		return this.distance - edge.distance;
	}

}
