package entities

import kotlin.random.Random

class Dice {
    companion object {
        fun `throw`(): Int {
            return Random.nextInt(1, 7)
        }
    }
}