package warmup.infrastructure

import java.io.{OutputStream, PrintStream}

import org.scalatest.{Args, ConfigMap, Reporter}
import org.scalatest.events.Event
import warmup.AllTests

// reports your score as a fraction between 0 and 1 for codegrade
object ReportFraction {
  def main(args : Array[String]) : Unit  = {
    val err = System.err
    // prevent inventive students from printing 1.0 to
    // stderr and then getting full points
    System.setErr(new PrintStream(new OutputStream {
      override def write(i: Int): Unit = ()
    }))
    val scoreCounter = new ScoreCounter()
    new AllTests().runDirect(None, Args(reporter = new Reporter {
      override def apply(event: Event): Unit = () } ,
      configMap = ConfigMap("scoreCounter"->  scoreCounter))
    )
    err.printf(scoreCounter.fraction().toString)
  }
}
