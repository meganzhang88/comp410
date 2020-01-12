package BST_A2;

public class BST implements BST_Interface {
  public BST_Node root;
  int size;
  
  public BST(){ size=0; root=null; }
  
  @Override
  //used for testing, please leave as is
  public BST_Node getRoot(){ return root; }

@Override
public boolean insert(String s) {
	if (s == null) {
		return false;
	} else if (contains(s)) {
		return false;
	} else if (root == null) {
		root = new BST_Node(s);
		size++;
		return true;
	} else {
		size++;
		return root.insertNode(s, root);
	}
}

@Override
public boolean remove(String s) {
	if (s == null) {
		return false;
	} else if (!contains(s)) {
		return false;
	} else if (root == null) {
		return false;
	} else if (size == 1 && root.data == s) {
		root = null;
		size = 0;
		return true;
	} else if (root.data == s && (root.right == null && root.left != null)) {
		root = root.left;
		size--;
		return true;
	} else if (root.data == s && (root.right != null && root.left == null)) {
		root = root.right;
		size--;
		return true;
	} else {
		size--;
		return root.removeNode(s, root, root, 0);
	}
}

@Override
public String findMin() {
	if (root == null) {
		return null;
	} else {
		return root.findMin(root).data;
	}
}

@Override
public String findMax() {
	if (root == null) {
		return null;
	} else {
		return root.findMax(root).data;
	}
}

@Override
public boolean empty() {
	if (size == 0) {
		return true;
	} else if (root == null) {
		return true;
	} else {
		return false;
	}
}

@Override
public boolean contains(String s) {
	if (s == null) {
		return false;
	} else if (root == null) {
		return false;
	}
	return root.containsNode(s, root);
}

@Override
public int size() {
	return size;
}

@Override
public int height() {
	if (root == null) {
		return -1;
	}
	return root.getHeight(root);
}

}
