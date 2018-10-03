package support_structures;

public class DoubleLinkedDeque<T> extends DoubleLinkedQueue<T> implements DequeInterface<T>
{

	@Override
	public void enqueueFront(T element) throws QueueOverflowException
	{
		if (isFull())	// not used
			throw new QueueOverflowException("the deque is full");
		else
		{
			DLLNode<T> newNode = new DLLNode<T>(element);
			if (isEmpty())
			{
				front = newNode;
				rear = newNode;
			}
			else
			{
				front.setBack(newNode);
				newNode.setForward(front);
				front = newNode;
			}
			numElements++;
		}
	}

	@Override
	public T dequeueRear() throws QueueUnderflowException
	{

		if (isEmpty())
			throw new QueueUnderflowException("dequeue rear attempted on empty queue.");
		else
		{
			T element;
			element = rear.getInfo();		// save the element to return it later
			if (rear.getBack()==null)		// if this element is the first one too (ie last one in the deque)
			{
				front = null;				// ...set front and rear to null
				rear = null;
			}
			else
			{
				rear.getBack().setForward(null);	// set the penultimate element's forward link to null
				rear = rear.getBack();				// set rear to the penultimate element, making it the last element
			}
			numElements--;
			return element;
		}

	}

	@Override
	public String toString()
	{
		return "DoubleLinkedDeque{}";
	}


}
