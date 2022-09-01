package warmup

import org.junit.runner.RunWith
import org.scalactic.{Equality, TolerantNumerics}
import org.scalatestplus.junit.JUnitRunner
import warmup.Exercises.{Observation, SpeedOffender, Time, speedOffenders}
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class SpeedOffendersTests extends TestBase {

  test("speedOffender1") {
    val input = List(
      Observation("A","NX-66-PP",Time(18492, 12,6,0)),
      Observation("B","NX-66-PP",Time(18492, 12,6,50)),
    )
    val expected = List(SpeedOffender("NX-66-PP",108))

    assert(speedOffenders(input).toList === expected)
  }

  test("speedOffender2") {
    val input = List(
      Observation("A","NX-66-PP",Time(18492, 3,6,0)),
      Observation("B","NX-66-PP",Time(18492, 3,6,50)),
    )
    val expected = List()
    assert(speedOffenders(input).toList  == expected)
  }

  test("speedOffender3") {
    val input = List(
      Observation("A", "OO-66-XX", Time(18491, 23, 59, 40)),
      Observation("A", "MM-43-QA", Time(18491, 23, 59, 50)),
      Observation("B", "OO-66-XX", Time(18492, 0, 0, 20)),
      Observation("B", "MM-43-QA", Time(18492, 0, 0, 40))
    )
    val expected = List(SpeedOffender("OO-66-XX", 135))
    assert(speedOffenders(input).toList  == expected)
  }


  test("speedOffender4") {
    val input = List(
      Observation("A","OO-66-XX",Time(18491, 23,59,40)),
      Observation("A","NX-66-PP",Time(18492, 3,6,0)),
      Observation("A","PP-33-XX", Time(18492, 3,6,10)),
      Observation("B","NX-66-PP",Time(18492, 3,6,30)),
      Observation("A","MM-11-OW", Time(18492, 3,6,40)),
      Observation("B","PP-33-XX", Time(18492, 3,7,0)),
      Observation("B","MM-11-OW", Time(18492, 3,7,16)),
      Observation("A","BA-12-PW",Time(18492, 8,59,50)),
      Observation("A","LA-53-NY",Time(18492, 8,59,59)),
      Observation("B","BA-12-PW",Time(18492, 9,0,40)),
      Observation("B","LA-53-NY",Time(18492, 9,0,56)),
    )

    val expected = List(SpeedOffender("NX-66-PP",180), SpeedOffender("MM-11-OW",150), SpeedOffender("BA-12-PW",108))
    assert(speedOffenders(input).toList  == expected)
  }
}