package ca.vgorcinschi.algorithms1_3

import scala.reflect.ClassTag

/**
  * From Introduction to the Art of Programming Using Scala
  * CRC Press. Chapter 24: Stacks and Queues
  *
  * @tparam A
  */
class ArrayStack[A: ClassTag] extends Stack[A] {

  private var top = 0
  private var data = new Array[A](10)

  /**
    * Add an item to the stack
    *
    * @param obj - item to add
    */
  override def push(obj: A): Unit = {
    if (top >= data.length) {
      val tmp = new Array[A](data.length * 2)
      Array.copy(data, 0, tmp, 0, data.length)
      data = tmp
    }
    data(top) = obj
    top += 1
  }

  /**
    * Remove the most recently added.
    *
    * @return the item that was removed
    */
  override def pop(): A = {
    assert(!isEmpty, "Pop called on an empty stack.")
    top -= 1
    data(top)
  }

  /**
    * Return the most recently added item without removing it.
    *
    * @return the most recently added item
    */
  override def peek: A = data(top - 1)

  /**
    * Tells whether this stack is empty
    *
    * @return true if there are no items on the stack, otherwise false
    */
  override def isEmpty: Boolean = top == 0
}