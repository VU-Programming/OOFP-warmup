package warmup

import warmup.Exercises.{Game, Player}
import org.scalactic.Tolerance._
import org.junit.runner.RunWith
import org.scalatestplus.junit.JUnitRunner
import warmup.infrastructure.TestBase

@RunWith(classOf[JUnitRunner])
class EloScoresTests extends TestBase {
    test("elo1") {
        val jaap = new Player("Jaap", 2000)
        val piet = new Player("Piet", 2200)
        val game = new Game(jaap, piet, 0.0)
        Exercises.updateEloScores(List(jaap, piet), List(game))

        val jaapResult = jaap.rating === 2018.24 +- 0.01
        val pietResult = piet.rating === 2181.76 +- 0.01
        val finalResult = jaapResult && pietResult

        assert(finalResult)
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

        val pietResult = piet.rating === 1502.87 +- 0.01
        val miesResult = mies.rating === 1709.39 +- 0.01
        val ellaResult = ella.rating === 1387.74 +- 0.01
        val finalResult = pietResult && miesResult && ellaResult

        assert(finalResult)
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

        val jaapResult = jaap.rating === 2026.13 +- 0.01
        val pietResult = piet.rating === 2183.20 +- 0.01
        val miesResult = mies.rating === 2579.34 +- 0.01
        val ellaResult = ella.rating === 1911.31 +- 0.01
        val finalResult = jaapResult && pietResult && miesResult && ellaResult

        assert(finalResult)
    }

    test("elo4") {
        val max = new Player("Max", 2882)
        val anish = new Player("Anish", 2872)
        val jan = new Player("Jan", 2852)
        val loek = new Player("Loek", 2742)

        val players = List(max, anish, jan,loek)

        val game1 = new Game(max, anish, 0.5)
        val game2 = new Game(anish, jan, 1)
        val game3 = new Game(jan, loek, 0.5)
        val game4 = new Game(jan, max, 0.5)
        val game5 = new Game(max, loek, 1)
        val game6 = new Game(jan, loek, 0.5)
        val game7 = new Game(anish, loek, 0)

        val games = List(game1, game2, game3,game4,game5,game6, game7)

        Exercises.updateEloScores(players, games)


        val maxResult = max.rating === 2864.03 +- 0.01
        val anishResult = anish.rating === 2867.36 +- 0.01
        val janResult = jan.rating === 2858.36 +- 0.01
        val loekResult = loek.rating === 2758.24 +- 0.01
        val finalResult = maxResult && anishResult && janResult && loekResult

        assert(maxResult)
    }
}
