package warmup

import org.junit.runner.RunWith
import org.scalatest.{Args,  Status, Suites}
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.ScoreCounter
import AllTests._

@RunWith(classOf[JUnitRunner])
class AllTests extends Suites(
    new IndexOfMaxTests,
    new AverageGradeTests,
    new CollatzLengthTests,
    new DiamondStringTests,
    new WordCountTests,
    new EloScoresTests,
    new SpeedOffendersTests,
    new SplitArraysTests,
    new MergeSortedArraysTests,
    new MergeSortTests,


) {

    override def run(testName: Option[String], args: Args): Status = {
        val scoreCounter = new ScoreCounter()
        val newArgs =
            args.copy(configMap = args.configMap.updated("scoreCounter",scoreCounter))
        val res = runDirect(testName,newArgs)
        printf("You got %d/%d points!\n", scoreCounter.points, scoreCounter.maxPoints)
        printf("Your base grade for exercise 1 will be : %.2f\n",scoreCounter.fraction() * MaxGrade)
        res
    }

    // run without making a new scorecounter
    def runDirect(testName: Option[String], args: Args): Status = {
        super.run(testName, args)
    }

}

object AllTests {
    val MaxGrade = 7
}
