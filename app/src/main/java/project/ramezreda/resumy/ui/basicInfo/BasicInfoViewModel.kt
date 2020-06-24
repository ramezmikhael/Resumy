package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import javax.inject.Inject


class BasicInfoViewModel @Inject constructor(application: Application) : AndroidViewModel(application) {

    private var repository: BasicInfoRepository? = BasicInfoRepository(application)

    var basicInfo: LiveData<BasicInfoEntity?>? = repository?.getTopOne()

    suspend fun update(basicInfoEntity: BasicInfoEntity?) {
        if (basicInfoEntity != null) {
            repository?.update(basicInfoEntity)
        }
    }
}