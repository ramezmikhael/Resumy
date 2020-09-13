package project.ramezreda.resumy.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.util.*

@Entity(tableName = "experience")
data class ExperienceEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 0,

    var company: String,
    var location: String?,
    var startDate: String,
    var endDate: String?,
    var position: String,
    var description: String?
)