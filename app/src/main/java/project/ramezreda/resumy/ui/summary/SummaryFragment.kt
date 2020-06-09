package project.ramezreda.resumy.ui.summary

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_summary.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.base.BaseFragment
import project.ramezreda.resumy.databinding.FragmentSummaryBinding

class SummaryFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val summaryViewModel: SummaryViewModel by lazy {
        ViewModelProvider(this).get(SummaryViewModel::class.java)
    }

    override fun onCreateView(
            inflater: LayoutInflater,
            container: ViewGroup?,
            savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val summaryBinding = (binding as FragmentSummaryBinding)
        summaryBinding.viewModel = summaryViewModel

        summaryViewModel.text.observe(viewLifecycleOwner, Observer {
            text_summary.text = it
        })

        return binding.root
    }

    override fun getLayoutRes(): Int = R.layout.fragment_summary
}