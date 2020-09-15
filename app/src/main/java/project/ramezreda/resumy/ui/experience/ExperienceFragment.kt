package project.ramezreda.resumy.ui.experience

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fab_layout.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentExperienceBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.ui.skills.SkillsDataAdapter
import javax.inject.Inject

class ExperienceFragment : BaseFragment() {

    @Inject
    lateinit var adapter: ExperienceDataAdapter

    private val viewModel: ExperienceViewModel by lazy {
        ViewModelProvider(this).get(ExperienceViewModel::class.java)
    }

    private lateinit var experienceBinding: FragmentExperienceBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()
        initDataBinding()
        initObservers()
        initSkillsRecyclerView()

        experienceBinding.fabLayout.fab.setOnClickListener {
            val action = ExperienceFragmentDirections.actionNavExperienceToAddExperienceFragment()
            it.findNavController().navigate(action)
        }

        return binding.root
    }

    private fun initObservers() {
        viewModel.experience?.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                experienceBinding.noExperienceTextView.visibility = View.VISIBLE
                experienceBinding.experienceRecyclerView.visibility = View.GONE
            } else {
                experienceBinding.noExperienceTextView.visibility = View.GONE
                experienceBinding.experienceRecyclerView.visibility = View.VISIBLE

                viewModel.experience?.value?.let { experience ->
                    adapter.setData(experience)
                    adapter.notifyDataSetChanged()
                }
            }
        })
    }

    private fun initDataBinding() {
        experienceBinding = (binding as FragmentExperienceBinding)
        experienceBinding.viewModel = viewModel
    }

    private fun initSkillsRecyclerView() {
        adapter.clickableItems = true
        val layoutManager = LinearLayoutManager(context)
//        adapter.itemsSelecteded = this
        experienceBinding.experienceRecyclerView.adapter = adapter
        experienceBinding.experienceRecyclerView.layoutManager = layoutManager
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    requireContext().applicationContext as Application,
                    requireContext()
                )
            )
            .notificationsModule(NotificationsModule(requireContext()))
            .build().inject(this)
    }

    override fun getLayoutRes(): Int = R.layout.fragment_experience

}