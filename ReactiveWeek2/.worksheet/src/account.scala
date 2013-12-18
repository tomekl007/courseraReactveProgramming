object account {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(45); 
  val acct = new BankAccount;System.out.println("""acct  : BankAccount = """ + $show(acct ));$skip(18); 
  acct deposit 50;$skip(19); val res$0 = 
  acct withdraw 20;System.out.println("""res0: Int = """ + $show(res$0));$skip(19); val res$1 = 
  acct withdraw 20;System.out.println("""res1: Int = """ + $show(res$1));$skip(389); 
  //acct withdraw(20)
  
  /*def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
  	def head = hd
  	private var tlOpt: Option[Stream[T]] = None
  	def tail: T = tlOpt match {
  	case Some(x) => x
  	case None => tlOpt = Some(tl); tail
  	}
  }*/
  
  def WHILE(condition: => Boolean)(command: => Unit): Unit =
  	if(condition){
  		command
  		WHILE(condition)(command)
  	}
  	else();System.out.println("""WHILE: (condition: => Boolean)(command: => Unit)Unit""");$skip(135); 
 
 
 
 
 def REPEAT(command: => Unit)(condition: => Boolean): Unit= {
 	command
 	if(condition)()
  else REPEAT(command)(condition)
 };System.out.println("""REPEAT: (command: => Unit)(condition: => Boolean)Unit""");$skip(146); 
 
 
 def REPEAT_UNTIL(command: => Unit)(condition: => Boolean): Unit= {
 	command
 	if(condition){
 		REPEAT(command)(condition)
 	}
  else ()
 };System.out.println("""REPEAT_UNTIL: (command: => Unit)(condition: => Boolean)Unit""");$skip(52); 
 for(i <- 1 until 3; j <-"abc") println(i+ " " + j)}
 //translates to
 //(1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j )
 
 
  
}
