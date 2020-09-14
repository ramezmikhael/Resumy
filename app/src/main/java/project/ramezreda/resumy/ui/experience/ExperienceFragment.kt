package project.ramezreda.resumy.ui.experience

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import kotlinx.android.synthetic.main.fab_layout.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentExperienceBinding
import project.ramezreda.resumy.ui.BaseFragment

class ExperienceFragment : BaseFragment() {

    private val viewModel: ExperienceViewModel by lazy {
        ViewModelProvider(this).get(ExperienceViewModel::class.java)
    }

    private lateinit var experienceBinding: FragmentExperienceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDataBinding()
        initObservers()

        experienceBinding.fabLayout.fab.setOnClickListener {
            val action = ExperienceFragmentDirections.actionNavExperienceToAddExperienceFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun initObservers() {
        viewModel.experience?.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) {
                experienceBinding.noExperienceTextView.visibility = View.VISIBLE
                experienceBinding.experienceRecyclerView.visibility = View.GONE
            } else {
                experienceBinding.noExperienceTextView.visibility = View.GONE
                experienceBinding.experienceRecyclerView.visibility = View.VISIBLE
            }
        })
    }

    private fun initDataBinding() {
        experienceBinding = (binding as FragmentExperienceBinding)
        experienceBinding.viewModel = viewModel
    }

    override fun getLayoutRes(): Int = R.layout.fragment_experience

}