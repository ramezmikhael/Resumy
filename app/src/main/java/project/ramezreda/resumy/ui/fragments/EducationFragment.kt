package project.ramezreda.resumy.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.layout_fab.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentEducationBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.ui.viewModels.EducationViewModel
import javax.inject.Inject

class EducationFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: EducationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        DaggerAppComponent
            .builder()
            .applicationContextModule(
                ApplicationContextModule(
                    requireContext().applicationContext!! as Application,
                    requireContext()
                )
            )
            .notificationsModule(NotificationsModule(requireContext()))
            .build()
            .inject(this)

        val bindingEducation = (binding as FragmentEducationBinding)
        bindingEducation.viewModel = viewModel

        bindingEducation.fabLayout.fab.setOnClickListener {
            // TODO
        }
        return binding.root
    }

    override fun getLayoutRes(): Int = R.layout.fragment_education

}