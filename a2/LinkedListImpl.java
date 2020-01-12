/**
 * COMP 410
 *See inline comment descriptions for methods not described in interface.
 *
*/
package LinkedList_A1;

public class LinkedListImpl implements LIST_Interface {
  Node sentinel; //this will be the entry point to your linked list (the head)
  
  public LinkedListImpl(){//this constructor is needed for testing purposes. Please don't modify!
    sentinel=new Node(0); //Note that the root's data is not a true part of your data set!
  }
  
  //implement all methods in interface, and include the getRoot method we made for testing purposes. Feel free to implement private helper methods!
  
  public Node getRoot(){ //leave this method as is, used by the grader to grab your linkedList easily.
    return sentinel;
  }

@Override
public boolean insert(double elt, int index) {
	// TODO Auto-generated method stub
	Node newNode = new Node(elt);
	int length = this.size();
	if (index > length || index < 0) {
		return false;
	} else if (length == 0) {
		sentinel.next = newNode;
		sentinel.prev = newNode;
		newNode.prev = sentinel;
		newNode.next = sentinel;
	} else {
		Node currentNode = sentinel;
		for (int i = 0; i < index; i++) {
			currentNode = currentNode.next;
		}
		newNode.prev = currentNode;
		newNode.next = currentNode.next;
		newNode.prev.next = newNode;
		newNode.next.prev = newNode;
	}
	return true;
}

@Override
public boolean remove(int index) {
	// TODO Auto-generated method stub
	int length = this.size();
	if (index >= length) {
		return false;
	}
	Node currentNode = sentinel;
	for (int i = 0; i <= index; i++) {
		currentNode = currentNode.next;
	}
	currentNode.prev.next = currentNode.next;
	currentNode.next.prev = currentNode.prev;
	return true;
}

@Override
public double get(int index) {
	// TODO Auto-generated method stub
	int length = this.size();
	if (index >= length || index < 0) {
		return Double.NaN;
	}
	Node currentNode = sentinel;
	for (int i = 0; i <= index; i++) {
		currentNode = currentNode.next;
	}
	return currentNode.data;
}

@Override
public int size() {
	// TODO Auto-generated method stub
	int size = 0;
	if (sentinel.next == null) {
		return 0;
	}
	Node currentNode = sentinel;
	while (currentNode.next != sentinel) {
		size++;
		currentNode = currentNode.next;
	}
	return size;
}

@Override
public boolean isEmpty() {
	// TODO Auto-generated method stub
	if (this.size() == 0) {
		return true;
	} else {
		return false;
	}
}

@Override
public void clear() {
	// TODO Auto-generated method stub
	sentinel.next = null;
	sentinel.prev = null;
}
}