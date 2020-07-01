package project.ramezreda.resumy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.roomdb.ResumeRoomDatabase
import project.ramezreda.resumy.roomdb.dao.SkillsDao
import project.ramezreda.resumy.roomdb.entities.SkillsEntity
import javax.inject.Inject

class SkillsRepository @Inject constructor(application: Application) {
    private var skillsDao: SkillsDao?

    init {
        val db = ResumeRoomDatabase.getDatabase(application)

        skillsDao = db?.skillsDao()
    }

    suspend fun insert(skillsEntity: SkillsEntity): Long? {
        return skillsDao?.insert(skillsEntity)
    }

    suspend fun delete(skillsEntity: SkillsEntity) {

    }

    fun getAll(): LiveData<List<SkillsEntity?>> {
        return skillsDao?.getAll()!!
    }

    suspend fun deleteMany(ids: List<Int>): Int? {
        return skillsDao?.deleteMany(ids)
    }
}