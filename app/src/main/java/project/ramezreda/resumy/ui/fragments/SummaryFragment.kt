package project.ramezreda.resumy.ui.fragments

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentSummaryBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.notifications.INotification
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.ui.viewModels.SummaryViewModel
import javax.inject.Inject

class SummaryFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SummaryViewModel

    @Inject
    lateinit var notification: INotification

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    requireContext().applicationContext as Application,
                    requireContext()
                )
            )
            .notificationsModule(NotificationsModule(requireContext()))
            .build()
            .inject(this)

        val summaryBinding = (binding as FragmentSummaryBinding)
        summaryBinding.viewModel = viewModel

        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            text_summary.setText(it?.first()?.summary)
        })

        setHasOptionsMenu(true)
        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        if (item.itemId == R.id.action_save) {
            viewModel.basicInfo?.value?.first()?.summary = text_summary.text.toString()
            val job = GlobalScope.async {
                viewModel.update(viewModel.basicInfo?.value?.first())
            }

            GlobalScope.launch(Dispatchers.Main) {
                val result = job.await()
                notification.showUpdateToast(result = result)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun getLayoutRes(): Int = R.layout.fragment_summary
}