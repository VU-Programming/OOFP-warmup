// DO NOT MODIFY THIS FILE
package warmup

import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}

abstract class TestBase extends FunSuite with TimeLimitedTests {
    override def timeLimit: Span = Span(1, Seconds)
    // this is need to actually stop when the buggy code contains an infinite loop...
    override val defaultTestSignaler: Signaler = ReallyStopSignaler
}

object ReallyStopSignaler extends Signaler {
    override def apply(testThread: Thread): Unit = {
        StopRunningNow.stopRunningNowUnsafe(testThread)
    }
}

