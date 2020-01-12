package DiGraph_A5;

public class Node {
	
	long id;
	String name;
	Edge edge;
	int inputDegree;
	int outputDegree;
	Boolean known;
	long distance;
	String path;
	
	public Node(long id, String name) {
		this.id = id;
		this.name = name;
		this.inputDegree = 0;
		this.outputDegree = 0;
		this.edge = null;
		this.known = false;
		this.distance = Long.MAX_VALUE;
		this.path = null;
				
	}
	
	public String getName() {
		return name;
	}
	
	public Edge getEdge() {
		return edge;
	}
	
	public int getInputDegree() {
		return inputDegree;
	}
	
	public int getOutputDegree() {
		return outputDegree;
	}

}
