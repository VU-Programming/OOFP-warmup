package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class CollatzLengthTests extends TestBase {
    test("collatz0") {
        assert(Exercises.collatzLength(1) == 0)
    }

    test("collatz1") {
        assert(Exercises.collatzLength(11) == 14)
    }

    test("collatz2") {
        assert(Exercises.collatzLength(7) == 16)
    }

    test("collatz3") {
        assert(Exercises.collatzLength(27) == 111)
    }

    test("collatz4") {
        assert(Exercises.collatzLength(837799) == 524)
    }
}
