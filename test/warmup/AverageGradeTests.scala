package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class AverageGradeTests extends TestBase {
    test("testaverage0") {
        val actual = Exercises.averageGrade("NS NS 9 NS NS 5")
        val expected = 7

        assert(actual == expected)
    }

    test("testaverage1") {
        val actual = Exercises.averageGrade("8 8.2 NS NS 9 4 1 5.5")
        val expected = 5.95

        assert(actual == expected)
    }

    test("testaverage2") {
        val actual = Exercises.averageGrade("7 8 5.5 9.2 NS 7.8 5.4 NS NS 7 9.2")
        val expected = 7.387499999999999

        assert(actual == expected)
    }
}
