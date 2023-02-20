package entities

class Player(
    attack : Int,
    defence : Int,
    damageRange : IntRange,
    maxHealth : Int,
) : Creature(attack, defence, damageRange, maxHealth){

    private var healingLeft = 3

    fun heal(){
        if(healingLeft == 0) {
            println("no healing left")
            return
        }
        health += maxHealth/2
        healingLeft--
    }

    override fun toString(): String {
        return super.toString() + "healing left = $healingLeft\n"
    }
}