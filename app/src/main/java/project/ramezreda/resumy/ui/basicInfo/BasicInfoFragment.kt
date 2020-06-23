package project.ramezreda.resumy.ui.basicInfo

import android.os.Bundle
import android.view.*
import android.widget.Toast
import androidx.databinding.ViewDataBinding
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import kotlinx.android.synthetic.main.fragment_basic_info.view.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.base.BaseFragment
import project.ramezreda.resumy.databinding.FragmentBasicInfoBinding
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity

class BasicInfoFragment<T : ViewDataBinding> : BaseFragment<T>() {

    private val viewModel by lazy { ViewModelProvider(this).get(BasicInfoViewModel::class.java) }

    override fun getLayoutRes(): Int = R.layout.fragment_basic_info

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        (binding as FragmentBasicInfoBinding).viewModel = viewModel

        setHasOptionsMenu(true)

        viewModel.basicInfo?.observe(viewLifecycleOwner, Observer {
            binding.root.editTextName.setText(it?.fullName)
            binding.root.editTextEmail.setText(it?.email)
            binding.root.editTextPhone.setText(it?.phone)
        })

        return binding.root
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        menu.clear()
        inflater.inflate(R.menu.menu_save, menu)
        super.onCreateOptionsMenu(menu, inflater)
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