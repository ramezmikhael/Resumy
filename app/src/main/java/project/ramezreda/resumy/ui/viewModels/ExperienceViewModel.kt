package project.ramezreda.resumy.ui.viewModels

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.repository.ExperienceRepository
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.utils.OperationState
import project.ramezreda.resumy.utils.ScreenMode
import javax.inject.Inject

class ExperienceViewModel(application: Application) : AndroidViewModel(application) {

    init {
        DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(application, application))
            .notificationsModule(NotificationsModule(application))
            .build()
            .inject(this)
    }

    @Inject
    lateinit var repository: ExperienceRepository

    private val _text: MutableLiveData<String> = MutableLiveData<String>().apply {
        value = application.getString(R.string.no_experience)
    }
    val state: MutableLiveData<OperationState> = MutableLiveData(OperationState.StandBy)
    var text: MutableLiveData<String> = _text
    val experience: MutableLiveData<ExperienceEntity> = MutableLiveData()
    var screenMode: ScreenMode = ScreenMode.Add
    var experienceList: LiveData<List<ExperienceEntity?>> = repository.getAll()

    fun save() {
        if (screenMode == ScreenMode.Add) {
            addNew()
        } else {
            updateExperience()
        }
    }

    private fun updateExperience() {
        val job = GlobalScope.async {
            experience.value?.let { repository.update(it) }
        }
        GlobalScope.launch {
            val result = job.await()
            translateResult(result?.toLong())
        }
    }

    private fun translateResult(result: Long?) {
        if (result != null) {
            when {
                result > 0 -> state.postValue(OperationState.Success)
                result < 0 -> state.postValue(OperationState.Failed)
                else -> state.postValue(OperationState.StandBy)
            }
        }
    }

    private fun addNew() {
        val job = GlobalScope.async {
            experience.value?.let { repository.insert(it) }
        }
        GlobalScope.launch {
            val result = job.await()
            translateResult(result)
        }
    }
}