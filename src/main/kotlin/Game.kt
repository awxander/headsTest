import entities.Monster
import entities.Player
import java.io.InputStream
import java.util.*

class Game(inputStream: InputStream) {

    companion object {
        const val DEFAULT_DELAY = 2000L
    }

    private val scanner: Scanner

    init {
        scanner = Scanner(inputStream)
    }

    private fun startBattle(player: Player) {
        firstFight(player)
        if (player.alive()) {
            printlnWithDelay("yea, you win!")
        } else {
            printlnWithDelay("oops, you lose(")
            return
        }

    }

    private fun firstFight(player: Player) {

        val monster = Monster(5, 5, 1..5, 40)
        printlnWithDelay("your opponent small monster:\n$monster")
        printlnWithDelay("small monster: ugu gaga...")
        startFight(player, monster)
    }

    private fun startFight(player: Player, monster: Monster) {
        printlnWithDelay("fight starts")

        var i = 0
        while (player.alive() && monster.alive()) {
            if (i % 2 == 0) {
                printlnWithDelay("you attack, ")
                player.hit(monster)
                println()
            } else {
                printlnWithDelay("monster attacks, ")
                monster.hit(player)
                println()
                if (player.health() < player.maxHealth / 2) {
                    askAboutHeal(player)
                }
            }
            i++

        }

    }

    private fun askAboutHeal(player: Player){
        printlnWithDelay("your hero has ${player.health()} hp, maybe heal?(yes/no)")
        val res = tryGetUserInput()
        if (res == "yes") {
            player.heal()
            println()
            printlnWithDelay("now your hp is ${player.health()}")
        } else {
            printlnWithDelay("ok, whatever, you the boss")
        }

    }

    private fun printlnWithDelay(str: String, delayMillis: Long = DEFAULT_DELAY) {
        println(str)
        Thread.sleep(delayMillis)
    }


    private fun tryGetUserInput(): String = try {
        scanner.next()
    } catch (e: Exception) {
        println(e.message)
        printlnWithDelay("try again")
        tryGetUserInput()
    }

    fun startGame() {

        printlnWithDelay("game starts!")
        val player = Player(10, 5, 1..20, 100)
        printlnWithDelay("your hero:\n$player")

        printlnWithDelay("ready to  fight?(yes/no)")
        println()
        val userInput = tryGetUserInput()

        if (userInput.replace(" ", "", true) == "yes")
            startBattle(player)
        else
            printlnWithDelay("not this time i guess...")

    }
}