package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import javax.inject.Inject

class BasicInfoViewModel @Inject constructor(application: Application) :
    AndroidViewModel(application) {

    @Inject
    lateinit var repository: BasicInfoRepository

    init {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(application,
                application)
            )
            .notificationsModule(NotificationsModule(application))
            .build()
            .inject(this)
    }

    var basicInfo: LiveData<List<BasicInfoEntity?>>? = repository.getAll()

    suspend fun update(basicInfoEntity: BasicInfoEntity?) : Int? {
        if (basicInfoEntity != null) {
            return repository.update(basicInfoEntity)
        }
        return null
    }
}