import graph.impl.Heap;
import org.junit.Before;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class HeapTest {
    private Heap heap;

    @Before
    public void setUp() {
	heap = new Heap(13);
    }

    @Test
    public void addTest() {
	heap.add(11);
	assertEquals(11, heap.getNodes()[0]);

	heap.add(8);
	assertEquals(8 , heap.getNodes()[0]);
	assertEquals(11, heap.getNodes()[1]);

	heap.add(9);
	assertEquals(8 , heap.getNodes()[0]);
	assertEquals(11, heap.getNodes()[1]);
	assertEquals(9 , heap.getNodes()[2]);

	heap.add(4);
	assertEquals(4 , heap.getNodes()[0]);
	assertEquals(8 , heap.getNodes()[1]);
	assertEquals(9 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);

	heap.add(5);
	assertEquals(4 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(9 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(8 , heap.getNodes()[4]);

	heap.add(0);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(4 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(8 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);

	heap.add(2);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(8 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);

	heap.add(6);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(8 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);

	heap.add(10);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(8 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);

	heap.add(1);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(1 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(5 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);

	heap.add(4);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(1 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(4 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);
	assertEquals(5 , heap.getNodes()[10]);

	heap.add(3);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(1 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(4 , heap.getNodes()[4]);
	assertEquals(3 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);
	assertEquals(5 , heap.getNodes()[10]);
	assertEquals(9 , heap.getNodes()[11]);

	heap.add(7);
	assertEquals(0 , heap.getNodes()[0]);
	assertEquals(1 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(4 , heap.getNodes()[4]);
	assertEquals(3 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);
	assertEquals(5 , heap.getNodes()[10]);
	assertEquals(9 , heap.getNodes()[11]);
	assertEquals(7 , heap.getNodes()[12]);
    }

    @Test
    public void removeSmallestTest() {
	heap.add(11);
	heap.add(8);
	heap.add(9);
	heap.add(4);
	heap.add(5);
	heap.add(0);
	heap.add(2);
	heap.add(6);
	heap.add(10);
	heap.add(1);
	heap.add(4);
	heap.add(3);
	heap.add(7);

	assertEquals(0, heap.removeSmallest());
	assertEquals(1 , heap.getNodes()[0]);
	assertEquals(4 , heap.getNodes()[1]);
	assertEquals(2 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(5 , heap.getNodes()[4]);
	assertEquals(3 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);
	assertEquals(7 , heap.getNodes()[10]);
	assertEquals(9 , heap.getNodes()[11]);

	assertEquals(1, heap.removeSmallest());
	assertEquals(2 , heap.getNodes()[0]);
	assertEquals(4 , heap.getNodes()[1]);
	assertEquals(3 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(5 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(4 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);
	assertEquals(7 , heap.getNodes()[10]);

	assertEquals(2, heap.removeSmallest());
	assertEquals(3 , heap.getNodes()[0]);
	assertEquals(4 , heap.getNodes()[1]);
	assertEquals(4 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(5 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(7 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);
	assertEquals(8 , heap.getNodes()[9]);

	assertEquals(3, heap.removeSmallest());
	assertEquals(4, heap.getNodes()[0]);
	assertEquals(4 , heap.getNodes()[1]);
	assertEquals(7 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(5 , heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(8 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);
	assertEquals(10, heap.getNodes()[8]);

	assertEquals(4, heap.removeSmallest());
	assertEquals(4 , heap.getNodes()[0]);
	assertEquals(5 , heap.getNodes()[1]);
	assertEquals(7 , heap.getNodes()[2]);
	assertEquals(6 , heap.getNodes()[3]);
	assertEquals(10, heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(8 , heap.getNodes()[6]);
	assertEquals(11, heap.getNodes()[7]);

	assertEquals(4, heap.removeSmallest());
	assertEquals(5 , heap.getNodes()[0]);
	assertEquals(6 , heap.getNodes()[1]);
	assertEquals(7 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(10, heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);
	assertEquals(8 , heap.getNodes()[6]);

	assertEquals(5, heap.removeSmallest());
	assertEquals(6 , heap.getNodes()[0]);
	assertEquals(8 , heap.getNodes()[1]);
	assertEquals(7 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(10, heap.getNodes()[4]);
	assertEquals(9 , heap.getNodes()[5]);

	assertEquals(6, heap.removeSmallest());
	assertEquals(7 , heap.getNodes()[0]);
	assertEquals(8 , heap.getNodes()[1]);
	assertEquals(9 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);
	assertEquals(10, heap.getNodes()[4]);

	assertEquals(7, heap.removeSmallest());
	assertEquals(8 , heap.getNodes()[0]);
	assertEquals(10, heap.getNodes()[1]);
	assertEquals(9 , heap.getNodes()[2]);
	assertEquals(11, heap.getNodes()[3]);

	assertEquals(8, heap.removeSmallest());
	assertEquals(9 , heap.getNodes()[0]);
	assertEquals(10, heap.getNodes()[1]);
	assertEquals(11, heap.getNodes()[2]);

	assertEquals(9, heap.removeSmallest());
	assertEquals(10, heap.getNodes()[0]);
	assertEquals(11, heap.getNodes()[1]);

	assertEquals(10, heap.removeSmallest());
	assertEquals(11, heap.getNodes()[0]);

	assertEquals(11, heap.removeSmallest());
    }
}