object account {
  val acct = new BankAccount                      //> acct  : BankAccount = BankAccount@40f6dcc7
  acct deposit 50
  acct withdraw 20                                //> res0: Int = 30
  acct withdraw 20                                //> res1: Int = 10
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
  	else()                                    //> WHILE: (condition: => Boolean)(command: => Unit)Unit
 
 
 
 
 def REPEAT(command: => Unit)(condition: => Boolean): Unit= {
 	command
 	if(condition)()
  else REPEAT(command)(condition)
 }                                                //> REPEAT: (command: => Unit)(condition: => Boolean)Unit
 
 
 def REPEAT_UNTIL(command: => Unit)(condition: => Boolean): Unit= {
 	command
 	if(condition){
 		REPEAT(command)(condition)
 	}
  else ()
 }                                                //> REPEAT_UNTIL: (command: => Unit)(condition: => Boolean)Unit
 for(i <- 1 until 3; j <-"abc") println(i+ " " + j)
                                                  //> 1 a
                                                  //| 1 b
                                                  //| 1 c
                                                  //| 2 a
                                                  //| 2 b
                                                  //| 2 c
 //translates to
 //(1 until 3) foreach (i => "abc" foreach (j => println(i + " " + j )
 
 
  
}