package project.ramezreda.resumy.ui.summary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import javax.inject.Inject

class SummaryViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var repository: BasicInfoRepository? = null

    var basicInfo: LiveData<BasicInfoEntity?>? = null

    init {
        repository = BasicInfoRepository(application)
        basicInfo = repository?.getTopOne()
    }

    suspend fun update(basicInfoEntity: BasicInfoEntity?) {
        if (basicInfoEntity != null) {
            repository?.update(basicInfoEntity)
        }
    }
}