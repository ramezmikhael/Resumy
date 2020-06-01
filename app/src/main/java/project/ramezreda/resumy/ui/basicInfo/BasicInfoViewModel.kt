package project.ramezreda.resumy.ui.basicInfo

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class BasicInfoViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is basicInfo Fragment"
    }
    val text: LiveData<String> = _text
}