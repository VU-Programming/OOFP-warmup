package warmup.infrastructure

import java.io.{OutputStream, PrintStream}
import java.io.{FileOutputStream, PrintWriter}
import org.scalatest.{Args, ConfigMap, Reporter}
import org.scalatest.events.{Event, TestFailed, TestSucceeded}
import warmup.AllTests

class CustomReporter(val out : PrintStream) extends Reporter {
    override def apply(event: Event): Unit = {
      event match {
        case e : TestSucceeded => out.printf("%-20s succeeded!\n",e.testName)
        case e : TestFailed =>  out.printf("%-20s FAILED!\n",e.testName)
        case _ => ()
      }
    }
}



// reports your score as a fraction between 0 and 1 for codegrade
object ReportFraction {
  def main(args : Array[String]) : Unit  = {
    val out = System.out
    val scoreCounter = new ScoreCounter()
    new AllTests().runDirect(None, Args(
      reporter = new CustomReporter(out),
      configMap = ConfigMap("scoreCounter"->  scoreCounter))
    )
    out.printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
    out.printf("Your base grade for exercise 1 will be : %.2f\n",scoreCounter.fraction() * AllTests.MaxGrade)
  }
}


// reports your score as a fraction between 0 and 1 for codegrade
object ReportFractionCodegrade {
  def main(args : Array[String]) : Unit  = {
    val scoreCounter = new ScoreCounter()
    val out = System.out
    val nulll = new PrintStream(new OutputStream {
      override def write(i: Int): Unit = ()
    })
    // prevent inventive students from printing 1.0 to
    // stderr and then getting full points
    System.setOut(nulll)
    System.setErr(nulll)
    new AllTests().runDirect(None, Args(
      reporter = new CustomReporter(nulll),
      configMap = ConfigMap("scoreCounter"->  scoreCounter))
    )
    out.printf("'{ \"tag\": \"points\", \"points\": \"%d / %d\" }", scoreCounter.points, scoreCounter.maxPoints)
    out.close()
  }
}
