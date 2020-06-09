package project.ramezreda.resumy.ui.education

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class EducationViewModel : ViewModel() {
    private val _text = MutableLiveData<String>().apply {
        value = "This is Education Fragment!"
    }

    val text = _text
}