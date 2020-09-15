package project.ramezreda.resumy.ui.experience

import android.app.Application
import android.view.View
import androidx.databinding.BaseObservable
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.*
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.repository.ExperienceRepository
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.utils.OperationState
import javax.inject.Inject

class AddExperienceViewModel(application: Application) : AndroidViewModel(application) {

    val experience: MutableLiveData<ExperienceEntity> = MutableLiveData()
    val state: MutableLiveData<Long> = MutableLiveData(0)

    @Inject
    lateinit var repository: ExperienceRepository

    init {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    application,
                    application
                )
            )
            .notificationsModule(NotificationsModule(application))
            .build()
            .inject(this)
    }

    fun save() {
        val job = GlobalScope.async {
            experience.value?.let { repository.insert(it) }
        }
        GlobalScope.launch {
            val result = job.await()
            if (result != null) {
                state.postValue(result)
            }
        }
    }
}