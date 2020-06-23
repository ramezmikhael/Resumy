package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity


class BasicInfoViewModel(application: Application) : AndroidViewModel(application) {

    private var mRepository: BasicInfoRepository? = null

    var basicInfo: LiveData<BasicInfoEntity?>? = null

    init {
        mRepository = BasicInfoRepository(application)
        basicInfo = mRepository?.getTopOne()
    }

    suspend fun insert(basicInfoEntity: BasicInfoEntity?) {
        if (basicInfoEntity != null) {
            mRepository?.insert(basicInfoEntity)
        }
    }
}