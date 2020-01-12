package DiGraph_A5;

public class Edge {
	
	protected String dLabel;
	protected String sLabel;
	protected String eLabel;
	protected long weight;
	protected long id;
	protected Edge next;
	
	public Edge(long id, String sLabel, String dLabel) {
		this.id = id;
		this.sLabel = sLabel;
		this.dLabel = dLabel;
		this.weight = 1;
		this.eLabel = null;
		this.next = null;
	}
	
	public String getsLabel() {
		return sLabel;
	}
	public String getdLabel() {
		return dLabel;
	}
	public String geteLabel() {
		return eLabel;
	}
	public long getWeight() {
		return weight;
	}
	public Edge getNext() {
		return next;
	}

}
