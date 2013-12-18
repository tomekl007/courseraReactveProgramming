package reactive_week5_actors

import akka.actor.Actor

class Counter extends Actor {
  var count = 0
  def receive = {
    case "incr" => count += 1
    case "get"  => sender ! count
  }
  //functional equivalent
  def counter(n:Int): Receive = {
    case "incr" => context.become(counter(n+1))
    case "get" => sender ! n
   }
  //def receive = counter(0)
  
  
} 