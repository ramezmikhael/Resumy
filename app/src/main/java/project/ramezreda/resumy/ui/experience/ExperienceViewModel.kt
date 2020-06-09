package project.ramezreda.resumy.ui.experience

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ExperienceViewModel : ViewModel() {
    private val _text : MutableLiveData<String> = MutableLiveData<String>().apply {
        value = "This is the Experience fragment!"
    }

    var text : MutableLiveData<String> = _text
}