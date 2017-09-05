import static org.junit.Assert.*;

import org.junit.Test;

public class LLTest {

	@Test
	public void testSorting() {
		LL<Integer> l_list = new LL<Integer>();
		l_list.add(5);
		l_list.add(3);
		l_list.add(4);
		l_list.add(6);
		l_list.add(10);
		l_list.add(-5);
		l_list.add(1);
		l_list.add(5);
		l_list.add(3);
		l_list.add(1);
		l_list.add(5);
		l_list.add(3);
		//l_list.bubbleSort();
		//l_list.InsertionSort();
		//l_list.SelectionSort();
		//LL<Integer> test = l_list.MergeSort(l_list);
		//System.out.println(test.toString());
		//l_list.quickSort(l_list, 0, l_list.size() - 1);
		System.out.println(l_list.toString());
		LL<Integer> test = l_list.BogoSort(l_list);
		System.out.println(test.toString());
	}
	@Test
	public void testConstructor() {
		LL<String> l_list = new LL<String>();
		assertEquals("[]", l_list.toString());
		assertEquals(0, l_list.size());
	}
	@Test
	public void testAdd() {
		LL<String> l_list = new LL<String>();
		l_list.add("a");
		assertEquals("[a]", l_list.toString());
		assertEquals(1, l_list.size());
		l_list.add("b");
		assertEquals("[a, b]", l_list.toString());
		assertEquals(2, l_list.size());
		l_list.add("c");
		assertEquals("[a, b, c]", l_list.toString());
		assertEquals(3, l_list.size());
	}
	@Test
	public void testGetandSet() {
		try {
			LL<String> l_list = new LL<String>();
			l_list.set(0,"a");
		}
		catch (IndexOutOfBoundsException e){
			System.out.println("caught failure");
		}
		LL<String> l_list2 = new LL<String>();
		l_list2.add("a");
		assertEquals("a", l_list2.get(0));
		l_list2.add("b");
		assertEquals("b", l_list2.get(1));
		l_list2.add("c");
		assertEquals("c", l_list2.get(2));
		l_list2.add("d");
		assertEquals("d", l_list2.get(3));
		l_list2.set(0, "1");
		assertEquals("[1, b, c, d]", l_list2.toString());
		l_list2.set(3, "life");
		assertEquals("[1, b, c, life]", l_list2.toString());
	}
	@Test
	public void testInsertBefore() {
		LL<String> l_list = new LL<String>();
		l_list.add("a");
		l_list.add("b");
		l_list.add("c");
		l_list.add("d");
		l_list.add(0, "a");
		assertEquals("[a, a, b, c, d]", l_list.toString());
		l_list.add(4, "b");
		assertEquals("[a, a, b, c, b, d]", l_list.toString());
		l_list.add(3, "c");
		assertEquals("[a, a, b, c, c, b, d]", l_list.toString());
	}
	
	@Test
	public void testRemove() {
		LL<String> l_list = new LL<String>();
		try {
			l_list.remove(0);
		}
		catch (IndexOutOfBoundsException e) {
			System.out.println("caught failure 3");
		}
		l_list.add("a");
		l_list.add("b");
		l_list.add("c");
		l_list.add("d");
		l_list.remove(0);
		assertEquals("[b, c, d]", l_list.toString());
		l_list.remove(2);
		assertEquals("[b, c]", l_list.toString());
		l_list.remove(0);
		assertEquals("[c]", l_list.toString());
		l_list.remove(0);
		assertEquals("[]", l_list.toString());
	}
}
