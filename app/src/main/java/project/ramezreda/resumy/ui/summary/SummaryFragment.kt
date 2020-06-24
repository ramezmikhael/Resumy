package project.ramezreda.resumy.ui.summary

import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.ramezreda.resumy.MyApplication
import project.ramezreda.resumy.R
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.databinding.FragmentSummaryBinding
import javax.inject.Inject

class SummaryFragment : BaseFragment() {

    @Inject
    lateinit var viewModel: SummaryViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        MyApplication.component.inject(this)

        val summaryBinding = (binding as FragmentSummaryBinding)
        summaryBinding.viewModel = viewModel

        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            text_summary.setText(it?.summary)
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
            viewModel.basicInfo?.value?.summary = text_summary.text.toString()
            GlobalScope.launch {
                viewModel.update(viewModel.basicInfo?.value)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun getLayoutRes(): Int = R.layout.fragment_summary
}