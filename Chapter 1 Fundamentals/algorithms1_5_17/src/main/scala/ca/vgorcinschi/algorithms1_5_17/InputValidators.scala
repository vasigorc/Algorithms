package ca.vgorcinschi.algorithms1_5_17

object InputValidators {
  
  def validateType[T](input: String)(implicit validable: Validable[T]): T = 
      validable.validate(input)
}