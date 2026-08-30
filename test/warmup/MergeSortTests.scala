package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class MergeSortTests extends TestBase {
    private def mergeSortTest(input: Array[Int], answer: Array[Int]): Unit = {
        val actual = Exercises.mergeSort(input)
        val clue = String.format("\nInput: %s\nYour output: %s\n", input.mkString(" "), actual.mkString(" "))

        assert(actual.sameElements(answer), clue)
    }

    test("mergesort0") {
        val input = Array(6,5,3,1,8,7,2,4)
        val answer = Array(1,2,3,4,5,6,7,8)

        mergeSortTest(input, answer)
    }

    test("mergesort1") {
        val input = Array(7,6,3,6,8,2,1,5,4)
        val answer = Array(1,2,3,4,5,6,6,7,8)

        mergeSortTest(input, answer)
    }

    test("mergesort2") {
        val input = Array(8,7,6,5,4,3,2,1)
        val answer = Array(1,2,3,4,5,6,7,8)
        val weight = 1

        mergeSortTest(input, answer)
    }
}
