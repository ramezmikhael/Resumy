package project.ramezreda.resumy.ui.skills

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_skills.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.databinding.FragmentSkillsBinding

class SkillsFragment: BaseFragment() {

    private val skillsViewModel: SkillsViewModel by lazy { ViewModelProvider(this).get(SkillsViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val skillsBinding = (binding as FragmentSkillsBinding)
        skillsBinding.viewModel = skillsViewModel

        skillsViewModel.text.observe(viewLifecycleOwner, Observer {
            text_skills.text = it
        })

        return binding.root
    }

    override fun getLayoutRes(): Int = R.layout.fragment_skills

}