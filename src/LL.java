import java.util.Comparator;
import java.util.Random;

/** An instance is a linked list. This is a side project I did, simplifying the doubly linked
 * list we made for assignment a3 (CS 2110, spring 2016) into a singly linked list. I did this because I was familiar 
 * with the list data structure and wanted to be able to put my implementation of sorting 
 * algorithms for the list structure on my github without concerns of academic integrity at Cornell. 
 */
public class LL<V extends Comparable<V>> implements Comparator<V> {
	private int size;  // number of values in the list.
    private Node head; // first node of the linked list (null if list is empty)
    
    /** Constructor: an empty linked list. */
    public LL() {
        size= 0;
    }
    
    /** = the number of values in this list.*/
    public int size() {
        return size;
    }
    
    /** If these linked lists are equal, then they have the same
     * size and their values at each index should be equal else the linked lists 
     * are not equal */
    public boolean equal(LL<V> l1, LL<V> l2) {
    	boolean equals = true;
    	if (l1.size() != l2.size()) {equals = false;}
    	else {
    		for (int i = 0; i < l1.size(); i++) {
    			if (l1.get(i).compareTo(l2.get(i)) != 0) {
    				equals = false;
    			}
    		}
    	}
    	return equals;
    }
    
    /** Return a string representation of this list */
    public @Override String toString() {
        String res= "[";
        for (Node n= head; n != null; n= n.next) {
            if (res.length() > 1)
                res= res + ", ";
            res= res + n.value;
        }
        return res + "]";
    }
    
    /** Append val to the end of this list. */
    public void add(V val) {
       if (size == 0) {
    	   //you must use the new keyword to instantiate new objects
    	   head= new Node(val, null);
    	   size++;
       }
       else {
    	   //go through the whole list, add at the end
    	   Node last = null;
    	   for (Node n= head; n != null; n= n.next) {
    		   last=n;
    	   }
           Node tail= new Node(val, null);
           last.next= tail;
           size++;
       }
    }
    
    /** Return Node number h of the linked list (not the value, which is this[h]).
     * @throws IndexOutOfBoundsException if h < 0 or h >= size of the list. */
    /* package */ Node getNode(int h) {
        if (h < 0 || h >= size) {throw new IndexOutOfBoundsException();}
        // h >= 0 && h < size
        //start from head of list
       	int count= 0;
       	Node at_ind= head;
       	while (count != h) {
       		at_ind= at_ind.next;
       		count= count+1;
       	}
       	return at_ind;
    }
    
    /** Return this[h] --value number h of the list.
     * @throws IndexOutOfBoundsException if h < 0 or h >= size of the list.*/
    public V get(int h) {
       return getNode(h).value;
    }
    
    /** Return value this[h] and replace it by val.
     * @throws IndexOutOfBoundsException if h < 0 or h >= size of the list.*/
    public V set(int h, V val) {
        Node n= getNode(h);
        V to_return= n.value;
        n.value= val;
        return to_return;
    }
    
    /** Insert val into a new Node before Node n of this list and return the new Node.
     * Precondition: n must be an Element of this list; it must not be null.*/
    public Node insertBefore(V val, Node n) {
    	if (n == head) {
    		head = new Node(val,n);
    		size++;
    		return head;
    	}
    	else {
    		int count= 0;
    		Node at_count= head;
           	while (at_count != n) {
           		at_count= at_count.next;
           		count= count+1;
           	}
           	//count is now the index of n
           	Node inserted= new Node(val, n);
           	getNode(count-1).next = inserted;
           	size++;
           	return inserted;
    	}
    }
    
    /** Insert val as this[h+1]; thus, this[h+1] becomes this[h+2..].
     * If h = size of the list, this means to append val to the list.
     * @throws IndexOutOfBoundsException if h < 0 or h > size of the list.*/
    public void add(int h, V val) {
    	if (h == size) {add(val);}
    	else {
    		Node n= getNode(h);
    		insertBefore(val, n);
    	}
    }
    
