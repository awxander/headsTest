package entities

open class Creature(
    val attack: Int,
    val defence: Int,
    val damageRange: IntRange,
    val maxHealth: Int
) {

    var health: Int = maxHealth

    companion object {
        const val HIGH_BORDER_ATTACK = 20
        const val HIGH_BORDER_DEFENCE = 20

        const val LOW_BORDER_ATTACK = 1
        const val LOW_BORDER_DEFENCE = 1

    }

    init {
        if (!checkParameters(attack, defence))
            throw IllegalArgumentException("defence and attack must be in range 1-20, health must be > 0")
    }

    private fun checkParameters(//true if parameters ok, else - false
        attack: Int,
        defence: Int,
        maxHealth: Int
    ) = (attack in LOW_BORDER_ATTACK..HIGH_BORDER_ATTACK
            && defence in LOW_BORDER_DEFENCE..HIGH_BORDER_DEFENCE
            && maxHealth > 0)


    private fun getAttackModifier(targetCreatureDefence: Int) = if ((attack - targetCreatureDefence + 1) > 0) {
        attack - targetCreatureDefence + 1
    } else {
        1
    }

    fun hit(targetCreature: Creature) {
        val attackModifier = getAttackModifier(targetCreature.defence)

        var successAttack = false

        for (i in 0 until attackModifier) {
            val res = Dice.`throw`()
            println("rolled $res")
            if (res == 5 || res == 6) {
                successAttack = true
                break
            }
        }
        if (successAttack)
            println("success attack")
        else {
            println("attack failed, not this time")
            return
        }

        targetCreature.health -= this.damageRange.random()

    }

    private fun dead() {
        println("dead")
        
    }
}