package project.ramezreda.resumy.ui.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentExperienceBinding
import project.ramezreda.resumy.ui.BaseFragment

class ExperienceFragment : BaseFragment() {

    private val viewModel: ExperienceViewModel by lazy {
        ViewModelProvider(this).get(ExperienceViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        (binding as FragmentExperienceBinding).viewModel = viewModel

        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        // TODO: Use the ViewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_experience

}