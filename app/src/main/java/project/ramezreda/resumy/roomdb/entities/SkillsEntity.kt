package project.ramezreda.resumy.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "skills")
data class SkillsEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var skill: String
)