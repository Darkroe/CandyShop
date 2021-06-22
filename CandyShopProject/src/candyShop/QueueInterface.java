package candyShop;

public interface QueueInterface<T> {
	/** Gets the current size of the line
	 * @return The size of the line
	 */
	public int getLineSize();
	
	/** Adds a new element to the Queue at the back
	 * Increases Queue size by 1
	 * Returns false if Queue is full
	 */
	public boolean add(T newClient);
	
	/** Removes the element from the front of the Queue
	 * Decreases Queue size by 1
	 * @return True if Queue is not empty; false otherwise
	 */
	public boolean remove();
	
	/** Removes the element from the front of the Queue
	 * Decreases Queue size by 1
	 * @return The removed element; null if Queue is empty
	 */
	public T poll();
	
	/** Sees the element at the front of the Queue
	 * Checks if the Queue is empty
	 * @return The element at the front of the Queue; null if Queue is empty
	 */
	public T peek();
}
