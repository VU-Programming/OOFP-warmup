package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IndexOfMaxTests extends TestBase {
    test("max0") {
        val actual = Exercises.indexOfMax(Array())
        val expected = -1

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }

    test("max1") {
        val actual = Exercises.indexOfMax(Array(1))
        val expected = 0

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }

    test("max2") {
        val actual = Exercises.indexOfMax(Array(1,2,3))
        val expected = 2

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }

    test("max3") {
        val actual = Exercises.indexOfMax(Array(3,10,3,10))
        val expected = 1

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }

    test("max4") {
        val actual = Exercises.indexOfMax(Array(6,5,4,3,6))
        val expected = 0

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }

    test("max5") {
        val actual = Exercises.indexOfMax(Array(4,5,6,3,6))
        val expected = 2

        val weight = 1
        TestMaster.addWeight(weight)
        if (actual == expected) {
            TestMaster.addPoints(weight)
        }

        assert(actual == expected)
    }
}
