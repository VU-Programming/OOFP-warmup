package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner

@RunWith(classOf[JUnitRunner])
class AverageGradeTests extends TestBase {
    test("testaverage0") {
        assert(Exercises.averageGrade("NS NS 9 NS NS 5") == 7)
    }

    test("testaverage1") {
        assert(Exercises.averageGrade("8 8.2 NS NS 9 4 1 5.5") == 5.95)
    }

    test("testaverage2") {
        assert(Exercises.averageGrade("7 8 5.5 9.2 NS 7.8 5.4 NS NS 7 9.2") == 7.387499999999999)
    }
}
