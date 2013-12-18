object monads {
  //m map f == m flatMap(x => f(x)))
  //== m flatMap (f andThen unit)
  abstract class Option[+T]{
    def flatMap[U](f: T => Option[U]): Option[U] = this match {
     case Some(x) => f(x)
     case None => None
    }
 	}
 
}