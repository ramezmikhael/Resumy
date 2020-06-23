package project.ramezreda.resumy.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var repository: BasicInfoRepository? = null

    var basicInfo: LiveData<BasicInfoEntity?>? = null

    init {
        repository = BasicInfoRepository(application)
        basicInfo = repository?.getTopOne()
    }
}