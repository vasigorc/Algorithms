package ca.vgorcinschi.algorithms1_4_34

import scala.scalajs.js.JSApp
import org.scalajs.jquery.jQuery

object HotAndColdJS extends JSApp{
  
   def main(): Unit = {
     /*
      * global function invocation like 
      * $(document).ready(function(){...}
      */
    jQuery(()=>setupUI())
  }
  
  /*
   * we no longer need the @JSExportTopLevel("addClickedMessage")
   * annotation since this method is not directly called from the DOM
   */
  def addClickedMessage():Unit ={
    jQuery("body").append("<p>You clicked the button!</p>")
  }
  
  def setupUI():Unit = {
    //click envokes an event handler
    jQuery("#click-me-button").click(()=> addClickedMessage())
    jQuery("body").append("<p>Hello World!</p>")
  }
}