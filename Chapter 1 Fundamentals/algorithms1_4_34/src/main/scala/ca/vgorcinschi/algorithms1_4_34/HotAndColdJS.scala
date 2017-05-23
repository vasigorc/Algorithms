package ca.vgorcinschi.algorithms1_4_34

import scala.scalajs.js.JSApp
import org.scalajs.dom
import dom.document
import scala.scalajs.js.annotation.JSExportTopLevel
import org.scalajs.jquery.jQuery

object HotAndColdJS extends JSApp{
  
   def main(): Unit = {
    jQuery("body").append("<p>Hello world!</p>")
  }
  
  //~TopLevel as will be seen as a global function
  @JSExportTopLevel("addClickedMessage")
  def addClickedMessage():Unit ={
    jQuery("body").append("<p>You clicked the button!</p>")
  }
}