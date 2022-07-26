package ae2;

public class TestQueue {
	public static void main(String[] args) {
		
		// 1(a):
		System.out.println("1(a):---------");
		MinPriorityQueueHeap heap = new MinPriorityQueueHeap(10);
		heap.insert(7);
		heap.insert(6);
		heap.insert(9);
		heap.insert(4);
		heap.showHeap();
		System.out.println("min(): " + heap.min());
		System.out.println("extractMin(): " + heap.extractMin());
		heap.showHeap();
		System.out.println("min(): " + heap.min());
		
		System.out.println("");
		
		
		// 1(b):
		System.out.println("1(b):---------");
		MinPriorityQueueBST bst = new MinPriorityQueueBST();
		bst.insert(7);
		bst.insert(6);
		bst.insert(9);
		bst.insert(4);
		
		bst.traverseInOrder();
		//System.out.println("height of bst: " + bst.height(bst.root)); 
		
		System.out.println("root: " + bst.root.key); // can comment out
		System.out.println("min: " + bst.min(bst.root).key);
		System.out.println("extractMin(): " + bst.extractMin().key);
		
		bst.traverseInOrder();
		System.out.println("root: " + bst.root.key); //
		System.out.println("min: " + bst.min(bst.root).key);
		
		System.out.println("");
		
		
		// 1(d):
		System.out.println("1(d):---------");
		MinPriorityQueueBST queueBST = new MinPriorityQueueBST(); 
		queueBST.insert(45);
		queueBST.insert(10);
		queueBST.insert(7);
		queueBST.insert(12); 
		queueBST.insert(90); 
		
		queueBST.insert(50); // causes error
		queueBST.traverseInOrder(); //
		
		System.out.println("root: " + queueBST.root.key); // can comment out
		System.out.println("O(1) min: " + queueBST.minConstant().key);
		queueBST.traverseInOrder(); 
		System.out.println("O(1) extractMin: " + queueBST.extractMinConstant().key);
		queueBST.traverseInOrder(); 

		System.out.println("");

		// 2(a):
		System.out.println("2(a):---------");
		int[] array = { 4, 3, 2, 6 };
		MinPriorityQueueHeap queueHeap = new MinPriorityQueueHeap(array.length);
		queueHeap.buildMinHeap(array); // buildMinHeap will do the insert() for us.

		/**
		 * this section below, when uncommented, shows that the smallest rope length is
		 * always at the start of the array-based binary heap when we use a min-priority
		 * queue.
		 */
		/*
		 * queueHeap.showHeap(); // can comment this out.
		 * System.out.println("min Value: " + queueHeap.min());
		 * 
		 * queueHeap.extractMin(); queueHeap.showHeap();
		 * System.out.println("min Value: " + queueHeap.min());
		 */

		int minCost = queueHeap.getRopeMinCost();
		System.out.println("minCost: " + minCost);

		System.out.println("");

		// 2(b):
		System.out.println("2(b):---------");
		int[] array2 = { 4, 8, 3, 1, 6, 9, 12, 7, 2 };
		MinPriorityQueueHeap queueHeap2 = new MinPriorityQueueHeap(array2.length);
		queueHeap2.buildMinHeap(array2); // buildMinHeap will do the insert() for us.

		int minCost2 = queueHeap2.getRopeMinCost();
		System.out.println("minCost: " + minCost2);

	}
}
