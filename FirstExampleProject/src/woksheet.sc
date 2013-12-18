object woksheet {
  val f: PartialFunction[String, String] = {case "ping" => "pong"}
  f("ping")
  f.isDefinedAt("pong")
  f.isDefinedAt("ping")
  //f("abc")
  
  val g: PartialFunction[List[Int], String] = {
        case Nil => "one"
        case x :: rest =>
          rest match {
            case Nil => "two"
          }
      }
 g.isDefinedAt(List(1,2,3))
 //g(List(1,2,3))//is definedAt works only to outer
 
 for{
   x <- 2 to 100
   y <- 2 to x
   if(x%y == 0)
 }yield(x,y)
 
 trait Generator[+T]{
 	self => //alias for "this"
 	def generate: T
 	def map[S](f: T => S):Generator[S] = new Generator[S]{
 		def generate = f(self.generate)
 	}
 	
 	def flatMap[S](f:T => Generator[S]): Generator[S] = new Generator[S] {
 		def generate = f(self.generate).generate
 	}
 	
 val integers = new Generator[Int]{
 	def generate = scala.util.Random.nextInt()
 }
 val booleans = integers.map(_ >= 0)
 
 class Leaf(x:T){}
 
 def leafs : Generator[Leaf] = for{
 	x <- integers
 } yield Leaf(x)
 
 def inners: Generator[Inner] = for{
 	l <- trees
 	r <- trees
 }yield Inner(l,r)
 
 def trees: Generator[Tree] = for {
   isLeaf <- booleans
   tree <- if(isLeaf) leafs else inners
 }yield tree
 
 
 
 }
 
 
 
 
      
  
  
}