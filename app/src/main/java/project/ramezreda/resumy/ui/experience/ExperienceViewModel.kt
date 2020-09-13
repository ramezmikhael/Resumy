package project.ramezreda.resumy.ui.experience

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import project.ramezreda.resumy.R
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity

class ExperienceViewModel(application: Application) : AndroidViewModel(application) {
    private val _text : MutableLiveData<String> = MutableLiveData<String>().apply {
        value = application.getString(R.string.no_experience)
    }

    var text : MutableLiveData<String> = _text
    var experience : MutableLiveData<List<ExperienceEntity?>>? = null
}