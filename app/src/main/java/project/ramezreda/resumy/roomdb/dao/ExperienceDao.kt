package project.ramezreda.resumy.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity

@Dao
interface ExperienceDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(experienceEntity: ExperienceEntity) : Long

    @Update
    suspend fun update(experienceEntity: ExperienceEntity) : Int

    @Query("DELETE FROM experience")
    fun deleteAll()

    @Query("SELECT * FROM experience")
    fun getAll() : LiveData<List<ExperienceEntity?>>

    @Delete
    fun delete(experienceEntity: ExperienceEntity)
}