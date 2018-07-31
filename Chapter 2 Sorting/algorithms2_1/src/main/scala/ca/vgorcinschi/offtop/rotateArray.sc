/*
  From Elements of Programming Interviews in Java
  5.19
  Write a function that takes as input an n * n 2D array and rotates the array by
  90 degrees clockwise.
 */

val matrix = Array(
  Array(1,2,3,4),
  Array(5,6,7,8),
  Array(9,10,11,12),
  Array(13,14,15,16))

matrix foreach { row => row foreach print; println }
//rotated 90 clockwise
val rotated90 = matrix.transpose.map(_.reverse)

rotated90 foreach { row => row foreach print; println }