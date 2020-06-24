package project.ramezreda.resumy.ui.home

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_home.*
import kotlinx.android.synthetic.main.home_basic_info.view.*
import kotlinx.android.synthetic.main.home_summary.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.databinding.FragmentHomeBinding

class HomeFragment : BaseFragment() {

    private val homeViewModel by lazy { ViewModelProvider(this).get(HomeViewModel::class.java) }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val homeBinding = (binding as FragmentHomeBinding)
        homeBinding.viewModel = homeViewModel

        homeViewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            segment_basic_info.textViewName.text = it?.fullName
            segment_basic_info.textViewPhone.text = it?.phone
            segment_basic_info.textViewEmail.text = it?.email
            segment_summary?.textViewSummary?.text = it?.summary
        })

        return binding.root
    }

    override fun getLayoutRes(): Int = R.layout.fragment_home
}