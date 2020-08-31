package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class MergeSortedArraysTests extends TestBase {
    private def mergeTest(first: Array[Int], second: Array[Int], answer: Array[Int]): Unit = {
        val actual = Exercises.mergeSortedArrays(first, second)
        val clue = String.format("\nInput: (%s)(%s)\nYour output: %s\n", first.mkString(" "), second.mkString(" "), actual.mkString(" "))

        assert(actual.sameElements(answer), clue)
    }

    test("merge0") {
        val first = Array(1,3,5,6,10)
        val second = Array(1,4,6,8)
        val answer = Array(1,1,3,4,5,6,6,8,10)

        mergeTest(first, second, answer)
    }

    test("merge1") {
        val first = Array(1,3,5,7,9)
        val second = Array(2,4,6,8)
        val answer = Array(1,2,3,4,5,6,7,8,9)

        mergeTest(first, second, answer)
    }

    test("merge2") {
        val first = Array(1,2,5,8,10)
        val second = Array(2,2,6,8,9)
        val answer = Array(1,2,2,2,5,6,8,8,9,10)

        mergeTest(first, second, answer)
    }

    test("merge3") {
        val first = Array(1,3,5,10)
        val second = Array(2,8)
        val answer = Array(1,2,3,5,8,10)

        mergeTest(first, second, answer)
    }

    test(testName = "merge4") {
        val first = Array(1,4,6,10,12)
        val second = Array(1,2,3,5,6,7,8,9,10,13,14)
        val answer = Array(1,1,2,3,4,5,6,6,7,8,9,10,10,12,13,14)


        mergeTest(first, second, answer)
    }
}
