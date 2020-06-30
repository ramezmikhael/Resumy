package project.ramezreda.resumy.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import project.ramezreda.resumy.roomdb.entities.SkillsEntity

@Dao
interface SkillsDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(skillsEntity: SkillsEntity) : Long?

    @Delete
    suspend fun delete(skillsEntity: SkillsEntity)

    @Query("SELECT * FROM skills")
    fun getAll() : LiveData<List<SkillsEntity?>>
}