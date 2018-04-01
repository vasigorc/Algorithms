package ca.vgorcinschi.algorithms1_5_14

import ca.vgorcinschi.algorithms1_5_7.AbstractUF

class WeightedQUByHeight (size: Int) extends AbstractUF(size) {

  type Response = (Int, Int)
  
  override val array = (0 to size).toArray
  /**
    * Nous aurrons pu introduire une variable d'instance currentTreeHeight,
    * mais nous aurions alors besoin de faire le travail supplémentaire de
    * réinitialisater son état, d'où le choix de renvoyer un tupple.
    * @param p - site pour lequel nous devons trouver la racine sur notre réseau (array)
    * @return - un tuple où _1 est la racine du site transmis
    *         et _2 est la hauteur de l'arbre qui l'imbrique
    */
  override def find(p: Int): (Int, Int) = {
    var tmp = p
    var height = 0

    while(tmp != array(tmp)){
      height +=1
      tmp = array(tmp)
    }
    (tmp, height)
  }
  
  override def union(p: Int, q: Int): Unit = {
    val (rootP, heightP) = find(p)
    val (rootQ, heightQ) = find(q)

    if (rootP != rootQ)
      if(heightP < heightQ)
        array(rootP) = rootQ
      else
        array(rootQ) = rootP
    count -= 1
  }
}