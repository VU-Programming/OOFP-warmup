package warmup

import org.junit.runner.RunWith
import org.scalactic.Tolerance._
import org.scalatest.FunSuite
import org.scalatest.concurrent.{Signaler, TimeLimitedTests}
import org.scalatest.time.{Seconds, Span}
import org.scalatestplus.junit.JUnitRunner
import warmup.Exercises.{Game, Player}

@RunWith(classOf[JUnitRunner])
class Tests extends FunSuite with TimeLimitedTests {
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

  test("testaverage0") {
    assert(Exercises.averageGrade("NS NS 9 NS NS 5") == 7)
  }

  test("testaverage1") {
    assert(Exercises.averageGrade("8 8.2 NS NS 9 4 1 5.5") == 5.95)
  }

  test("testaverage2") {
    assert(Exercises.averageGrade("7 8 5.5 9.2 NS 7.8 5.4 NS NS 7 9.2") == 7.387499999999999)
  }

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

  private val diamond3 =
    """ #
       |###
       | #""".stripMargin

  private val diamond4 =
    """ #
      |###
      |###
      | #""".stripMargin

  private val diamond7 =
    """   #
      |  ###
      | #####
      |#######
      | #####
      |  ###
      |   #""".stripMargin

  private val diamond12 =
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

  private def buildDiamondTestClue(actual: String, expected: String): String = {
    def lineCount(s: String): Int = {
      s.count(_ == '\n')
    }

    def highestLineCount(s1: String, s2: String): Int = {
      Math.max(lineCount(s1), lineCount(s2))
    }

    def matchedLength(input: Array[String], length: Int): Array[String] = {
      val diff = length - input.length
      if (diff < 1) input else input ++ Array.fill(diff)("")
    }

    val requiredLength = 1 + highestLineCount(expected, actual)
    val linesExpected = matchedLength(expected.split("\n"), requiredLength)
    val linesActual = matchedLength(actual.split("\n"), requiredLength)

    val formattedLinesActual = linesActual.map(l => String.format("%1$-20s", l))

    (formattedLinesActual zip linesExpected)
        .map(s => s._1 + s._2)
        .mkString("\n")
        .prependedAll(String.format("\n%1$-20s%2$s\n", "Your output:", "Expected output:"))
  }

  test("diamond3") {
    val actual = Exercises.diamondString(3)
    val clue = buildDiamondTestClue(actual, diamond3)

    assert(actual == diamond3, clue)
  }

  test("diamond4") {
    val actual = Exercises.diamondString(4)
    val clue = buildDiamondTestClue(actual, diamond4)

    assert(actual == diamond4, clue)
  }

  test("diamond7") {
    val actual = Exercises.diamondString(7)
    val clue = buildDiamondTestClue(actual, diamond7)

    assert(actual == diamond7, clue)
  }

  test("diamond12") {
    val actual = Exercises.diamondString(12)
    val clue = buildDiamondTestClue(actual, diamond12)

    assert(actual == diamond12, clue)
  }

  test("wordcount0") {
    val actual = Exercises.wordCount("the monkey hugs the monkey")
    val expected = Map("the" -> 2, "monkey" -> 2, "hugs" -> 1)
    assert(actual == expected)
  }

  test("wordcount1") {
    val actual = Exercises.wordCount("A horse, a horse! My kingdom for a horse!")
    val expected = Map("for" -> 1, "a" -> 3, "my" -> 1, "horse" -> 3, "kingdom" -> 1)
    assert(actual == expected)
  }


  test("wordcount2") {
    val actual = Exercises.wordCount("A woman is but a woman. A man is but a man. A child is but a child.")
    val expected = Map("is" -> 3, "but" -> 3, "a" -> 6, "man" -> 2, "child" -> 2, "woman" -> 2)
    assert(actual == expected)

  }

  test("elo1") {
    val jaap = new Player("Jaap", 2000)
    val piet = new Player("Piet", 2200)
    val game = new Game(jaap, piet, 0.0)
    Exercises.updateEloScores(List(jaap, piet), List(game))
    assert(jaap.rating === 2018.24 +- 0.01)
    assert(piet.rating === 2181.76 +- 0.01)
  }


