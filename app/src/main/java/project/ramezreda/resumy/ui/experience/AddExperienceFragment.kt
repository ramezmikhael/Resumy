package project.ramezreda.resumy.ui.experience

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import project.ramezreda.resumy.R

class AddExperienceFragment : Fragment() {

    companion object {
        fun newInstance() = AddExperienceFragment()
    }

    private lateinit var viewModel: AddExperienceViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.add_experience_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(AddExperienceViewModel::class.java)
        // TODO: Use the ViewModel
    }

}