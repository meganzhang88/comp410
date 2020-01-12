package SPLT_A4;

public class SPLT implements SPLT_Interface{
  private BST_Node root;
  private int size;
  
  public SPLT() {
    this.size = 0;
  } 
  
  public BST_Node getRoot() { //please keep this in here! I need your root node to test your tree!
    return root;
  }

@Override
public boolean insert(String s) {
	// TODO Auto-generated method stub
	if (empty()) {
		root = new BST_Node(s);
		size = 1;
		return true;
	} else {
		root = root.insertNode(s);
		if (root.justMade) {
			size++;
		}
		return true;
	}
	
}

@Override
public boolean remove(String s) {
	// TODO Auto-generated method stub
	if (contains(s)) {
		if (root.right == null && root.left == null) {
			root = null;
			size = 0;
			return true;
		}
		if (root.right == null) {
			root = root.left;
			root.par = null;
		} else if (root.left == null) {
			root = root.right;
			root.par = null;
		} else {
			BST_Node rightSide = root.right;
			BST_Node leftSide = root.left;
			
			root = leftSide.findMax();
			root.right = rightSide;
			rightSide.par = root;
			
			root.par = null;
		}
		size--;
		return true;
	} else {
		return false;
	}
}

@Override
public String findMin() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		root = root.findMin();
		return root.data;
	}
}

@Override
public String findMax() {
	// TODO Auto-generated method stub
	if (empty()) {
		return null;
	} else {
		root = root.findMax();
		return root.data;
	}
}

@Override
public boolean empty() {
	// TODO Auto-generated method stub
	return (size == 0);
}

@Override
public boolean contains(String s) {
	// TODO Auto-generated method stub
	if (empty()) {
		return false;
	}
	BST_Node tempNode = root.containsNode(s);
	if (tempNode != null) {
		root = tempNode;
	}
	if (tempNode != null && s == tempNode.data) {
		return true;
	} else {
		return false;
	}

}

@Override
public int size() {
	// TODO Auto-generated method stub
	return size;
}

@Override
public int height() {
	// TODO Auto-generated method stub
	if (empty()) {
		return -1;
	} else {
		return root.getHeight();
	}
}  

}