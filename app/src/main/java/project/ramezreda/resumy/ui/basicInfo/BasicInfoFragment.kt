package project.ramezreda.resumy.ui.basicInfo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import project.ramezreda.resumy.R
import project.ramezreda.resumy.base.BaseFragment
import project.ramezreda.resumy.databinding.FragmentBasicInfoBinding

class BasicInfoFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val basicInfoViewModel by lazy { ViewModelProvider(this).get(BasicInfoViewModel::class.java) }

    override fun getLayoutRes(): Int = R.layout.fragment_basic_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        (binding as FragmentBasicInfoBinding).viewModel = basicInfoViewModel

        return binding.root
    }
}