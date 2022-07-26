package ae2;

public class MinPriorityQueueHeap {
	/**
	 * This is an array based binary heap implementation of min priority queue. The
	 * Min priority Queue is an abstract data type (ADT) for maintaining a
	 * collection of elements, each with an associated value called a key.
	 * INSERT(Q,x): insert the element x into the queue Q. MIN(Q): returns the
	 * element of Q with the smallest key. EXTRACT-MIN (Q): removes and returns the
	 * element of Q with the smallest key.
	 */

	public int maxSize, currentSize; // maxSize = capacity
	public int[] minHeap;
	public int[] tempHeap; // for the getRopeMinCost
	public int tempCurrentSize; // for the getRopeMinCost

	public MinPriorityQueueHeap(int maxSize) {
		this.maxSize = maxSize;
		minHeap = new int[maxSize + 1];
		currentSize = 0;
	}

	public void buildMinHeap(int[] array) {
		if (array.length > 0) {
			for (int index = 0; index < array.length; index++) {
				insert(array[index]);
			}
		}
	}

	public void showHeap() {
		for (int index = 1; index < minHeap.length; index++) {
			System.out.print(minHeap[index] + " ");
		}
		System.out.println("");
	}

	public void insert(int value) {
		if (currentSize == maxSize) {
			System.out.println("overflow - the heap is full");
		} else {
			currentSize++;
			minHeap[currentSize] = value;
			moveUp(currentSize);
		}
	}

	public void moveUp(int currentPosition) {
		int parentPosition = currentPosition / 2;
		while (currentPosition > 0 && minHeap[parentPosition] > minHeap[currentPosition]) {
			swap(currentPosition, parentPosition);
			currentPosition = parentPosition;
			parentPosition = parentPosition / 2;
		}
	}

	public void swap(int position1, int position2) {
		int temp = minHeap[position1];
		minHeap[position1] = minHeap[position2];
		minHeap[position2] = temp;
	}

	public int min() {
		/** return min value of the heap. */
		if (currentSize != 0) {
			return minHeap[1];
		}
		System.out.println("underflow - heap is empty");
		return -1;
	}

	public int extractMin() {
		if (currentSize == 0) {
			System.out.println("underflow - heap is empty");
			return -1;
		} else {
			int minValue = minHeap[1];
			minHeap[1] = minHeap[currentSize];
			minHeap[currentSize] = 0;
			moveDown(1);
			currentSize--;
			return minValue;
		}
	}
	
	public void moveDown(int index) { // same as minHeapify
		int smallest = index;
		int leftChild = 2*index;
		int rightChild = (2*index)+1;
		if (leftChild < currentSize && minHeap[smallest] > minHeap[leftChild]) {
			smallest = leftChild;
		}
		if (rightChild < currentSize && minHeap[smallest] > minHeap[rightChild]) {
			smallest = rightChild;
		}
		if (smallest != index) {
			swap(index, smallest);
			moveDown(smallest);
		}
	}
	
	public int getRopeMinCost() {
		int minCost = 0;
		System.out.println("heap at start:");
		showHeap();
		while (currentSize != 1) {
			int minRopeLength = extractMin();
			int secondMinRopeLength = extractMin();
			int newRopeLength = minRopeLength + secondMinRopeLength;
			minCost += newRopeLength;
			insert(newRopeLength);
			System.out.println("newly connected rope's length: " + newRopeLength);
			System.out.println("current heap:");
			showHeap();
		}
		return minCost;
	}

