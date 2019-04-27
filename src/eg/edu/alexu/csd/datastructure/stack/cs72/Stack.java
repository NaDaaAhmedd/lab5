package eg.edu.alexu.csd.datastructure.stack.cs72;

public class Stack {

	int size;
	Node top;

	/**
	 * Pushes an item onto the top of this stack
	 * 
	 * @param element is the object
	 */
	public void push(Object element) {
		Node tmp = new Node();
		tmp.data = element;
		tmp.next = top;
		top = tmp;
		size++;

	}

	/**
	 * Looks at the object at the top of this stack without removing it from the
	 * stack
	 * 
	 * @return the object at the top of this stack
	 */

	public Object peek() {
		if (isEmpty())
			return null;
		return top.data;
	}

	/**
	 * Removes the object at the top of this stack and returns that object as the
	 * value of this function
	 * 
	 * @return the object at the top of this stack
	 */
	public Object pop() {
		if (isEmpty())
			return null;
		Object res = top.data;
		top = top.next;
		size--;
		return res;
	}

	/**
	 * Tests if this stack is empty
	 * 
	 * @return true if the stack is empty, false otherwise
	 */
	public boolean isEmpty() {
		if (size == 0)
			return true;
		else
			return false;
	}

	/**
	 * @return the size of the stack
	 */
	public int size() {
		return size;
	}

	public void show() {
		Node n = top;
		while (n != null) {
			System.out.println(n.data);
			n = n.next;
		}
	}
}

class Node {
	Object data;
	Node next;
}

interface IStack {
/**
* Removes the element at the top of stack and returns that element.
*
* @return top of stack element, or through exception if empty
*/
public Object pop();
/**
* Get the element at the top of stack without removing it from stack.
*
* @return top of stack element, or through exception if empty
*/
public Object peek();
/**
* Pushes an item onto the top of this stack.
*
* @param element
* to insert
*/
public void push(Object element);
/**
* Tests if this stack is empty
*
* @return true if stack empty
*/
public boolean isEmpty();
/**
* Returns the number of elements in the stack.
*
* @return number of elements in the stack
*/
public int size();
}
