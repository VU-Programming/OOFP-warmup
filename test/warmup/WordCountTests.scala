package warmup

import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class WordCountTests extends TestBase {
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
}
