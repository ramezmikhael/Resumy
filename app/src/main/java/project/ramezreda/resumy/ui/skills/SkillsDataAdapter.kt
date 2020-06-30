package project.ramezreda.resumy.ui.skills

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.ramezreda.resumy.databinding.SkillListItemBinding
import project.ramezreda.resumy.roomdb.entities.SkillsEntity
import javax.inject.Inject

class SkillsDataAdapter @Inject constructor() :
    RecyclerView.Adapter<SkillsDataAdapter.SkillsViewHolder>() {

    var skills: List<SkillsEntity?>? = null

    fun setData(skills: List<SkillsEntity?>) {
        this.skills = skills
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

    class SkillsViewHolder(private val skillListItemBinding: SkillListItemBinding) :
        RecyclerView.ViewHolder(skillListItemBinding.root) {

        fun bind(skillsEntity: SkillsEntity) {
            skillListItemBinding.textviewSkill.text = skillsEntity.skill
        }
    }
}