package ca.vgorcinschi.algorithms1_4_34

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document

object HotAndColdJS extends JSApp{
  
   def main(): Unit = {
    appendPar(document.body, "Hello world!")
  }
  
  def appendPar(targetNode: dom.Node, text: String): Unit = {
    val parNode = document.createElement("p")
    val textNode = document.createTextNode(text)
    parNode.appendChild(textNode)
    targetNode.appendChild(parNode)
  } 
}