    /** Remove this[h] from the list and return the value that was removed.
     * @throws IndexOutOfBoundsException if h < 0 or h >= size of the list. */
    public V remove(int h) {
    	Node n= getNode(h); //will throw index exceptions
        if (size == 1) {
        	head= null;
        }
        else {
        	 //size > 1
            Node after= n.next;
            if (h == 0) {
            	head= after;
            }
            else {
            	Node before = getNode(h-1);
            	before.next = after;
            }
        }
        size--;
        V v= n.value;
        n.value= null;
        n.next= null;
        return v;
    }
    
    /** Swap the values of the elements of the doubly linked 
     * list at index 1 and index 2
     */
    public void swap(LL<V> list, int index1, int index2) {
    	V i = list.get(index1);
    	list.set(index1, list.get(index2));
    	list.set(index2,i);
    }
    
    /** Bubble Sort: comparison based algorithm in which each pair of adjacent elements is compared and 
     * the elements are swapped if they are not in order. Average and worst case complexity are O(n^2). When
     * there's no swap required, the array is sorted. 
     */
    public LL<V> bubbleSort() {
    	int length = this.size;
    	//assume the list isn't sorted
    	boolean swapped;
    	do {
    		swapped = false;
    		for (int i = 1; i < length; i++) {
    			/* if this pair is out of order */
    			if (this.get(i-1).compareTo(this.get(i)) < 0) {
    				/* swap and track the swap */
    				swap(this, i-1, i);
    				swapped = true;
    			}
    		}
    	} while (swapped);
    	return this;
    }
    
    /** Insertion Sort: Sub list is maintained which is always sorted. An element which is to be inserted in this 
     * sorted sublist has to find its appropriate place and then be inserted there. The array is searched sequentially 
     * and unsorted items are moved and inserted into the sorted sub-list. Take the first element outside the 
     * sorted section. Compare it with the element after. If not in order, swap and then compare it with the element
     * before, until the element is in its place. Average and worst case time complexity of O(n^2)
     */
    public LL<V> InsertionSort() {
    	int i = 1;
    	while (i < this.size) {
    		int j = i;
    		while (j > 0 && this.get(j-1).compareTo(this.get(j)) > 0) {
    			swap(this, j, j-1);
    			j--;
    		}
    	    i++;
    	}
    	return this;
    }
    
    /** Selection Sort: List is divided into two parts, sorted part at the left end and the unsorted part at the right
     * end. Initially the sorted part is empty and the unsorted part is the entire list. Smallest element is selected 
     * from the unsorted array and swapped with the leftmost element, and that element becomes a part of the sorted
     * array. This process continues moving the unsorted array boundary by one element to the right. Its average and
     * worst case time complexities are O(n^2)
     */
    public LL<V> SelectionSort() {
    	int i, j;
    	int n = this.size;
    	for (j = 0; j < n -1; j++) {
    		int min = j;
    		for (i = j+1; i < n; i++) {
    			if (this.get(i).compareTo(this.get(min)) < 0) {
    				min = i;
    			}
    		}
    		if (min != j) {
    			swap(this, j, min);
    		}
    	}
    	return this;
    }

    /** Merge Sort: Worst case time complexity of O(nlogn). Mergesort first divides the array into two equal halves 
     * and then combines them in a sorted manner using recusion.
     */
    public LL<V> MergeSort(LL<V> list) {
    	if (list.size() <= 1) {
    		return list;
    	}
    	LL<V> left = new LL<V>();
    	LL<V> right = new LL<V>();
    	for (int i = 0; i < list.size(); i++) {
    		V val = list.get(i);
    		if (i < list.size()/2) {
    			left.add(val);
    		}
    		else {
    			right.add(val);
    		}
    	}
    	left = MergeSort(left);
    	right = MergeSort(right);
    	return merge(new LL<V>(), left, right);
    }
    
