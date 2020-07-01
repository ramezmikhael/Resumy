package project.ramezreda.resumy.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.annotation.LayoutRes
import androidx.databinding.DataBindingUtil
import androidx.databinding.ViewDataBinding
import androidx.fragment.app.Fragment
import project.ramezreda.resumy.utils.ScreenMode

abstract class BaseFragment: Fragment() {
    lateinit var binding: ViewDataBinding

    @LayoutRes
    abstract fun getLayoutRes(): Int

    protected var screenMode: ScreenMode = ScreenMode.Normal

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = DataBindingUtil.inflate(
            inflater,
            getLayoutRes(),
            container,
            false
        )

        binding.lifecycleOwner = viewLifecycleOwner
        setHasOptionsMenu(true)
        return binding.root
    }

}