package reactiveWeek3
import scala.util.{Try, Success, Failure}
import scala.util.Success
import scala.concurrent._
import scala.util.Success
import scala.concurrent.ExecutionContext.Implicits.global
import scala.collection.immutable.Queue
import scala.concurrent.duration.Duration
import scala.concurrent.duration.TimeUnit
import scala.language.postfixOps
import scala.actors.remote.SendTo


trait Socket{
  def readFromMemory(): Future[Array[Byte]]
  def sendToEurope(packet: Array[Byte]): Future[Array[Byte]]
}
class EMailMessage(val from:String, val to:String)
class SocketImp extends Socket{
  val memory = Queue[EMailMessage](
      new EMailMessage(from ="erok", to="roland"),
      new EMailMessage(from = "martin", to="Erik"))
      
      //it will run on background thread
  def readFromMemory(): Future[Array[Byte]] = Future {
    val email = memory.dequeue
    //some dump thing
    Array(1,2,3)
  }
  def sendToEurope(packet: Array[Byte]): Future[Array[Byte]] = {
    println("Sending to europe" + packet(1))
    Future(Array(1))
  }
}

trait Adventure {
	def collectCoins(): Try[List[Coin]]
	def buyTreasure(coins: List[Coin]): Try[Treasure]
}
class Coin(val value:Int)
  

class Treasure
class GameOverException(msg: String) extends Exception(msg)
case class Gold(override val value:Int) extends Coin(value)
case class Silver(override val value:Int) extends Coin(value)
case class Diamond extends Treasure

class AdvImp extends Adventure{
  val treasureCost = 100
  def collectCoins():Try[List[Coin]] = {
    if(eatenByMonser(this)){
      throw new GameOverException("oops")
      println("was eaten")
    }
    Success(List(Gold(1),Gold(1),Silver(10)))
  }
  def buyTreasure(coins: List[Coin]): Try[Treasure]= {
       
	    	if(coins(0).value < treasureCost){
	    	  println("not enought moneY")
	    		throw new GameOverException("Nice try!")
	    	}
	    Success( new Diamond)
  }
  def eatenByMonser(a:Adventure):Boolean = {
    false
  }
  
}

object test{
   def main(args: Array[String]) {
//     val adventure = new AdvImp()
//     val coins  = adventure.collectCoins()
     //val treasure = adventure.buyTreasure(coins)
     
     //affter add Try[] to returning statement
     val adventure = new AdvImp()
     //val coins:Try[List[Coin]] = adventure.collectCoins()
//     val treasure: Try[Treasure] = coins match{
//       case Success(cs) => adventure.buyTreasure(cs)
//       case failure @ Failure(t) => failure
//     }
     
     //monads guilds you through Happy Path
     //version with flatMap
     val trea: Try[Treasure] = 
       adventure.collectCoins().flatMap(coins => {
         adventure.buyTreasure(coins);
       })
   //    adventure.buyTreasure(List(Silver(2))).flatMap(tres =>{
     //    adventure.collectCoins
      // })
       
       trea match{
       case Success(s) => println("treasure bought")
       case Failure(s) => println("treasure not bought")
     }
     
       //try is a monad so i could use comprehension syntax
       val treasureC: Try[Treasure] = for{
         coins <- adventure.collectCoins()
         treasure <- adventure.buyTreasure(coins) //left - T; right - Try[T]
       }yield treasure
    //---------Example in asynch computation---------------
       val socket = new SocketImp()
       val packet:Future[Array[Byte]] = socket.readFromMemory()
       packet onSuccess{
         case bs => socket.sendToEurope(bs)
       }
         packet onComplete {
         	case Success(packet) =>{ 
         	  println("send to europe")
         	  val confirmation:Future[Array[Byte]] =
         	  socket.sendToEurope(packet)
         	}
         	case Failure(t) => println("fail when receiving")
         } 
       //rewrite onComplete using flatMap
         val confirmationFromFlatMap:Future[Array[Byte]] = 
           packet.flatMap(p =>{ 
             println("sendToEurope from flatMap")
             socket.sendToEurope(p)
           })
           //IMPORTANT !!!!
           /*object Http{
             def apply(url:URL, req:Request): Future[Response] = 
               //runs http request asynch
          }
           
           def sendTo(url: URL, packet:Array[Byte]): Future[Array[Byte]] =
             Http(url, Request(packet)).filter(response => response.isOk)
             .map(response => response.toByteArray)

             
           def sendToSafe(packet: Array[Byte]): Future[Array[Byte]] = 
            sendTo(mailSever.europe, packet) fallbackTo{
        	   sendTo(mailServer.usa, packet) 
           }recover {
               case usaError => usaError.getMessage.toByteArray
           }*/
           val c = Await.result(confirmationFromFlatMap, Duration.create(2, scala.concurrent.duration.SECONDS) )
           println(c(0).toString())
           
           val confirmationFromFor: Future[Array[Byte]] = for {
             packet <- socket.readFromMemory()
             confirmationFromFor <- socket.sendToEurope(packet) 
           }yield confirmationFromFor
           
          
           
           //block is passed : call by name, so it is evaluated lazy
           //recurse solution
           def retry(noTimes: Int)(block: => Future[Int]): Future[Int] = {
             if(noTimes == 0){
               Future.failed(new Exception("Sorry"))
             }else {
               block fallbackTo {
                 retry(noTimes - 1) { block }
               }
             }
           }
     def race[Int](left:Future[Int], right: Future[Int]): Future[Int] = {
      val p = Promise[Int]()
      left onComplete{ p.tryComplete(_)}
      right onComplete{ p.tryComplete(_)}
      p.future
    }
     println(race(Future ({ while(true){}; 1 }), Future(2)).value)
     
     //def success(value:T):Unit = this.complete(Success(value))
     //def failure(t:Throwable): Unit = this.complete(failure(t))
     
         
     
   }
}/*
object reimplementedFromTry{
	def map[S](f: T=>S): Try[S] = this match {
	  case Success(value) => Try(f(value))
	  case failure @ Failure(t) => failure
	}
	def flatMap[s](f: T=> Try[S]): Try[S] = this match{
	  case Success(value) => try { f(value)} catch { case t=> Failure(t)}
	  case failure @ Failure(t) => failure
	  
	}
}*/
//try companion obj
object Try{
  def apply[T](r : =>T): Try[T] = {
    try{ Success(r)}
    catch {case t => Failure(t)}
  }
}

class Retrying[T]{
    def retry(noTimes: Int)(block: => Future[T]): Future[T] = {
             val ns: Iterator[Int] = (1 to noTimes).iterator
             val attemps: Iterator[Future[T]] = ns.map(_ => block)
             val failed = Future.failed(new Exception)
             
             //attemps.foldLeft(failed)
             	// ((a, block) => a recoverWith{ block() })
             	
             //attemps.foldRight(() => failed)
             //((block, a) => () => { block() fallbackTo { a()}})
             null	
           }
}



           





























