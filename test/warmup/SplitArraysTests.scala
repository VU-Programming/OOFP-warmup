package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class SplitArraysTests extends TestBase {
    private def testSplit(input : Array[Int], weight: Int): Unit = {
        val (l,r) = Exercises.splitArray(input)
        val clue = String.format("\nInput: %s\nYour output: (%s)(%s)\n", input.mkString(" "), l.mkString(" "), r.mkString(" "))

        val lengthResult = l.length == input.length / 2
        val elementsResult = (l ++ r).sameElements(input)

        TestMaster.addWeight(weight)
        if (lengthResult && elementsResult) {
            TestMaster.addPoints(weight)
        }

        assert(lengthResult, clue)
        assert(elementsResult, clue)
    }

    test("split0") {
        val weight = 1
        testSplit(Array(5,3,10,5,6,1,3), weight)
    }

    test("split1") {
        val weight = 1
        testSplit(Array(6,5,3,6,1,3), weight)
    }

    test("split2" ) {
        val weight = 1
        testSplit(Array(6,5,3,6,1,3,8,5,3), weight)
    }
}
