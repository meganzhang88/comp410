package DiGraph_A5;

import java.util.Comparator;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Iterator;
import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.PriorityQueue;
import java.util.Queue;
import java.util.Set;

public class DiGraph implements DiGraphInterface {

  // in here go all your data and methods for the graph
	HashSet<Long> nids;
	HashSet<Long> eids;
	HashMap<String, Node> nodes;
	List<Node> nodeList;
	long edgeNum;
	long nodeNum;

  public DiGraph ( ) { // default constructor
    // explicitly include this
    // we need to have the default constructor
    // if you then write others, this one will still be there
	  nids = new HashSet<Long>();
	  eids = new HashSet<Long>();
	  nodes = new HashMap<String, Node>();
	  nodeList = new LinkedList<Node>();
	  edgeNum = 0;
	  nodeNum = 0;
  }

@Override
public boolean addNode(long idNum, String label) {
	if (nodes.containsKey(label) || label == null) {
		return false;
	} else if (idNum < 0 || nids.contains(idNum)) {
		return false;
	} else {
		Node node = new Node(idNum, label);
		nodes.put(label, node);
		nodeList.add(node);
		nids.add(idNum);
		nodeNum++;
		return true;
	}
}

@Override
public boolean addEdge(long idNum, String sLabel, String dLabel, long weight, String eLabel) {
	if (nodes.isEmpty()) {
		return false;
	} else if (idNum < 0 || eids.contains(idNum)) {
		return false;
	} else if (nodes.containsKey(sLabel) == false) {
		return false;
	} else if (nodes.containsKey(dLabel) == false) {
		return false;
	}
	Node sNode = nodes.get(sLabel);
	Node dNode = nodes.get(dLabel);
	Edge sEdge = sNode.getEdge();
	Edge pEdge = null;
	while (sEdge != null) {
		pEdge = sEdge;
		if (sEdge.getdLabel().equals(dLabel)) {
			return false;
		}
		sEdge = sEdge.getNext();
	}
	Edge edge = new Edge(idNum, sLabel, dLabel);
	edge.weight = weight;
	edge.eLabel = eLabel;
	if (pEdge == null) {
		sNode.edge = edge;
	} else {
		pEdge.next = edge;
	}
	eids.add(idNum);
	edgeNum++;
	sNode.outputDegree++;
	dNode.inputDegree++;
	return true;
}

@Override
public boolean delNode(String label) {
	if (!nodes.containsKey(label)) {
		return false;
	} else {
		Set<Entry<String, Node>> set = nodes.entrySet();
		Iterator<Entry<String, Node>> iterator = set.iterator();
		while (iterator.hasNext()) {
			Map.Entry<String, Node> nodePair = (Map.Entry<String, Node>)iterator.next();
			Node currentNode = nodePair.getValue();
			Edge pEdge = null;
			Edge deleteEdge = currentNode.getEdge();
			if (deleteEdge != null) {
				int i = 0;
				while (deleteEdge != null && !deleteEdge.dLabel.equals(label)) {
					pEdge = deleteEdge;
					deleteEdge = deleteEdge.getNext();
					i++;
				}
				if (deleteEdge == null) {
					continue;
				} else if (i == 0) {
					currentNode.edge = deleteEdge.getNext();
				} else {
					pEdge.next = deleteEdge.getNext();
					eids.remove(deleteEdge.id);
					currentNode.outputDegree--;
					edgeNum--;
				}
			}
		}
		Node node = nodes.get(label);
		Edge edge = node.getEdge();
		int i = 0;
		while (edge != null) {
			i++;
			nodes.get(edge.dLabel).inputDegree--;
			edge = edge.getNext();
		}
		nodes.remove(node.getName());
		edgeNum -= i;
		nids.remove(node.id);
		nodeNum--;
		nodeList.remove(node);
		return true;
	}
}

@Override
public boolean delEdge(String sLabel, String dLabel) {
	Node sNode = nodes.get(sLabel);
	Node dNode = nodes.get(dLabel);
	if (sNode == null || dNode == null) {
		return false;
	}
	Edge edge = sNode.getEdge();
	Edge pEdge = null;
	int i = 0;
	while (edge != null) {
		pEdge = edge;
		if (edge.dLabel.equals(dLabel)) {
			if (i == 0) {
				sNode.edge = edge.getNext();
			} else {
				pEdge.next = edge.getNext();
			}
			sNode.outputDegree--;
			dNode.inputDegree--;
			edgeNum--;
			eids.remove(edge.id);
			return true;
		}
		edge = edge.getNext();
		i++;
	}
	return false;
}

@Override
public long numNodes() {
	return nodeNum;
}

@Override
public long numEdges() {
	return edgeNum;
}
  
