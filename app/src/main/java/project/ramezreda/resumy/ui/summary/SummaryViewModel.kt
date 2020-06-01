package project.ramezreda.resumy.ui.summary

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class SummaryViewModel : ViewModel() {

    private val _text = MutableLiveData<String>().apply {
        value = "This is summary Fragment"
    }
    val text: LiveData<String> = _text
}