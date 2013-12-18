package reactive_week5_actors

import scala.actors.Actor

class BecameExample extends Actor {
  

  
  def angry: Receive = {
    case "foo" ⇒ sender ! "I am already angry?"
    case "bar" ⇒ become(happy)
  }
 
  def happy: Receive = {
    case "bar" ⇒ sender ! "I am already happy :-)"
    case "foo" ⇒ become(angry)
  }
 
  def receive = {
    case "foo" ⇒ become(angry)
    case "bar" ⇒ become(happy)
  }
}

