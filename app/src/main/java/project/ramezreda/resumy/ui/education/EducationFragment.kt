package project.ramezreda.resumy.ui.education

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.snackbar.Snackbar
import kotlinx.android.synthetic.main.fab_layout.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.base.BaseFragment
import project.ramezreda.resumy.databinding.FragmentEducationBinding

class EducationFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val viewModel: EducationViewModel by lazy {
        ViewModelProvider(this).get(EducationViewModel::class.java)
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        val bindingEducation = (binding as FragmentEducationBinding)
        bindingEducation.viewModel = viewModel

        bindingEducation.fabLayout.fab.setOnClickListener {
            // TODO
        }
        return binding.root
    }

    override fun getLayoutRes(): Int = R.layout.fragment_education

}