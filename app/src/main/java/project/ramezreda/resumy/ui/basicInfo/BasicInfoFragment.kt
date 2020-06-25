package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_basic_info.view.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentBasicInfoBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.notifications.FailNotification
import project.ramezreda.resumy.notifications.Notification
import project.ramezreda.resumy.notifications.SuccessNotification
import project.ramezreda.resumy.ui.BaseFragment
import javax.inject.Inject

class BasicInfoFragment : BaseFragment() {

    @Inject lateinit var viewModel : BasicInfoViewModel
    @Inject lateinit var successNotification: SuccessNotification
    @Inject lateinit var failNotification: FailNotification
    @Inject lateinit var notification: Notification

    override fun getLayoutRes(): Int = R.layout.fragment_basic_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(requireContext().applicationContext as Application,
            requireContext()))
            .build()
            .inject(this)

        (binding as FragmentBasicInfoBinding).viewModel = viewModel

        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            binding.root.editTextName.setText(it?.first()?.fullName)
            binding.root.editTextEmail.setText(it?.first()?.email)
            binding.root.editTextPhone.setText(it?.first()?.phone)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            fetchBasicInfo()
            val job = GlobalScope.async {
                viewModel.update(viewModel.basicInfo?.value?.first())
            }

            GlobalScope.launch(Dispatchers.Main) {
                val res = job.await()
                if(res?.compareTo(0)!! > 0) {
                    notification.showToast(successNotification)
                } else {
                    notification.showToast(failNotification)
                }
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchBasicInfo() {
        val entity = viewModel.basicInfo?.value?.first()
        entity?.fullName = binding.root.editTextName.text.toString()
        entity?.email = binding.root.editTextEmail.text.toString()
        entity?.phone = binding.root.editTextPhone.text.toString()
    }
}