  test("elo2") {
    val piet = new Player("Piet", 1500)
    val mies = new Player("Mies", 1700)
    val ella = new Player("Ella", 1400)

    val players = List(piet, mies, ella)

    val game1 = new Game(mies, piet, 0.0)
    val game2 = new Game(ella, mies, 1)
    val game3 = new Game(piet, ella, 0)

    val games = List(game1, game2, game3)

    Exercises.updateEloScores(players, games)

    assert(piet.rating === 1502.87 +- 0.01)
    assert(mies.rating === 1709.39 +- 0.01)
    assert(ella.rating === 1387.74 +- 0.01)
  }

  test("elo3") {
    val jaap = new Player("Jaap", 2000)
    val piet = new Player("Piet", 2200)
    val mies = new Player("Mies", 2600)
    val ella = new Player("Ella", 1900)

    val players = List(jaap, piet, mies, ella)

    val game1 = new Game(jaap, piet, 0.0)
    val game2 = new Game(mies, piet, 0.0)
    val game3 = new Game(ella, mies, 0)
    val game4 = new Game(ella, jaap, 1.0)
    val game5 = new Game(piet, ella, 0)
    val game6 = new Game(mies, jaap, 0)

    val games = List(game1, game2, game3, game4, game5, game6)

    Exercises.updateEloScores(players, games)

    assert(jaap.rating === 2026.13 +- 0.01)
    assert(piet.rating === 2183.20 +- 0.01)
    assert(mies.rating === 2579.34 +- 0.01)
    assert(ella.rating === 1911.31 +- 0.01)
  }

  private def testSplit(input : Array[Int]): Unit = {
    val (l,r) = Exercises.splitArray(input)
    val clue = String.format("\nInput: %s\nYour output: (%s)(%s)\n", input.mkString(" "), l.mkString(" "), r.mkString(" "))

    assert(l.length == input.length / 2, clue)
    assert((l ++ r).sameElements(input), clue)
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

  private def mergeTest(first: Array[Int], second: Array[Int], answer: Array[Int]): Unit = {
    val actual = Exercises.mergeSortedArrays(first, second)
    val clue = String.format("\nInput: (%s)(%s)\nYour output: %s\n", first.mkString(" "), second.mkString(" "), actual.mkString(" "))

    assert(actual.sameElements(answer), clue)
  }

  test("merge0") {
    val first = Array(1,3,5,6,10)
    val second = Array(1,4,6,8)
    val answer = Array(1,1,3,4,5,6,6,8,10)

    mergeTest(first, second, answer)
  }

  test("merge1") {
    val first = Array(1,3,5,7,9)
    val second = Array(2,4,6,8)
    val answer = Array(1,2,3,4,5,6,7,8,9)

    mergeTest(first, second, answer)
  }

  test("merge2") {
    val first = Array(1,2,5,8,10)
    val second = Array(2,2,6,8,9)
    val answer = Array(1,2,2,2,5,6,8,8,9,10)

    mergeTest(first, second, answer)
  }

  test("merge3") {
    val first = Array(1,3,5,10)
    val second = Array(2,8)
    val answer = Array(1,2,3,5,8,10)

    mergeTest(first, second, answer)
  }

  private def mergeSortTest(input: Array[Int], answer: Array[Int]): Unit = {
    val output = Exercises.mergeSort(input)
    val clue = String.format("\nInput: %s\nYour output: %s\n", input.mkString(" "), output.mkString(" "))

    assert(output.sameElements(answer), clue)
  }

  test("mergesort0") {
    val input = Array(6,5,3,1,8,7,2,4)
    val answer = Array(1,2,3,4,5,6,7,8)

    mergeSortTest(input, answer)
  }

  test("mergesort1") {
    val input = Array(7,6,3,6,8,2,1,5,4)
    val answer = Array(1,2,3,4,5,6,6,7,8)

    mergeSortTest(input, answer)
  }

  test("mergesort2") {
    val input = Array(8,7,6,5,4,3,2,1)
    val answer = Array(1,2,3,4,5,6,7,8)

    mergeSortTest(input, answer)
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
