package warmup

import org.junit.runner.RunWith
import org.scalatest.{Args, Status, Suites}
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.ScoreCounter


@RunWith(classOf[JUnitRunner])
class AllTests extends Suites(
    new AverageGradeTests,
    new CollatzLengthTests,
    new DiamondStringTests,
    new EloScoresTests,
    new IndexOfMaxTests,
    new MergeSortedArraysTests,
    new MergeSortTests,
    new SplitArraysTests,
    new WordCountTests
) {
    override def run(testName: Option[String], args: Args): Status = {
        val scoreCounter = new ScoreCounter()
        val argsp = args.copy(
            configMap = args.configMap.updated("scoreCounter", scoreCounter))
        val res = super.run(testName, argsp)
        println("Scoref:" + scoreCounter.fraction().toString)
        res
    }
}
