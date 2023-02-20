package entities

class Monster(
    attack : Int,
    defence : Int,
    damageRange : IntRange,
    maxHealth : Int,
) : Creature(attack, defence, damageRange, maxHealth)