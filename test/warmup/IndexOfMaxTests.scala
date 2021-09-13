package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class IndexOfMaxTests extends TestBase {
    test("max0") {
        val actual = Exercises.indexOfMax(Array())
        val expected = -1

        assert(actual == expected)
    }

    test("max1") {
        val actual = Exercises.indexOfMax(Array(1))
        val expected = 0


        assert(actual == expected)
    }

    test("max2") {
        val actual = Exercises.indexOfMax(Array(1,2,3))
        val expected = 2

        assert(actual == expected)
    }

    test("max3") {
        val actual = Exercises.indexOfMax(Array(3,10,3,10))
        val expected = 1

        assert(actual == expected)
    }

    test("max4") {
        val actual = Exercises.indexOfMax(Array(6,5,4,3,6))
        val expected = 0

        assert(actual == expected)
    }

    test("max5") {
        val actual = Exercises.indexOfMax(Array(4,5,6,3,6))
        val expected = 2

        assert(actual == expected)
    }

    test("max6") {
        val actual = Exercises.indexOfMax(Array(6,5,4,3,2,1))
        val expected = 0

        assert(actual == expected)
    }

    test("max7") {
        val actual = Exercises.indexOfMax(Array(-10,-5,-3,-5))
        val expected = 2

        assert(actual == expected)
    }
}
