package project.ramezreda.resumy.ui.experience

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.fab_layout.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentExperienceBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.utils.OperationState
import project.ramezreda.resumy.utils.ScreenMode
import javax.inject.Inject

class ExperienceFragment : BaseFragment(), ExperienceClickListener {

    override fun getLayoutRes(): Int = R.layout.fragment_experience

    @Inject
    lateinit var adapter: ExperienceDataAdapter

    private val viewModel: ExperienceViewModel by activityViewModels()

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
            setAddMode()
            openAddExperienceFragment()
        }

        return binding.root
    }

    private fun openAddExperienceFragment() {
        val action = ExperienceFragmentDirections.actionNavExperienceToAddExperienceFragment()
        findNavController().navigate(action)
    }

    private fun initObservers() {
        viewModel.experienceList?.observe(viewLifecycleOwner, Observer {
            if (it.isEmpty()) {
                experienceBinding.noExperienceTextView.visibility = View.VISIBLE
                experienceBinding.experienceRecyclerView.visibility = View.GONE
            } else {
                experienceBinding.noExperienceTextView.visibility = View.GONE
                experienceBinding.experienceRecyclerView.visibility = View.VISIBLE

                viewModel.experienceList?.value?.let { experience ->
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
        adapter.clickListener = this
        val layoutManager = LinearLayoutManager(context)
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

    override fun onExperienceSelected(experienceEntity: ExperienceEntity) {
        setUpdateMode()

        viewModel.experience.postValue(experienceEntity)
        openAddExperienceFragment()
    }

    private fun setUpdateMode() {
        viewModel.screenMode = ScreenMode.Update
        viewModel.state.postValue(OperationState.StandBy)
    }

    private fun setAddMode() {
        viewModel.experience.postValue(null)
        viewModel.screenMode = ScreenMode.Add
        viewModel.state.postValue(OperationState.StandBy)
    }
}