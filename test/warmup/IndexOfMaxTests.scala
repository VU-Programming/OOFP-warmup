package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class IndexOfMaxTests extends TestBase {
    test("max0") {
        assert(Exercises.indexOfMax(Array()) == -1)
    }

    test("max1") {
        assert(Exercises.indexOfMax(Array(1)) == 0)
    }

    test("max2") {
        assert(Exercises.indexOfMax(Array(1,2,3)) == 2)
    }

    test("max3") {
        assert(Exercises.indexOfMax(Array(3,10,3,10)) == 1)
    }

    test("max4") {
        assert(Exercises.indexOfMax(Array(6,5,4,3,6)) == 0)
    }

    test("max5") {
        assert(Exercises.indexOfMax(Array(4,5,6,3,6)) == 2)
    }
}
