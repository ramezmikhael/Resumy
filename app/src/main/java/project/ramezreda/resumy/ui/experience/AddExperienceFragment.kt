package project.ramezreda.resumy.ui.experience

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentAddExperienceBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.notifications.Notification
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.ui.BaseFragment
import javax.inject.Inject

class AddExperienceFragment : BaseFragment() {

    @Inject
    lateinit var notification: Notification

    private lateinit var dataBinding: FragmentAddExperienceBinding

    companion object {
        fun newInstance() = AddExperienceFragment()
    }

    private val viewModel: AddExperienceViewModel by lazy {
        ViewModelProvider(this).get(AddExperienceViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()
        initDataBinding()
        initObservers()

        return dataBinding.root
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, Observer {
            notification.showInsertToast(it)
            if (it > 0) // success
                findNavController().popBackStack()
        })
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            fillExperienceFields()
            viewModel.save()
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fillExperienceFields() {
        viewModel.experience.value = ExperienceEntity(
            id = 0,
            position = dataBinding.editTextPosition.text.toString(),
            company = dataBinding.editTextCompany.text.toString(),
            location = dataBinding.editTextLocation.text.toString(),
            startDate = dataBinding.editTextStartDate.text.toString(),
            endDate = dataBinding.editTextEndDate.text.toString(),
            description = dataBinding.editTextDescription.text.toString()
        )
    }

    private fun initDataBinding() {
        dataBinding = (binding as FragmentAddExperienceBinding)
        dataBinding.viewModel = viewModel
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

    override fun getLayoutRes(): Int = R.layout.fragment_add_experience

}