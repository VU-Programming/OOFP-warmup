package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class CollatzLengthTests extends TestBase {
    test("collatz0") {
        val actual = Exercises.collatzLength(1)
        val expected = 0

        assert(actual == expected)
    }

    test("collatz1") {
        val actual = Exercises.collatzLength(11)
        val expected = 14

        assert(actual == expected)
    }

    test("collatz2") {
        val actual = Exercises.collatzLength(7)
        val expected = 16

        assert(actual == expected)
    }

    test("collatz3") {
        val actual = Exercises.collatzLength(27)
        val expected = 111

        assert(actual == expected)
    }

    test("collatz4") {
        val actual = Exercises.collatzLength(837799)
        val expected = 524

        assert(actual == expected)
    }
}
