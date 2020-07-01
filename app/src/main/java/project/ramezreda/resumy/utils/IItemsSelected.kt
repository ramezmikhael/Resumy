package project.ramezreda.resumy.utils

import project.ramezreda.resumy.roomdb.entities.SkillsEntity

interface IItemsSelected {
    fun onItemsSelected(skills: MutableList<SkillsEntity>)
}