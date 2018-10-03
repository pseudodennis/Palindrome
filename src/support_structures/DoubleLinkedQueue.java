//---------------------------------------------------------------------------
// Based on LinkedQueue.java        by Dale/Joyce/Weems             Chapter 4
//
// Implements QueueInterface using a double-linked list.
//---------------------------------------------------------------------------

package support_structures;

public class DoubleLinkedQueue<T> implements QueueInterface<T>
{
  protected DLLNode<T> front;     // reference to the front of this queue
  protected DLLNode<T> rear;      // reference to the rear of this queue
  protected int numElements = 0; // number of elements in this queue

  public DoubleLinkedQueue()
  {
    front = null;
    rear = null;
  }

  public void enqueue(T element)
  // Adds element to the rear of this queue.
  { 
    DLLNode<T> newNode = new DLLNode<T>(element);
    if (rear == null)
      front = newNode;
    else
    {
      newNode.setBack(rear);
      rear.setForward(newNode);
    }
    rear = newNode;
    numElements++;
  }     

  public T dequeue()
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes front element from this queue and returns it.
  {
    if (isEmpty())
      throw new QueueUnderflowException("Dequeue attempted on empty queue.");
    else
    {
      T element;
      element = front.getInfo();	// 'remember' the information in the first node so we can return it later
      front = front.getForward();	// remove the front node by setting 'front' to point to the next element
      //front.setBack(null);			// update the front's back pointer to be null (probably redundant if all the methods stop at 'front')
      if (front == null)			// if the queue becomes empty, also set `rear` to null
        rear = null;
      numElements--;				// update size of queue
      return element;
    }
  }

  public boolean isEmpty()
  // Returns true if this queue is empty; otherwise, returns false.
  {              
    return (front == null);
  }
  
  public boolean isFull()
  // Returns false - a linked queue is never full.
  {              
    return false;
  }

  public int size()
  // Returns the number of elements in this queue.
  {
    return numElements;
  }

}

