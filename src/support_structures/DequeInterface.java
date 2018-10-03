package support_structures;

import support_structures.QueueInterface;
import support_structures.QueueOverflowException;
import support_structures.QueueUnderflowException;

public interface DequeInterface<T> extends QueueInterface<T>
{
  void enqueueFront(T element) throws QueueOverflowException;
  // Throws QueueOverflowException if this queue is full;
  // otherwise, adds element to the front of this queue.

  T dequeueRear() throws QueueUnderflowException;
  // Throws QueueUnderflowException if this queue is empty;
  // otherwise, removes rear element from this queue and returns it.
}




