package project.ramezreda.resumy.roomdb.dao

import androidx.lifecycle.LiveData
import androidx.room.*
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity

@Dao
interface BasicInfoDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(basicInfoEntity: BasicInfoEntity?)

    @Update
    suspend fun update(basicInfoEntity: BasicInfoEntity?) : Int

    @Query("DELETE FROM basic_info")
    fun deleteAll()

    @Query("SELECT * FROM basic_info LIMIT 1")
    fun getTopOne() : LiveData<BasicInfoEntity?>
}