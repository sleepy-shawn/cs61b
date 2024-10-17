package synthesizer;
import java.util.Iterator;

//TODO: Make sure to make this class and all of its methods public

public class ArrayRingBuffer<T> extends AbstractBoundedDeque<T> {
	/* Index for the next dequeue or peek. */
	private int first;            // index for the next dequeue or peek
	/* Index for the next enqueue. */
	private int last;
	/* Array for storing the buffer data. */
	private T[] rb;

	/**
	 * Create a new ArrayRingBuffer with the given capacity.
	 */
	public ArrayRingBuffer(int capacity) {
		// TODO: Create new array with capacity elements.
		//       first, last, and fillCount should all be set to 0.
		//       this.capacity should be set appropriately. Note that the local variable
		//       here shadows the field we inherit from AbstractBoundedQueue, so
		//       you'll need to use this.capacity to set the capacity.
		rb = (T[]) new Object[capacity];
		last = 0;
		first = 0;
		fillCount = 0;
		this.capacity = capacity;
	}

	/**
	 * Adds x to the end of the ring buffer. If there is no room, then
	 * throw new RuntimeException("Ring buffer overflow"). Exceptions
	 * covered Monday.
	 */
	@Override
	public void enqueue(T x) {
		if (isEmpty()) {
			first = (first -1 + capacity) % capacity;
		}
		rb[last] = x;
		last = (last + 1) % capacity;
		fillCount = fillCount + 1;
		// TODO: Enqueue the item. Don't forget to increase fillCount and update last.
	}

	/**
	 * Dequeue oldest item in the ring buffer. If the buffer is empty, then
	 * throw new RuntimeException("Ring buffer underflow"). Exceptions
	 * covered Monday.
	 */

	@Override
	public T dequeue() {
		first =(first + 1) % capacity;
		capacity = capacity - 1;
		return rb[first];
		// TODO: Dequeue the first item. Don't forget to decrease fillCount and update
	}

	/**
	 * Return oldest item, but don't remove it.
	 */

	@Override
	public T peek() {
		return rb[(first + 1) % capacity];
		// TODO: Return the first item. None of your instance variables should change.
	}

	// TODO: When you get to part 5, implement the needed code to support iteration.
}
