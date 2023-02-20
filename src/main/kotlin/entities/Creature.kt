package entities

open class Creature(
    private val attack: Int,
    private val defence: Int,
    private val damageRange: IntRange,
    val maxHealth: Int
) {

    protected var health = maxHealth
    private var alive = true

    companion object {
        const val HIGH_BORDER_ATTACK = 20
        const val HIGH_BORDER_DEFENCE = 20

        const val LOW_BORDER_ATTACK = 1
        const val LOW_BORDER_DEFENCE = 1

    }

    fun alive() = alive

    fun health() = health

    init {
        if (!checkParameters(attack, defence, health))
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

    open fun hit(targetCreature: Creature) {

        if (!targetCreature.alive) {
            println("creature already dead")
            return
        }

        val attackModifier = getAttackModifier(targetCreature.defence)

        val successAttack = checkSuccessAttack(attackModifier)

        if (successAttack)
            println("success attack")
        else {
            println("attack failed, not this time")
            return
        }
        val damage = this.damageRange.random()
        println("$damage damage dealt")
        targetCreature.health -= damage
        if (targetCreature.health <= 0) targetCreature.kill()
    }


    private fun checkSuccessAttack(attackModifier: Int): Boolean {
        for (i in 0 until attackModifier) {
            val res = Dice.`throw`()
            println("rolled $res")
            if (res == 5 || res == 6) {
                return true
            }
        }
        return false

    }


    private fun kill() {
        alive = false
    }

    override fun toString(): String {
        return "attack = $attack\n" +
                "defence = $defence\n" +
                "damage range = $damageRange\n" +
                "health = $health\n" +
                "alive = $alive\n"
    }
}