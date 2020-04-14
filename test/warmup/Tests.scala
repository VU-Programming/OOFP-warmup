package warmup

import org.junit.runner.RunWith
import org.scalactic.Tolerance._
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, ThreadSignaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner
import warmup.Exercises.{Game, Player}

@RunWith(classOf[JUnitRunner])
class Tests extends FunSuite with TimeLimitedTests {
  test("max0") {
    assert(Exercises.indexOfMax(Array())== -1)
  }

  test("max1") {
    assert(Exercises.indexOfMax(Array(1))==0)
  }

  test("max2") {
    assert(Exercises.indexOfMax(Array(1,2,3))==2)
  }

  test("max3") {
    assert(Exercises.indexOfMax(Array(3,10,3,10))== 1)
  }

  test("max4") {
    assert(Exercises.indexOfMax(Array(6,5,4,3,6)) == 0)
  }

  test("max5") {
    assert(Exercises.indexOfMax(Array(4,5,6,3,6)) == 2)
  }

  test("testaverage0") {
    assert(Exercises.averageGrade("NS NS 9 NS NS 5") == 7)
  }

  test("testaverage1") {
    assert(Exercises.averageGrade("8 8.2 NS NS 9 4 1 5.5") == 5.95)
  }

  test("testaverage2") {
    assert(Exercises.averageGrade("7 8 5.5 9.2 NS 7.8 5.4 NS NS 7 9.2") == 7.387499999999999)
  }

  //        collatzLength(11) = 14
  //       collatzLength(1) = 0
  //       collatzLength(4) = 2
  //       collatzLength(7) = 16

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

  test("diamond3") {
    assert(Exercises.diamondString(3) == diamond3)
  }

  test("diamond4") {
    assert(Exercises.diamondString(4) == diamond4)
  }

  test("diamond7") {
    assert(Exercises.diamondString(7) == diamond7)
  }

  test("diamond12") {
    assert(Exercises.diamondString(12) == diamond12)
  }

  val diamond3 =
    """ #
       |###
       | #""".stripMargin

  val diamond4 =
    """ #
      |###
      |###
      | #""".stripMargin

  val diamond7 =
    """   #
      |  ###
      | #####
      |#######
      | #####
      |  ###
      |   #""".stripMargin

  val diamond12 =
    """     #
      |    ###
      |   #####
      |  #######
      | #########
      |###########
      |###########
      | #########
      |  #######
      |   #####
      |    ###
      |     #""".stripMargin



  test("wordcount0") {
    assert(Exercises.wordCount("the monkey hugs the monkey") ==  Map("the" -> 2, "monkey" -> 2, "hugs" -> 1))
  }

  test("wordcount1") {
    assert(Exercises.wordCount("A horse, a horse! My kingdom for a horse!") ==  Map("for" -> 1, "a" -> 3, "my" -> 1, "horse" -> 3, "kingdom" -> 1))
  }


  test("wordcount2") {
    assert(Exercises.wordCount("A woman is but a woman. A man is but a man. A child is but a child.") ==  Map("is" -> 3, "but" -> 3, "a" -> 6, "man" -> 2, "child" -> 2, "woman" -> 2))

  }

  test("elo1") {
    val jaap = new Player("Jaap",2000)
    val piet = new Player("Piet", 2200)
    val game = new Game(jaap,piet,0.0)
    Exercises.updateEloScores(List(jaap,piet), List(game) )
    assert(jaap.rating === 2018.24 +- 0.01)
    assert(piet.rating === 2181.76 +- 0.01)
  }


  test("elo2") {
    val piet = new Player("Piet", 1500)
    val mies = new Player("Mies", 1700)
    val ella = new Player("Ella", 1400)

    val players = List(piet,mies,ella)

    val game1 = new Game(mies,piet,0.0)
    val game2 = new Game(ella, mies, 1)
    val game3 = new Game(piet, ella,0)

    val games = List(game1,game2,game3)

    Exercises.updateEloScores(players, games)

    assert(piet.rating === 1502.87 +- 0.01)
    assert(mies.rating === 1709.39 +- 0.01)
    assert(ella.rating === 1387.74 +- 0.01)
  }

  test("elo3") {
    val jaap = new Player("Jaap",2000)
    val piet = new Player("Piet", 2200)
    val mies = new Player("Mies", 2600)
    val ella = new Player("Ella", 1900)

    val players = List(jaap,piet,mies,ella)

    val game1 = new Game(jaap,piet,0.0)
    val game2 = new Game(mies,piet,0.0)
    val game3 = new Game(ella, mies, 0)
    val game4 = new Game(ella, jaap, 1.0)
    val game5 = new Game(piet, ella,0)
    val game6 = new Game(mies,jaap,0)

    val games = List(game1,game2,game3,game4,game5,game6)

    Exercises.updateEloScores(players, games)

    assert(jaap.rating === 2026.13 +- 0.01)
    assert(piet.rating === 2183.20 +- 0.01)
    assert(mies.rating === 2579.34 +- 0.01)
    assert(ella.rating === 1911.31 +- 0.01)
  }

  def testSplit(a : Array[Int]) = {
    val (l,r) = Exercises.splitArray(a)
    assert(l.size == a.length / 2)
    assert((l ++ r).sameElements(a))
  }

  test("split0") {
    testSplit(Array(5,3,10,5,6,1,3))
  }

  test("split1") {
    testSplit(Array(6,5,3,6,1,3))
  }

  test("split2" ) {
    testSplit(Array(6,5,3,6,1,3,8,5,3))
  }

  test("merge0") {
    val ar = Exercises.mergeSortedArrays(Array(1, 3, 5, 6, 10), Array(1, 4, 6, 8))
    assert(ar.sameElements(Array(1,1,3,4,5,6,6,8,10)))
  }

  test("merge1") {
    val ar = Exercises.mergeSortedArrays(Array(1, 3,5,7,9), Array(2,4,6,8))
    assert(ar.sameElements(Array(1,2,3,4,5,6,7,8,9)))
  }

  test("merge2") {
    val ar = Exercises.mergeSortedArrays(Array(1, 2,5,8,10), Array(2,2,6,8,9))
    assert(ar.sameElements(Array(1,2,2,2,5,6,8,8,9,10)))
  }

  test("merge3") {
    val ar = Exercises.mergeSortedArrays(Array(1, 3,5,10), Array(2,8))
    assert(ar.sameElements(Array(1,2,3,5,8,10)))
  }

  test("mergesort0") {
    val a = Array(6,5,3,1,8,7,2,4)
    val b = Exercises.mergeSort(a)
    assert(b.sameElements(Array(1,2,3,4,5,6,7,8)))
  }

  test("mergesort1") {
    val a = Array(7,6,3,6,8,2,1,5,4)
    val b = Exercises.mergeSort(a)
    assert(b.sameElements(Array(1,2,3,4,5,6,6,7,8)))
  }

  test("mergesort2") {
    val a = Array(8,7,6,5,4,3,2,1)
    val b = Exercises.mergeSort(a)
    assert(b.sameElements(Array(1,2,3,4,5,6,7,8)))
  }
  override def timeLimit: Span = Span(1,Seconds)
  // this is need to actually stop when the buggy code contains an infinite loop...
  override val defaultTestSignaler: Signaler = ReallyStopSignaler
}

object ReallyStopSignaler extends Signaler {
  override def apply(testThread: Thread): Unit = {
    StopRunningNow.stopRunningNowUnsafe(testThread)
  }
}
