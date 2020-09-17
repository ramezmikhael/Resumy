package project.ramezreda.resumy.ui.skills

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.repository.SkillsRepository
import project.ramezreda.resumy.roomdb.entities.SkillsEntity
import javax.inject.Inject

class SkillsViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    @Inject
    lateinit var skillsRepository: SkillsRepository

    init {
        DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(application, application))
            .notificationsModule(NotificationsModule(application))
            .build()
            .inject(this)
    }

    val skills: LiveData<List<SkillsEntity?>> = skillsRepository.getAll()

    suspend fun insert(skillsEntity: SkillsEntity): Long? {
        return skillsRepository.insert(skillsEntity)
    }

    suspend fun deleteMany(ids: List<Int>) : Int? {

//        Log.d("IDS", ids)
        return skillsRepository.deleteMany(ids)
    }


}