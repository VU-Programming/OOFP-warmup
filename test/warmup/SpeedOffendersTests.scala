package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class SpeedOffendersTests extends TestBase {

  test("mergesort0") {
    val input = Array(6,5,3,1,8,7,2,4)
    val answer = Array(1,2,3,4,5,6,7,8)

    mergeSortTest(input, answer)
  }

}
