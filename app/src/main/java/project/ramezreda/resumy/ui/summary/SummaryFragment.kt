package project.ramezreda.resumy.ui.summary

import android.os.Bundle
import android.view.*
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_summary.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.base.BaseFragment
import project.ramezreda.resumy.databinding.FragmentSummaryBinding

class SummaryFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val viewModel: SummaryViewModel by lazy {
        ViewModelProvider(this).get(SummaryViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

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
        if(item.itemId == R.id.action_save) {
            viewModel.basicInfo?.value?.summary = text_summary.text.toString()
            GlobalScope.launch {
                viewModel.update(viewModel.basicInfo?.value)
            }
        }
        return super.onOptionsItemSelected(item)

    }

    override fun getLayoutRes(): Int = R.layout.fragment_summary
}