    public LL<V> merge(LL<V> result, LL<V> left, LL<V> right) {
    	while (left.size() != 0 && right.size() != 0) {
    		if (left.get(0).compareTo(right.get(0)) <= 0) {
    				result.add(left.get(0));
    				left.remove(0);
    		}
    		else {
    			result.add(right.get(0));
    			right.remove(0);
    		}
    	}
    	while (left.size != 0) {
    		result.add(left.get(0));
    		left.remove(0);
    	}
    	while (right.size != 0) {
    		result.add(right.get(0));
    		right.remove(0);
    	}
    	return result;
    }
    
    /** Quick Sort: Worst case time is O(n^2). Worst case space is O(logn). A large array is partitioned into two arrays,
     * one of which holds values smaller than the specified value, say pivot, based on which the partitin is 
     * made and another array holds values greater than the pivot value. Repeat this using recursion until you have
     * a list of size 1 or 0, the base cases.
     */
    public void quickSort(LL<V> toSort, int lo, int hi) {
    	if (lo < hi) {
    		int p = partition(toSort, lo, hi);
    		quickSort(toSort, lo, p-1);
    		quickSort(toSort, p+1, hi);
    	}
    }
    
    public int partition(LL<V> toSort, int lo, int hi) {
    	V pivot = toSort.get(hi);
    	int i = lo-1;
    	for (int j = lo; j < hi; j++) {
    		if (toSort.get(j).compareTo(pivot) < 0) {
    			i++;
    			swap(toSort, i, j);
    		} 
    	}
    	if (toSort.get(hi).compareTo(toSort.get(i+1)) <= 0) {
			swap(toSort, i+1, hi);
		}
    	return i + 1;
    }
    
    /** Bogosort: The algorithm generates permutations of the its input 
     * list until it finds one that is sorted */
    public LL<V> BogoSort(LL<V> toSort) {
    	long startTime = System.nanoTime();
    	LL<V> storedList = new LL<V>();
    	for (int i = 0; i < toSort.size; i++) {
    		storedList.add(toSort.get(i));
    	}
    	LL<V> randList = new LL<V>();
    	boolean sorted = isSorted(toSort);
    	while (!sorted) {
    		//shuffle the list
    		Random rand = new Random();
        	while (toSort.size() > 0) {
        		int randIndex = rand.nextInt(toSort.size());
        		//pull value at randIndex from hat
        		randList.add(toSort.get(randIndex));
        		toSort.remove(randIndex);
        	}
        	if (isSorted(randList)) {
        		sorted= true;
        		toSort = new LL<V>();
        		for (int i = 0; i < randList.size(); i++) {
        			toSort.add(randList.get(i));
        		}
        	}
        	else {
        		toSort = new LL<V>();
        		randList = new LL<V>();
        		for (int i = 0; i < storedList.size(); i++) {
        			toSort.add(storedList.get(i));
        		}
        	}
    	}
    	long estimatedTime = System.nanoTime() - startTime;
    	System.out.println(estimatedTime/(1000000000.0));
    	return toSort;
    }
    
    /**Return true if this list @list is sorted */
    public boolean isSorted(LL<V> list) {
    	boolean sorted = true;
    	for (int i = 0; i < list.size()-1; i++) {
    		if (list.get(i).compareTo(list.get(i+1)) > 0) {
    			sorted = false; 
    		}
    	}
    	return sorted;
    }
    
    /**Comparison function, using comparable class method  */
    public int compare(V a, V b) {
        return a.compareTo(b);
    }
    
    /** An instance is a Node of this linked list. Node class based on the Node
     * class from assignment a3 in CS 2110. */
    /* package */ class Node {
        /** The value in this element. */
        /* package */ V value;

        /** Successor of this Node on list. (null if this is last Node). */
        /* package */ Node next;

        /** Constructor: an instance with predecessor node p (can be null),
          *  successor node s (can be null), and value val. */
        /* package */ Node(V val, Node s) {
            next= s;
            value= val;
        }
    }
}
