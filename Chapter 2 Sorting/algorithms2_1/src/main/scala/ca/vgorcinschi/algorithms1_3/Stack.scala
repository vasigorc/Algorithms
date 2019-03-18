package ca.vgorcinschi.algorithms1_3

/**
  * From Introduction to the Art of Programming Using Scala
  * CRC Press. Chapter 24: Stacks and Queues
  * @tparam A
  */
trait Stack [A]{

  /**
    * Add an item to the stack
    * @param obj - item to add
    */
  def push(obj: A)

  /**
    * Remove the most recently added.
    * @return the item that was removed
    */
  def pop(): A

  /**
    * Return the most recently added item without removing it.
    * @return the most recently added item
    */
  def peek: A

  /**
    * Tells whether this stack is empty
    * @return true if there are no items on the stack, otherwise false
    */
  def isEmpty: Boolean
}
