package candyShop;

public class checkOutLine<T> implements QueueInterface<T> {
	
	private static int lineSize; //Create a variable to store the size of the array
	private T[] clientLine; //Create an array to store abstract elements
	private final int maxClients = 30; //Create a variable to limit the size of the array
	
	public checkOutLine() {
		lineSize = 0;
		clientLine = (T[]) new Object[maxClients];
	}
	
	public int getLineSize() { // Accessor to get the size of the line
		return lineSize;
	}
	
	public T getLineElement(int index) { // Accessor to get the element in the line array at a specified index
		if (lineSize == 0) {
			return null;
		}
		else {
			return clientLine[index];
		}
	}

	public boolean add(T newClient) { // Mutator that adds a client object to the back of the array
		if (!isFull()) {
			clientLine[lineSize] = newClient;
			lineSize++;
			return true;
		}
		return false;
	}
	
	public boolean remove() { // Mutator that removes a client object to the front of the array
		if (!isEmpty()) {
			clientLine[0] = null;
			sortLine(clientLine);
			lineSize--;
			return true;
		}
		return false;
	}
	
	public T poll() { // Method that removes the object at the front of the array and returns the removed object
		if (!isEmpty()) {
			T tempVal = clientLine[0];
			clientLine[0] = null;
			sortLine(clientLine);
			lineSize--;
			return tempVal;
		}
		return null;
	}
	
	public T peek() { // Method that returns the object at the front of the array
		if (!isEmpty()) {
			return clientLine[0];
		}
		return null;
	}
	
	private static void sortLine(Object[] arrLine) { // Mutator that edits the array to ensure that all elements are next to each other
		for (int i = 0; i < lineSize; i++) {
			if (arrLine[i] == null && arrLine[i + 1] != null) {
				arrLine[i] = arrLine[i + 1];
				arrLine[i + 1] = null;
			}
		}
	}
	
	private static boolean isFull() { // Method to check if the array is full
		if (lineSize == 30) {
			return true;
		}
		return false;
	}
	
	private static boolean isEmpty() { // Method to check if the array is empty
		if (lineSize == 0) {
			return true;
		}
		return false;
	}

}
