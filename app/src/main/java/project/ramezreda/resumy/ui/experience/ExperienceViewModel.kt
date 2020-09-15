package project.ramezreda.resumy.ui.experience

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.ramezreda.resumy.R
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.repository.ExperienceRepository
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import javax.inject.Inject

class ExperienceViewModel(application: Application) : AndroidViewModel(application) {

    @Inject
    lateinit var repository: ExperienceRepository

    init {
        DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(application, application))
            .notificationsModule(NotificationsModule(application))
            .build()
            .inject(this)
    }

    private val _text : MutableLiveData<String> = MutableLiveData<String>().apply {
        value = application.getString(R.string.no_experience)
    }

    var text : MutableLiveData<String> = _text
    var experience : LiveData<List<ExperienceEntity?>> = repository.getAll()
}