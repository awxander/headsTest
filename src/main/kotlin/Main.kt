import entities.Player

fun main(args: Array<String>) {
    val player1 = Player(10,5,100)
    val player2 = Player(10,5,100)
    player1.hit(player2)
    println(player2.health)
    println(player2)
}