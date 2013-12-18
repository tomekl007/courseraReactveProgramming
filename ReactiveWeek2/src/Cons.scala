object Cons {
   /*def cons[T](hd: T, tl: => Stream[T]) = new Stream[T] {
  	def head = hd
  	private var tlOpt: Option[Stream[T]] = None
  	def tail: T = tlOpt match {
  	case Some(x) => x
  	case None => tlOpt = Some(tl); tail
  	}
  }*/

}