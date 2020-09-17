package project.ramezreda.resumy.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "experience")
data class ExperienceEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var company: String? = null,
    var location: String? = null,
    var startDate: String? = null,
    var endDate: String? = null,
    var position: String? = null,
    var description: String? = null
)