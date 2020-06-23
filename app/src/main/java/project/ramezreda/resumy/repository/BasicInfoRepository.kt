package project.ramezreda.resumy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import androidx.room.Room
import project.ramezreda.resumy.roomdb.ResumeRoomDatabase
import project.ramezreda.resumy.roomdb.dao.BasicInfoDao
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity

class BasicInfoRepository(application: Application) {
    private var basicInfoDao: BasicInfoDao

    init {
        val db = Room.databaseBuilder(application,
            ResumeRoomDatabase::class.java,
            "resumy_database")
            .build()

        basicInfoDao = db.basicInfoDao()!!
    }

    suspend fun insert(basicInfoEntity: BasicInfoEntity){
        basicInfoDao.insert(basicInfoEntity)
    }

    fun update(basicInfoEntity: BasicInfoEntity) {
        basicInfoDao.update(basicInfoEntity)
    }

    fun getTopOne() : LiveData<BasicInfoEntity?> {
        return basicInfoDao.getTopOne()
    }
}