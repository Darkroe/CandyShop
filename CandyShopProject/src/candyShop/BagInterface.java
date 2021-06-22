package candyShop;

public interface BagInterface<T> {
	
	/** Gets the number of elements in the client's shopping bag
	 * @return  The number of elements in the client's shopping bag 
	 */
	public int getSize();
	
	/** Gets the number of times a specific Candy appears in the client's shopping bag
	 * @param aCandy
	 * @return The number of times a specific Candy appears in the client's shopping bag
	 */
	public int getNumOf(T aCandy);
	
	/** Adds a new Candy to the client's shopping bag
	 * Checks if the client's shopping bag is full
	 * @param newCandy
	 * @return  True if the add was successful; false otherwise 
	 */
	public boolean add(T newCandy);
	
	/** Removes a specific Candy from the client's shopping bag
	 * Checks if the client's shopping bag is empty
	 * Fails to remove if shopping bag is empty
	 * @param aCandy
	 * @return  True if the removal was successful; false otherwise 
	 */
	public boolean remove(T aCandy);
	
	/** Removes a random Candy from the client's shopping bag
	 * @return The removed Candy, or null if the bag is empty
	 */
	public T remove();
	
	/** Removes all Candies from the client's shopping bag
	 */
	public void clear();
	
	/** Checks if the client's shopping bag contains a specific Candy
	 * @param aCandy
	 * @return True if the specific Candy is in the cleint's bag; false otherwise
	 */
	public boolean contains(T aCandy);
}
