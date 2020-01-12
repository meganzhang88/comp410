package BST_A2;

public class BST_Node {
  String data;
  BST_Node left;
  BST_Node right;
  int height = 0;
  
  BST_Node(String data){ this.data=data; }

  // --- used for testing  ----------------------------------------------
  //
  // leave these 3 methods in, as is

  public String getData(){ return data; }
  public BST_Node getLeft(){ return left; }
  public BST_Node getRight(){ return right; }

  // --- end used for testing -------------------------------------------

  
  // --- fill in these methods ------------------------------------------
  //
  // at the moment, they are stubs returning false 
  // or some appropriate "fake" value
  //
  // you make them work properly
  // add the meat of correct implementation logic to them

  // you MAY change the signatures if you wish...
  // make the take more or different parameters
  // have them return different types
  //
  // you may use recursive or iterative implementations


  public boolean containsNode(String s, BST_Node currentNode){
	if (currentNode.data == s) {
		return true;
	} else if (s.compareTo(currentNode.data) < 0 && currentNode.left != null) {
		return currentNode.containsNode(s, currentNode.left);
	} else if (s.compareTo(currentNode.data) > 0 && currentNode.right != null) {
		return currentNode.containsNode(s, currentNode.right);
	} else {
		return false;
	}
  }
  public boolean insertNode(String s, BST_Node currentNode){
	if (s.compareTo(currentNode.data) < 0 && currentNode.left != null) {
		return insertNode(s, currentNode.left);
	} else if (s.compareTo(currentNode.data) < 0) {
		currentNode.left = new BST_Node(s);
		return true;
	} else if (s.compareTo(currentNode.data) > 0 && currentNode.right != null) {
		return insertNode(s, currentNode.right);
	} else if (s.compareTo(currentNode.data) > 0) {
		currentNode.right = new BST_Node(s);
		return true;
	} else {
		return false;
	}
  }
  
  public boolean removeNode(String s, BST_Node currentNode, BST_Node previousNode, int direction){
	  if (s.compareTo(currentNode.data) < 0) {
		return currentNode.left.removeNode(s,  currentNode.left, currentNode, -1);
	  } else if (s.compareTo(currentNode.data) > 0) {
		  return currentNode.right.removeNode(s, currentNode.right, currentNode, 1);
	  } else if (currentNode.left == null && currentNode.right != null) {
		  currentNode = currentNode.right;
		  if (direction == 1) {
			  previousNode.right = currentNode;
		  } else {
			  previousNode.left = currentNode;
		  }
		  return true;
	  } else if (currentNode.right == null && currentNode.left != null) {
		  currentNode = currentNode.left;
		  if (direction == 1) {
			  previousNode.right = currentNode;
		  } else {
			  previousNode.left = currentNode;
		  }
		  return true;
	  } else if (currentNode.left == null && currentNode.right == null) {
		  if (direction == 1) {
			  previousNode.right = null;
		  } else {
			  previousNode.left = null;
		  }
		  currentNode = null;
		  return true;
	  } else {
		  currentNode.data = currentNode.findMin(currentNode.right).data;
		  currentNode.removeNode(currentNode.data, currentNode.right, currentNode, 1);
		  return true;
	  }
  }
 
  
  
  public BST_Node findMin(BST_Node currentNode){
	  if (currentNode.left == null) {
		return currentNode;
	} else {
		return currentNode.findMin(currentNode.left);
	}
  }
  public BST_Node findMax(BST_Node currentNode){
	if (currentNode.right == null) {
		return currentNode;
	} else {
		return currentNode.findMax(currentNode.right);
	}
  }
  public int getHeight(BST_Node currentNode){
	  if (currentNode.left == null && currentNode.right == null) {
		  return 0;
	  } else if (currentNode.left == null || currentNode.right == null) {
		  return 1;
	  } else {
		  return Math.max(getHeight(currentNode.left), getHeight(currentNode.right)) + 1;
	  }
  }
 
  // --- end fill in these methods --------------------------------------


  // --------------------------------------------------------------------
  // you may add any other methods you want to get the job done
  // --------------------------------------------------------------------
  
  public String toString(){
    return "Data: "+this.data+", Left: "+((this.left!=null)?left.data:"null")
            +",Right: "+((this.right!=null)?right.data:"null");
  }

}