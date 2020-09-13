package project.ramezreda.resumy.ui.skills

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.skill_list_item.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.SkillListItemBinding
import project.ramezreda.resumy.roomdb.entities.SkillsEntity
import project.ramezreda.resumy.utils.IItemsSelected
import javax.inject.Inject

class SkillsDataAdapter @Inject constructor() :
    RecyclerView.Adapter<SkillsDataAdapter.SkillsViewHolder>() {

    var clickableItems: Boolean = false
    var itemsSelected: IItemsSelected? = null
    var skills: List<SkillsEntity?>? = null
    val selectedSkills : MutableList<SkillsEntity> = mutableListOf()

    fun setData(skills: List<SkillsEntity?>) {
        this.skills = skills
    }

    fun setItemsSelectedListener(itemsSelected: IItemsSelected) {
        this.itemsSelected = itemsSelected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SkillsViewHolder {
        val skillListItemBinding = SkillListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return SkillsViewHolder(skillListItemBinding)
    }

    override fun getItemCount(): Int {
        skills?.let {
            return it.count()
        }
        return 0
    }

    override fun onBindViewHolder(holder: SkillsViewHolder, position: Int) {
        skills?.get(position)?.let { holder.bind(it) }
    }

    inner class SkillsViewHolder(private val skillListItemBinding: SkillListItemBinding) :
        RecyclerView.ViewHolder(skillListItemBinding.root) {

        fun bind(skillsEntity: SkillsEntity) {
            skillListItemBinding.textviewSkill.text = skillsEntity.skill
            if(clickableItems) {
                skillListItemBinding.root.setOnClickListener {

                    if(selectedSkills.contains(skillsEntity)) {
                        unselectSkill(it, skillsEntity)
                    } else {
                        selectSkill(it, skillsEntity)
                    }

                    itemsSelected?.onItemsSelected(selectedSkills)
                }
            }
        }

        private fun unselectSkill(
            it: View?,
            skillsEntity: SkillsEntity
        ) {
            selectedSkills.remove(skillsEntity)
            it?.textview_skill?.background = it?.context?.resources?.getDrawable(R.drawable.skill_item_background)
            it?.textview_skill?.setTextColor(ContextCompat.getColor(it.context, R.color.skillTextColor))
        }

        private fun selectSkill(
            it: View?,
            skillsEntity: SkillsEntity
        ) {
            selectedSkills.add(skillsEntity)
            it?.textview_skill?.background = it?.context?.resources?.getDrawable(R.drawable.skill_item_selected_background)
            it?.textview_skill?.setTextColor(ContextCompat.getColor(it.context, android.R.color.white))
        }

    }
}