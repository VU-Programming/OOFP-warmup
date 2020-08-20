// DO NOT MODIFY THIS FILE
package warmup

import org.scalactic.source.Position
import org.scalatest.{BeforeAndAfterAll, FunSuite, Tag}
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}

abstract class TestBase extends FunSuite with TimeLimitedTests with BeforeAndAfterAll {
    override def timeLimit: Span = Span(1, Seconds)
    // this is need to actually stop when the buggy code contains an infinite loop...
    override val defaultTestSignaler: Signaler = ReallyStopSignaler

    override def beforeAll(): Unit = {
        super.beforeAll()
    }

    override def test(testName: String, testTags: Tag*)(testFun: => Any)(implicit pos: Position): Unit =
        test(testName,1,testTags:_*){testFun}


    def test(testName : String, weight : Int, testTags : Tag*)(testFun : => Any)(implicit pos: Position): Unit = {
        super.test(testName,testTags:_*){
            try {
                testFun
                ScoreCounter.addScore(weight,weight)
            } catch {
                case e : Any => {
                    ScoreCounter.addScore(weight, 0)
                    throw e
                }
            }
        }
    }
}

object ReallyStopSignaler extends Signaler {
    override def apply(testThread: Thread): Unit = {
        StopRunningNow.stopRunningNowUnsafe(testThread)
    }
}

