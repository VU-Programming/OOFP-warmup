package warmup

import org.junit.runner.RunWith
import org.scalatest.{Args, Status, Suites}
import org.scalatestplus.junit.JUnitRunner


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
        ScoreCounter.reset()
        val res = super.run(testName, args)
        println(ScoreCounter.fraction())
        res
    }
}
// this global mutable state is not very nice,
// but the simpest way of keeping track of
// the score
object ScoreCounter {
    private var maxPoints = 0 : Int
    private var points = 0  : Int

    def reset() : Unit = { maxPoints = 0 ; points = 0}

    def addScore(max : Int, actual : Int) : Unit  = {
        maxPoints += max
        points += actual
    }

    // fraction of total points attained
    def fraction() : Double = points.toDouble / maxPoints

}