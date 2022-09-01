package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class DiamondStringTests extends TestBase {
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
        val actual = Exercises.diamondString(3).stripLineEnd
        val expected = diamond3
        val clue = buildDiamondTestClue(actual, expected)

        assert(actual == expected, clue)
    }

    test("diamond4") {
        val actual = Exercises.diamondString(4).stripLineEnd
        val expected = diamond4
        val clue = buildDiamondTestClue(actual, expected)

        assert(actual  == expected, clue)
    }

    test("diamond7") {
        val actual = Exercises.diamondString(7).stripLineEnd
        val expected = diamond7
        val clue = buildDiamondTestClue(actual, expected)

        assert(actual == expected, clue)
    }

    test("diamond12") {
        val actual = Exercises.diamondString(12).stripLineEnd
        val expected = diamond12
        val clue = buildDiamondTestClue(actual, expected)

        assert(actual == expected, clue)
    }
}
