package project.ramezreda.resumy.roomdb.entities

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "basic_info")
data class BasicInfoEntity(
    @PrimaryKey(autoGenerate = true)
    var id: Int = 1,

    @ColumnInfo(name = "full_name")
    var fullName: String,

    var email: String,
    var phone: String,
    var summary: String,

    @ColumnInfo(typeAffinity = ColumnInfo.BLOB)
    var picture: ByteArray?
)