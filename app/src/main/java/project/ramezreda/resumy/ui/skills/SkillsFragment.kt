package project.ramezreda.resumy.ui.skills

import android.app.Application
import android.os.Bundle
import android.view.*
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.FragmentSkillsBinding
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import project.ramezreda.resumy.notifications.INotification
import project.ramezreda.resumy.roomdb.entities.SkillsEntity
import project.ramezreda.resumy.ui.BaseFragment
import project.ramezreda.resumy.utils.IItemsSelected
import project.ramezreda.resumy.utils.ScreenMode
import javax.inject.Inject

class SkillsFragment : BaseFragment(), IItemsSelected {

    @Inject
    lateinit var skillsViewModel: SkillsViewModel

    @Inject
    lateinit var notification: INotification

    @Inject
    lateinit var skillsDataAdapter: SkillsDataAdapter

    private lateinit var skillsBinding: FragmentSkillsBinding
    private var selectedSkills : MutableList<SkillsEntity> = mutableListOf()

    override fun getLayoutRes(): Int = R.layout.fragment_skills

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        super.onCreateView(inflater, container, savedInstanceState)

        initDagger()
        initDataBinding()
        initSkillsRecyclerView()

        skillsBinding.buttonAddSkill.setOnClickListener {
            insertSkill()
        }

        skillsViewModel.skills.observe(viewLifecycleOwner, Observer {
            skillsDataAdapter.setData(it)
            skillsBinding.recyclerViewSkills.adapter?.notifyDataSetChanged()
        })

        return skillsBinding.root
    }

    private fun initDagger() {
        DaggerAppComponent.builder()
            .applicationContextModule(
                ApplicationContextModule(
                    requireContext().applicationContext as Application,
                    requireContext()
                )
            )
            .notificationsModule(NotificationsModule(requireContext()))
            .build().inject(this)
    }

    private fun initDataBinding() {
        skillsBinding = (binding as FragmentSkillsBinding)
        skillsBinding.viewModel = skillsViewModel
        skillsBinding.lifecycleOwner = viewLifecycleOwner
    }

    private fun initSkillsRecyclerView() {
        val layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)

        skillsDataAdapter.itemsSelected = this
        skillsBinding.recyclerViewSkills.adapter = skillsDataAdapter
        skillsBinding.recyclerViewSkills.layoutManager = layoutManager
    }

    private fun insertSkill() {
        val job = GlobalScope.async {
            skillsViewModel.insert(SkillsEntity(skill = skillsBinding.editTextSkill.text.toString()))
        }

        GlobalScope.launch(Dispatchers.Main) {
            val id = job.await()
            notification.showInsertToast(id)
            skillsBinding.editTextSkill.text.clear()
        }
    }

    private fun deleteSkills() {
        val job = GlobalScope.async {
            skillsViewModel.deleteMany(selectedSkills.map { it.id })
        }

        GlobalScope.launch(Dispatchers.Main) {
            val result = job.await()
            notification.showUpdateToast(result)
        }
    }

    override fun onItemsSelected(skills: MutableList<SkillsEntity>) {
        selectedSkills = skills

        screenMode = if(skills.count() > 0)
            ScreenMode.Edit
        else
            ScreenMode.Normal

        activity?.invalidateOptionsMenu()
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu_edit_mode, menu)
    }

    override fun onPrepareOptionsMenu(menu: Menu) {
        super.onPrepareOptionsMenu(menu)

        menu.findItem(R.id.action_delete).isVisible = screenMode != ScreenMode.Normal
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if(item.itemId == R.id.action_delete) {
            deleteSkills()
        }
        return super.onOptionsItemSelected(item)

    }

}