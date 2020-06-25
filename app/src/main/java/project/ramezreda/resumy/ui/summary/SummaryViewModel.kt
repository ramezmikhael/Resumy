package project.ramezreda.resumy.ui.summary

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import javax.inject.Inject

class SummaryViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var repository: BasicInfoRepository? = null

    var basicInfo: LiveData<List<BasicInfoEntity?>>? = null

    init {
        repository = BasicInfoRepository(application)
        basicInfo = repository?.getAll()
    }

    suspend fun update(basicInfoEntity: BasicInfoEntity?) : Int? {
        if (basicInfoEntity != null) {
            return repository?.update(basicInfoEntity)
        }
        return null
    }
}