package graph.impl;

public class Heap {

    private int current;

    private int[] nodes;

    public Heap(int capacity) {
	nodes = new int[capacity];
	current = 0;
    }

    public void add(int n)  {
	nodes[current] = n;
	sortBottomUp(current);
	++current;
    }

    public int[] getNodes() {
	
        return (int[]) nodes.clone();
    }

    private int getParentIndex(int child) {
	
        return (child-1)/2;
    }

    private int getFirstChildIndex(int parent) {
	
        return parent*2 + 1;
    }

    private int getSecondChildIndex(int parent) {
	
        return parent*2 + 2;
    }

    private void sortBottomUp(int childIndex) {
	int parentIndex = getParentIndex(childIndex);
	while (childIndex != 0 && nodes[parentIndex] > nodes[childIndex]) {
	    int tmp = nodes[childIndex];
	    nodes[childIndex] = nodes[parentIndex];
	    nodes[parentIndex] = tmp;

	    childIndex = parentIndex;
	    parentIndex = getParentIndex(childIndex);
	}
    }

    private void sortTopDown(int parentIndex) {
	int child1Index = getFirstChildIndex (parentIndex);
	int child2Index = getSecondChildIndex(parentIndex);
	int smallestChildIndex;
	boolean sorted = false;

	while (child1Index < current && !sorted) {
	    if (child2Index == current || nodes[child1Index] < nodes[child2Index])
		smallestChildIndex = child1Index;
	    else
		smallestChildIndex = child2Index;

	    sorted = nodes[smallestChildIndex] > nodes[parentIndex];
	    if (!sorted) {
		int tmp = nodes[smallestChildIndex];
		nodes[smallestChildIndex] = nodes[parentIndex];
		nodes[parentIndex] = tmp;

		parentIndex = smallestChildIndex;
		child1Index = getFirstChildIndex (parentIndex);
		child2Index = getSecondChildIndex(parentIndex);
	    }
	}
    }

    public int removeSmallest() {
	--current;
	int smallest = nodes[0];
	nodes[0] = nodes[current];
	sortTopDown(0);
	return smallest;
    }


}
