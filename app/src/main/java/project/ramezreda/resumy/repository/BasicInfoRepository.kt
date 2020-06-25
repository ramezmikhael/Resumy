package project.ramezreda.resumy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.roomdb.ResumeRoomDatabase
import project.ramezreda.resumy.roomdb.dao.BasicInfoDao
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import javax.inject.Inject

class BasicInfoRepository @Inject constructor(application: Application) {
    private var basicInfoDao: BasicInfoDao?

    init {
        val db = ResumeRoomDatabase.getDatabase(application)

        basicInfoDao = db?.basicInfoDao()
    }

    suspend fun insert(basicInfoEntity: BasicInfoEntity){
        basicInfoDao?.insert(basicInfoEntity)
    }

    suspend fun update(basicInfoEntity: BasicInfoEntity) : Int? {
        return basicInfoDao?.update(basicInfoEntity)
    }

    fun getAll() : LiveData<List<BasicInfoEntity?>> {
        return basicInfoDao?.getAll()!!
    }
}