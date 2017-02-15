package edu.iit.cs542;

/* 
 * Fixed front implementation of a Queue using arrays */

public class CS542Queue<E>  {
	private E[] data;
	private int front, back;
	private int capacity;

	/**
	 * Constructor
	 * 
	 * @param num_elems
	 */
	public CS542Queue(int num_elems) {
		capacity = num_elems;
		data = (E[]) new Object[capacity];
		front = back = 0;
	}

	/**
	 * Add the new element into the end of the queue Enqueue operation
	 * 
	 * @param element
	 */
	public void add(E element) {
		if (is_full()) {
			capacity *= 2;
			E[] newData = (E[]) new Object[capacity];
			for (int i = 0; i < capacity / 2; i++) {
				newData[i] = data[i];
			}
			data = newData;
			data[back] = element;
		} else {
			data[back] = element;
		}
		back++;
	}

	/**
	 * Remove the frist element in the front of the queue Dequeue operation
	 * 
	 * @return
	 */
	public E remove() {
		if (is_empty()) {
			return null;
		} else {
			E oe = data[0];
			for (int i = 1; i < back; i++) {
				data[i - 1] = data[i];
			}
			back--;
			return oe;
		}

	}

	/**
	 * Return the first element value in the font of the queue without remove
	 * it.
	 * 
	 * @return
	 */
	public E peek() {
		if (is_empty()) {
			return null;
		} else {
			return data[0];
		}
	}

	/**
	 * True if the queue is empty
	 * 
	 * @return
	 */
	public boolean is_empty() {
		if (back == 0)
			return true;
		else
			return false;
	}

	/**
	 * True if the queue is full
	 * 
	 * @return
	 */
	public boolean is_full() {
		if (back == capacity) {
			return true;
		} else {
			return false;
		}
	}

	/**
	 * Return the capacity of the array inside. [For test use]
	 * 
	 * @return
	 */
	protected int getCapacity() {
		return capacity;
	}

	/**
	 * Return the front pointer of the array inside. [For test use]
	 * 
	 * @return
	 */
	protected int getFront() {
		return front;
	}

	/**
	 * Return the back pointer of the array inside. [For test use]
	 * 
	 * @return
	 */
	protected int getBack() {
		return back;
	}

}
