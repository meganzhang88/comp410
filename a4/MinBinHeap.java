package MinBinHeap_A3;

public class MinBinHeap implements Heap_Interface {
  private EntryPair[] array; //load this array
  private int size = 0;
  private static final int arraySize = 10000; //Everything in the array will initially 
                                              //be null. This is ok! Just build out 
                                              //from array[1]

  public MinBinHeap() {
    this.array = new EntryPair[arraySize];
    array[0] = new EntryPair(null, -100000); //0th will be unused for simplicity 
                                             //of child/parent computations...
                                             //the book/animation page both do this.
  }
    
  //Please do not remove or modify this method! Used to test your entire Heap.
  @Override
  public EntryPair[] getHeap() { 
    return this.array;
  }

@Override
public void insert(EntryPair entry) {
		size++;
		int i = size;
		for (array[0] = entry; entry.priority < array[i / 2].priority; i /= 2) {
			array[i] = array[i / 2];
		}
		array[i] = entry;
}

@Override
public void delMin() {
	if (size == 0) {
	} else {
		array[1] = array[size];
		size--;
		percolateDown(1);
	}
}

@Override
public EntryPair getMin() {
	if (size == 0) {
		return null;
	} else {
		return array[1];
	}
}

@Override
public int size() {
	return size;
}

public void percolateDown(int x) {
	int child;
	EntryPair temp = array[x];
	
	for (; x * 2 <= size; x = child) {
		child = x * 2;
		if (child != size && array[child + 1].priority < array[child].priority) {
			child++;
		}
		if (array[child].priority < temp.priority) {
			array[x] = array[child];
		} else {
			break;
		}
	}
	array[x] = temp;
}

@Override
public void build(EntryPair[] entries) {
	int i = 1;
	for (int k = 0; k < entries.length; k++) {
		array[i++] = entries[k];
	}
	size = entries.length;
	for (int j = size / 2; j > 0; j--) {
		percolateDown(j);
	}
	
}
}