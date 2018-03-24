import scala.util.matching.Regex;

object ex {
  println("Welcome to the Scala worksheet")       //> Welcome to the Scala worksheet
  	
  val Integer = new Regex("(-)?(\\d+)")           //> Integer  : scala.util.matching.Regex = (-)?(\d+)
  
  Integer findFirstIn "528 503"                   //> res0: Option[String] = Some(528)
}