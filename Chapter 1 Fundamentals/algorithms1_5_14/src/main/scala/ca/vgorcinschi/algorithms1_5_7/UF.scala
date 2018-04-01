package ca.vgorcinschi.algorithms1_5_7

trait UF {
  
  //en fonction de la mise en œuvre, le type renvoyé par la méthode "find" différera
  type Response
  //renvoie le nombre de composants
  def counter():Int
  //identifiant de composant pour p (0 à N-1)
  def find(p: Int): Response
  //renvoie vrai si les deux sites sont dans le même composant
  def connected(p: Int, q: Int):Boolean
  //connecte q à p
  def union(p: Int, q: Int)
}