  // rest of your code to implement the various operations

public String[] topoSort() {
	Queue<Node> nodesQueue = new LinkedList<Node>();
	int size = (int) nodeNum;
	int i = 0;
	String[] arr = new String[size];
	
	ListIterator<Node> iterator = nodeList.listIterator();
	while (iterator.hasNext()) {
		Node node = iterator.next();
		if (node.inputDegree == 0) {
			nodesQueue.add(node);
		}
	}
	if (nodesQueue.isEmpty()) {
		return null;
	}
	while (!nodesQueue.isEmpty()) {
		Node removeNode = nodesQueue.remove();
		arr[i] = removeNode.name;
		Edge removeEdge = removeNode.getEdge();
		while (removeEdge != null) {
			int dInputDegree = -- nodes.get(removeEdge.getdLabel()).inputDegree;
			if (dInputDegree == 0) {
				nodesQueue.add(nodes.get(removeEdge.getdLabel()));
			}
			removeEdge = removeEdge.getNext();
		}
		i++;
	}
	if (i == size) {
		return arr;
	} else {
		return null;
	}
}

@Override
public ShortestPathInfo[] shortestPath(String label) {
	int size = (int) numNodes();
	ShortestPathInfo[] arr = new ShortestPathInfo[size];
	ListIterator<Node> iterator = nodeList.listIterator();
	
	while (iterator.hasNext()) {
		Node node = iterator.next();
		node.distance = Long.MAX_VALUE;
		node.known = false;
		node.path = null;
	}
	
	Comparator<ShortestPathInfo> distanceComparator = new Comparator<ShortestPathInfo>() {
		public int compare(ShortestPathInfo path1, ShortestPathInfo path2) {
			return (int) (path1.getTotalWeight() - path2.getTotalWeight());
		}
	};
	Queue<ShortestPathInfo> nodePriorityQueue = new PriorityQueue<ShortestPathInfo>(size, distanceComparator);
	
	long currentDistance = nodes.get(label).distance = 0;
	nodePriorityQueue.add(new ShortestPathInfo(label, currentDistance));
	int i = 0;
	
	while (!nodePriorityQueue.isEmpty()) {
		Node currentNode = nodes.get(nodePriorityQueue.peek().getDest());
		Long currentNodeDistance = currentNode.distance;
		String currentNodeName = currentNode.name;
		ShortestPathInfo currentPath = nodePriorityQueue.poll();
		if (currentNode.known) {
			continue;
		}
		arr[i] = currentPath;
		currentNode.known = true;
		Edge edge = currentNode.getEdge();
		while (edge != null) {
			Node node = nodes.get(edge.dLabel);
			if (!node.known) {
				long preDistance = node.distance;
				currentDistance = currentNodeDistance + edge.weight;
				if (currentDistance < preDistance) {
					node.distance = currentDistance;
					node.path = currentNodeName;
					nodePriorityQueue.add(new ShortestPathInfo(node.name, currentDistance));
				}
				
			}
			edge = edge.getNext();
		}
		i++;
	}
	
	if (i < size) {
		while (iterator.hasPrevious()) {
			Node node = iterator.previous();
			if (node.known == false) {
				arr[i] = new ShortestPathInfo(node.name, -1);
				i++;
				if (i == size) {
					break;
				}
			}
		}
	}
	return arr;
}

}
