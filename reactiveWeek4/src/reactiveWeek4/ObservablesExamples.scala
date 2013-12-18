package reactiveWeek4


import scala.collection._



object ObservablesExamples {
   def main(args: Array[String]) {
     val ticks: Observable[Long] = Observable.interval(1 seconds)
     
     val events: Observable[Long] = ticks.filter(s=>s%2==0)
     
     val bufs: Observable[Seq[Long]] = ticks.buffer(2,1)
     val s = bufs.subscribe(b=>println(b))
     s.unsubscribe()
     
     
     
   }

}