	/*
	 * int[] array;
	 * 
	 * public MinPriorityQueueHeap(int[] array) { this.array = array; }
	 * 
	 * private int left(int index) { return (2*index) +1; }
	 * 
	 * private int right(int index) { return (2*index) + 2; }
	 * 
	 * private void buildMinHeap(int[] array) { for (int index=0; index <=
	 * (array.length/2)-1; index++) { minHeapify(array, index, array.length); } }
	 * 
	 * private void minHeapify(int[] array, int index, int size) { int left =
	 * left(index), right = right(index), smallest; if ((left < size) &&
	 * (array[left] < array[index])) { smallest = left; } else { smallest = index; }
	 * if ((right < size) && (array[right] < array[smallest])) { smallest = right; }
	 * if (smallest != index) { swap(array, index, smallest); minHeapify(array,
	 * smallest, size); } }
	 * 
	 * private void swap(int[] array, int index1, int index2) { int temp =
	 * array[index1]; array[index1] = array[index2]; array[index2] = temp; }
	 * 
	 * private void heapSort(int[] array) { buildMinHeap(array); int size =
	 * array.length; // size of the heap for (int index=1; index <= array.length-1;
	 * index++) { swap(array, 0, index); size--; minHeapify(array, 0, size); } }
	 * 
	 * /*private int[] queue; private int sizeOfQueue, maxSizeOfQueue; private
	 * static final int FRONT = 1;
	 * 
	 * public MinPriorityQueueHeap(int maxSizeOfQueue) { this.maxSizeOfQueue =
	 * maxSizeOfQueue; this.sizeOfQueue = 0;
	 * 
	 * queue = new int[this.maxSizeOfQueue+1]; queue[0] = Integer.MIN_VALUE; }
	 * 
	 * private int parent(int position) { return position/2; }
	 * 
	 * private int leftChild(int position) { return 2*position; }
	 * 
	 * private int rightChild(int position) { return (2*position) + 1; }
	 * 
	 * private boolean isLeaf(int position) { if ((position > (sizeOfQueue/2)) &&
	 * (position <= sizeOfQueue)) { return true; } return false; }
	 * 
	 * private void swap(int position1, int position2) { int temp =
	 * queue[position1]; queue[position1] = queue[position2]; queue[position2] =
	 * temp; }
	 * 
	 * private void minHeapify(int position) { if (!isLeaf(position)) { if
	 * ((queue[position] > queue[leftChild(position)]) || (queue[position] >
	 * queue[rightChild(position)])) { if (queue[leftChild(position)] <
	 * queue[rightChild(position)]) { swap(position, leftChild(position));
	 * minHeapify(leftChild(position)); } else { swap(position,
	 * rightChild(position)); minHeapify(rightChild(position)); } } } }
	 * 
	 * public void insert(int element) { if (sizeOfQueue >= maxSizeOfQueue-1) {
	 * System.out.println("overflow"); return; } sizeOfQueue++; queue[sizeOfQueue] =
	 * element; int currentPosition = sizeOfQueue; while (queue[currentPosition] <
	 * queue[parent(currentPosition)]) { swap(currentPosition,
	 * parent(currentPosition)); currentPosition = parent(currentPosition); } }
	 * 
	 * public int getRopeMinCost() { int minCost = 0; while (sizeOfQueue != 1) { int
	 * minRopeLength = extractMin(); int secondMinRopeLength = extractMin(); int
	 * newRopeLength = minRopeLength + secondMinRopeLength; minCost +=
	 * newRopeLength; insert(newRopeLength); } return minCost; }
	 * 
	 * /*public void minHeapify(int index) { /** To ensure heap property is
	 * satisfied. For min heap, the heap property is that parent node is smaller
	 * than child node for all nodes, and for max heap parent node greater than
	 * child node.
	 * 
	 * 
	 * int left = getLeftIndex(index), right = getRightIndex(index), smallest=index;
	 * 
	 * if ((left < sizeOfQueue) && (this.queue[left] < this.queue[smallest])) {
	 * smallest = left; } else if ((right < sizeOfQueue) && (this.queue[right] <
	 * this.queue[smallest])) { smallest = right; }
	 * 
	 * if (smallest != index) { swap(index, smallest); minHeapify(smallest); } }
	 * 
	 * public int getLeftIndex(int index) { return (2 * index) + 1; }
	 * 
	 * public int getRightIndex(int index) { return (2 * index) + 2; }
	 * 
	 * public void swap(int index, int smallest) { int temp = this.queue[index];
	 * this.queue[index] = this.queue[smallest]; this.queue[smallest] = temp; }
	 * 
	 * public void buildMinHeap() { for (int index = 0; index <= (numOfElements / 2)
	 * - 1; index++) { minHeapify(index); } }
	 * 
	 * /** Q.head indexes the element at its head; Q.tail indexes the next location
	 * at which a new element will be inserted into the queue; array Q[0..n-1]
	 * implements a queue of at most n - 1 elements; Q[Q.tail] is kept empty; when
	 * Q.head = Q.tail the queue is empty.
	 * 
	 * 
	 * 
	 * public void insert(int elementToInsert) { sizeOfQueue++; if (sizeOfQueue ==
	 * numOfElements - 1) { System.out.println("overflow"); } else {
	 * this.queue[sizeOfQueue-1] = elementToInsert; moveDown(sizeOfQueue-1,
	 * elementToInsert); //this.tail = (this.tail + 1) % (numOfElements); }
	 * 
	 * }
	 * 
	 * public void moveDown(int element, int insertedElement) { this.queue[element]
	 * = insertedElement; while (element > 0 && this.queue[element] <
	 * this.queue[parent(element)]) { swap(element, parent(element)); element =
	 * parent(element); } }
	 * 
	 * public int queueSize() { return (numOfElements - head + this.tail) %
	 * (numOfElements); }
	 * 
	 * public boolean queueEmpty() { return head == this.tail; }
	 * 
	 * public void buildMinHeap() { for (int index = 0; index <= (sizeOfQueue /
	 * 2)-1; index++) { minHeapify(index); } }
	 * 
	 * public Integer extractMin() { /** removes and returns smallest element. if
	 * (sizeOfQueue == 0) { System.out.println("underflow"); return null; } else {
	 * int minElementToRemove = min(); queue[0] = queue[sizeOfQueue-1];
	 * sizeOfQueue--; //swap(this.tail, head); minHeapify(0); // minHeapify and not
	 * buildMinHeap bc the former does change order just // for this swap of tail
	 * and head whether buildMinHeap does it for all the rest // of queue/
	 * //this.tail--; // 'remove tail element' by just moving the tail pointer.
	 * return minElementToRemove; } }
	 * 
	 * public Integer min() { /** returns smallest element without removing it. if
	 * (sizeOfQueue == 0) { System.out.println("underflow"); return null; } else {
	 * return this.queue[0]; } }
	 */
}
