package project.ramezreda.resumy.ui.home

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_basic_info.view.*
import kotlinx.android.synthetic.main.home_skills.view.*
import kotlinx.android.synthetic.main.home_summary.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentHomeBinding
import project.ramezreda.resumy.databinding.FragmentSkillsBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.ui.skills.SkillsDataAdapter
import project.ramezreda.resumy.utils.ImageConverter
import javax.inject.Inject

class HomeFragment : BaseFragment() {

    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    @Inject
    lateinit var skillsDataAdapter: SkillsDataAdapter

    lateinit var homeBinding: FragmentHomeBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()
        initDataBinding()
        initSkillsRecyclerView()
        initObservers()

        return binding.root
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

    private fun initDataBinding() {
        homeBinding = (binding as FragmentHomeBinding)
        homeBinding.viewModel = homeViewModel
        homeBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initObservers() {
        homeViewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            if(it.isEmpty()) {
                return@Observer
            }
            segment_basic_info.textViewName.text = it?.first()?.fullName
            segment_basic_info.textViewPhone.text = it?.first()?.phone
            segment_basic_info.textViewEmail.text = it?.first()?.email
            segment_summary?.textViewSummary?.text = it?.first()?.summary

            val image = ImageConverter.byteArrayToBitmap(it?.first()?.picture)
            image?.let { bitmap ->  segment_basic_info?.imageViewPhoto?.setImageBitmap(bitmap) }
        })

        homeViewModel.skills?.observe(viewLifecycleOwner, Observer {
            skillsDataAdapter.setData(it)
            skillsDataAdapter.notifyDataSetChanged()
        })
    }

    fun initSkillsRecyclerView() {
        skillsDataAdapter.clickableItems = false
        homeBinding.segmentSkills.recycler_view_skills.adapter = skillsDataAdapter
        homeBinding.segmentSkills.recycler_view_skills.layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
    }
    override fun getLayoutRes(): Int = R.layout.fragment_home
}