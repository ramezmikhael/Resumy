package project.ramezreda.resumy.ui.basicInfo

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_basic_info.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.ramezreda.resumy.MyApplication
import project.ramezreda.resumy.R
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.databinding.FragmentBasicInfoBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import javax.inject.Inject

class BasicInfoFragment : BaseFragment() {

    @Inject lateinit var viewModel : BasicInfoViewModel

    override fun getLayoutRes(): Int = R.layout.fragment_basic_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        MyApplication.component.inject(this)

        (binding as FragmentBasicInfoBinding).viewModel = viewModel

        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            binding.root.editTextName.setText(it?.fullName)
            binding.root.editTextEmail.setText(it?.email)
            binding.root.editTextPhone.setText(it?.phone)
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
            GlobalScope.launch {
                viewModel.update(viewModel.basicInfo?.value)
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun fetchBasicInfo() {
        viewModel.basicInfo?.value?.fullName = binding.root.editTextName.text.toString()
        viewModel.basicInfo?.value?.email = binding.root.editTextEmail.text.toString()
        viewModel.basicInfo?.value?.phone = binding.root.editTextPhone.text.toString()
    }
}