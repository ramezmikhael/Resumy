package project.ramezreda.resumy.ui.experience

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.core.view.isVisible
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentAddExperienceBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.notifications.Notification
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.utils.OperationState
import project.ramezreda.resumy.utils.ScreenMode
import javax.inject.Inject
import kotlin.math.exp

class AddExperienceFragment : BaseFragment() {

    override fun getLayoutRes(): Int = R.layout.fragment_add_experience

    @Inject
    lateinit var notification: Notification

    private lateinit var dataBinding: FragmentAddExperienceBinding

    companion object {
        fun newInstance() = AddExperienceFragment()
    }

    private val viewModel: ExperienceViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()
        initDataBinding()
        initUIActions()
        initObservers()

        return dataBinding.root
    }

    private fun initUIActions() {
        dataBinding.checkBoxNoEndDate.setOnCheckedChangeListener { _, isChecked ->
            dataBinding.editTextEndMonth.isVisible = !isChecked
            dataBinding.editTextEndYear.isVisible = !isChecked
        }
    }

    private fun initObservers() {
        viewModel.state.observe(viewLifecycleOwner, {
            if (it == OperationState.Failed) {
                notification.showInsertToast(-1)
            } else if (it == OperationState.Success) {
                notification.showInsertToast(1)
                findNavController().popBackStack()
            }
        })

        viewModel.experience.observe(viewLifecycleOwner, {
            if (viewModel.screenMode == ScreenMode.Update) {
                fillExperienceUI(it)
            }
        })
    }

    private fun fillExperienceUI(experience: ExperienceEntity?) {
        dataBinding.editTextPosition.setText(experience?.position)
        dataBinding.editTextCompany.setText(experience?.company)
        dataBinding.editTextLocation.setText(experience?.location)
        dataBinding.editTextDescription.setText(experience?.description)
        dataBinding.editTextStartMonth.setText(experience?.startMonth.toString())
        dataBinding.editTextStartYear.setText(experience?.startYear.toString())

        val currentPosition = experience?.endYear == null
        dataBinding.checkBoxNoEndDate.isChecked = currentPosition
        dataBinding.editTextEndMonth.isVisible = currentPosition
        dataBinding.editTextEndYear.isVisible = currentPosition
        dataBinding.editTextEndMonth.setText(experience?.endMonth.toString())
        dataBinding.editTextEndYear.setText(experience?.endYear.toString())

        viewModel.screenMode = ScreenMode.Update
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
        val currentPosition = dataBinding.checkBoxNoEndDate.isChecked
        val endMonth: Int? =
            if (currentPosition) null else dataBinding.editTextEndMonth.text.toString().toInt()
        val endYear: Int? =
            if (currentPosition) null else dataBinding.editTextEndYear.text.toString().toInt()

        val experience = ExperienceEntity(
            id = viewModel.experience.value?.id ?: 0,
            position = dataBinding.editTextPosition.text.toString(),
            company = dataBinding.editTextCompany.text.toString(),
            location = dataBinding.editTextLocation.text.toString(),
            startMonth = dataBinding.editTextStartMonth.text.toString().toInt(),
            startYear = dataBinding.editTextStartYear.text.toString().toInt(),
            endMonth = endMonth,
            endYear = endYear,
            description = dataBinding.editTextDescription.text.toString()
        )

        viewModel.experience.value = experience
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
}