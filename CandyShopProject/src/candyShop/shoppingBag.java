package candyShop;

public class shoppingBag<T> implements BagInterface<T>  {
	
	private static int bagSize; //Create a variable to store the size of the array
	private T[] candyNames; //Create an array to store abstract elements
	private final int maxBagSize = 25; //Create a variable to limit the size of the array
	
	public shoppingBag() { //Initial Constructor to create the array and initialize the array size
		bagSize = 0;
		candyNames = (T[]) new Object[maxBagSize];
	}
	
	public int getSize() { //Accessor to return the array size
		return bagSize;
	}
	
	public T getElement(int index) { //Accessor to return the abstract element at a specified index
		if (bagSize == 0) {
			return null;
		}
		else {
			return candyNames[index];
		}
	}
	
	public int getNumOf(T aCandy) { //Accessor to return the frequency a certain abstract element appears
		int count = 0;
		for (int i = 0; i < bagSize; i++) {
			if (candyNames[i].equals(aCandy)) {
				count++;
			}
		}
		return count;
	}
	
	public boolean add(T newCandy) { //Mutator to add an abstract element to the array
		if (!isFull()) {
			candyNames[bagSize] = newCandy;
			bagSize++;
			return true;
		}
		return false;
	}
	
	public boolean remove(T aCandy) { //Mutator to remove all instances of a specified abstract element from the array
		int removalChecker = 0;
		if (!isEmpty()) {
			for (int i = 0; i < bagSize; i++) {
				if (candyNames[i].equals(aCandy)) {
					candyNames[i] = null;
					sortBag(candyNames);
					removalChecker++;
					bagSize--;
					i = -1;
				}
			}
			if (removalChecker > 0) {
				return true;
			}
		}
		return false;
	}
	
	public T remove() { //Mutator that removes the abstract element at index 0 of the array
		if (!isEmpty()) {
			T removedVal = candyNames[0];
			candyNames[0] = null;
			sortBag(candyNames);
			bagSize--;
			return removedVal;
		}
		return null;
	}
	
	public void clear() { //Mutator that resets the array and array size
		bagSize = 0;
		for (int i = 0; i < maxBagSize; i++) {
			candyNames[i] = null;
		}
	}
	
	public boolean contains(T aCandy) { //Method that checks to see the array contains a specified abstract element
		for (int i = 0; i < bagSize; i++) {
			if (candyNames[i].equals(aCandy)) {
				return true;
			}
		}
		return false;
	}
	
	public static void sortBag(Object[] arrBag) { //Mutator that edits the array to ensure that all elements are next to each other
		for (int i = 0; i < bagSize - 1; i++) {
			if (arrBag[i] == null && arrBag[i + 1] != null) {
				arrBag[i] = arrBag[i + 1];
				arrBag[i + 1] = null;
			}
		}
	}
	
	private static boolean isFull() { //Method to check if the array is full
		if (bagSize == 25) {
			return true;
		}
		return false;
	}
	
	private static boolean isEmpty() { //Method to check if the array is empty
		if (bagSize == 0) {
			return true;
		}
		return false;
	}

}
