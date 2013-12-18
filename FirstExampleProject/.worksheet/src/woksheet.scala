object woksheet {;import org.scalaide.worksheet.runtime.library.WorksheetSupport._; def main(args: Array[String])=$execute{;$skip(84); 
  val f: PartialFunction[String, String] = {case "ping" => "pong"};System.out.println("""f  : PartialFunction[String,String] = """ + $show(f ));$skip(12); val res$0 = 
  f("ping");System.out.println("""res0: String = """ + $show(res$0));$skip(24); val res$1 = 
  f.isDefinedAt("pong");System.out.println("""res1: Boolean = """ + $show(res$1));$skip(24); val res$2 = 
  f.isDefinedAt("ping");System.out.println("""res2: Boolean = """ + $show(res$2));$skip(189); 
  //f("abc")
  
  val g: PartialFunction[List[Int], String] = {
        case Nil => "one"
        case x :: rest =>
          rest match {
            case Nil => "two"
          }
      };System.out.println("""g  : PartialFunction[List[Int],String] = """ + $show(g ));$skip(28); val res$3 = 
 g.isDefinedAt(List(1,2,3));System.out.println("""res3: Boolean = """ + $show(res$3));$skip(121); val res$4 = 
 //g(List(1,2,3))//is definedAt works only to outer
 
 for{
   x <- 2 to 100
   y <- 2 to x
   if(x%y == 0)
 }yield(x,y);System.out.println("""res4: scala.collection.immutable.IndexedSeq[(Int, Int)] = """ + $show(res$4))}
 
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
