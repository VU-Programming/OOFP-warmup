package warmup.infrastructure

import java.io.{OutputStream, PrintStream}

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
    // prevent inventive students from printing 1.0 to
    // stderr and then getting full points
    System.setOut(new PrintStream(new OutputStream {
      override def write(i: Int): Unit = ()
    }))
    System.setErr(new PrintStream(new OutputStream {
      override def write(i: Int): Unit = ()
    }))
    val scoreCounter = new ScoreCounter()
    new AllTests().runDirect(None, Args(
      reporter = new CustomReporter(out),
      configMap = ConfigMap("scoreCounter"->  scoreCounter))
    )
    out.printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
    out.printf("Your base grade for exercise 1 will be : %.2f\n",scoreCounter.fraction() * AllTests.MaxGrade)
    out.printf("Fractiontouseforcodegrade %.2f",scoreCounter.fraction())
  }
}
