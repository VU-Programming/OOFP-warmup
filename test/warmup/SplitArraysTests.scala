package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class SplitArraysTests extends TestBase {
    private def testSplit(input : Array[Int]): Unit = {
        val (l,r) = Exercises.splitArray(input.clone())
        val clue = String.format("\nInput: %s\nYour output: (%s)(%s)\n", input.mkString(" "), l.mkString(" "), r.mkString(" "))

        val lengthResult = l.length == input.length / 2
        val elementsResult = (l ++ r).sameElements(input)

        assert(lengthResult, clue)
        assert(elementsResult, clue)
    }

    test("split0") {
        testSplit(Array(5,3,10,5,6,1,3))
    }

    test("split1") {
        testSplit(Array(6,5,3,6,1,3))
    }

    test("split2" ) {
        testSplit(Array(6,5,3,6,1,3,8,5,3))
    }
}
