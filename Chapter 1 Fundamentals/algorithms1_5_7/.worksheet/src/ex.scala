import scala.util.matching.Regex;

object ex {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(90); 
  println("Welcome to the Scala worksheet");$skip(44); 
  	
  val Integer = new Regex("(-)?(\\d+)");System.out.println("""Integer  : scala.util.matching.Regex = """ + $show(Integer ));$skip(35); val res$0 = 
  
  Integer findFirstIn "528 503";System.out.println("""res0: Option[String] = """ + $show(res$0))}
}
