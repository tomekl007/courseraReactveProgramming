package sim

class Wire extends Simulation{
  private var sigVal = false
  private var actions: List[Action] = List()
  def getSignal:Boolean = sigVal
  def setSignal(s:Boolean): Unit = 
    if(s != sigVal){
      sigVal = s
      actions foreach (_())//eqivalent of for(a <- actions) a() 
    }
  def addAction(a: Action): Unit = {
    actions = a :: actions
    a()
  }

 
}

abstract class Gates extends Simulation{
  def InvertDelay:Int
  def AndGateDelay:Int 
  def OrGateDelay:Int 




  def inverter(input:Wire, output:Wire):Unit = {
	def invertAction(): Unit = {
	  val inputSig = input.getSignal
	  afterDelay(InvertDelay) { output setSignal !inputSig}
	}	  
	input addAction invertAction
  }
  
  def andGate(in1: Wire, in2:Wire, output:Wire): Unit = {
    def andAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(AndGateDelay) { output setSignal (in1Sig & in2Sig )}
    }
    in1 addAction andAction
    in2 addAction andAction
  }
  
  def orGate(in1: Wire, in2:Wire, output:Wire): Unit = {
    def orAction(): Unit = {
      val in1Sig = in1.getSignal
      val in2Sig = in2.getSignal
      afterDelay(OrGateDelay) { output setSignal (in1Sig | in2Sig )}
    }
    in1 addAction orAction
    in2 addAction orAction
  }
  
    def probe(name: String , wire: Wire): Unit = {
    def probeAction(): Unit = {
      println(s"$name $currentTime value = ${wire.getSignal}")
    }
    wire addAction probeAction
  }
    
    def orGateAlt(in1: Wire, in2:Wire, output: Wire) : Unit = {
      val notIn1, notIn2, notOut = new Wire
      inverter(in1, notIn1); inverter(in2, notIn2)
      andGate(notIn1, notIn2, notOut)
      inverter(notOut, output)
    }



}














