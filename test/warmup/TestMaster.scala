package warmup

import org.junit.runner.RunWith
import org.scalatest.{Args, Status, Suites}
import org.scalatestplus.junit.JUnitRunner
import warmup.TestMaster.totalWeight

@RunWith(classOf[JUnitRunner])
class TestMaster extends Suites(
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
        val res = super.run(testName, args)
        println(TestMaster.points / totalWeight.toFloat)
        res
    }
}

object TestMaster {
    private var totalWeight = 0
    private var points = 0

    def addWeight(amount: Int): Unit = {
        this.synchronized {
            totalWeight += amount
        }
    }

    def addPoints(amount: Int): Unit = {
        this.synchronized {
            points += amount
        }
    }
}