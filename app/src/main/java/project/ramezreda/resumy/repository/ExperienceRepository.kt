package project.ramezreda.resumy.repository

import android.app.Application
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.roomdb.ResumeRoomDatabase
import project.ramezreda.resumy.roomdb.dao.ExperienceDao
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import javax.inject.Inject

class ExperienceRepository @Inject constructor(application: Application) {
    private var experienceDao: ExperienceDao?

    init {
        val db = ResumeRoomDatabase.getDatabase(application)

        experienceDao = db?.experienceDao()
    }

    suspend fun insert(experienceEntity: ExperienceEntity): Long? {
        return experienceDao?.insert(experienceEntity)
    }

    fun delete(experienceEntity: ExperienceEntity) {
        experienceDao?.delete(experienceEntity)
    }

    fun getAll(): LiveData<List<ExperienceEntity?>> {
        return experienceDao?.getAll()!!
    }

    suspend fun update(it: ExperienceEntity) : Int? {
        return experienceDao?.update(it)
    }
}