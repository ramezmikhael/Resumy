package project.ramezreda.resumy.ui.experience

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.skill_list_item.view.*
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.ExperienceListItemBinding
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.utils.IItemsSelected
import javax.inject.Inject

class ExperienceDataAdapter @Inject constructor() :
    RecyclerView.Adapter<ExperienceDataAdapter.ExperienceViewHolder>() {

    var clickableItems: Boolean = false
    var itemsSelected: IItemsSelected? = null
    var experiences: List<ExperienceEntity?>? = null
    val selectedExperiences : MutableList<ExperienceEntity> = mutableListOf()

    fun setData(experiences: List<ExperienceEntity?>) {
        this.experiences = experiences
    }

    fun setItemsSelectedListener(itemsSelected: IItemsSelected) {
        this.itemsSelected = itemsSelected
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ExperienceViewHolder {
        val experienceListItemBinding = ExperienceListItemBinding.inflate(
            LayoutInflater.from(parent.context), parent, false
        )
        return ExperienceViewHolder(experienceListItemBinding)
    }

    override fun getItemCount(): Int {
        experiences?.let {
            return it.count()
        }
        return 0
    }

    override fun onBindViewHolder(holder: ExperienceViewHolder, position: Int) {
        experiences?.get(position)?.let { holder.bind(it) }
    }

    inner class ExperienceViewHolder(private val itemBinding: ExperienceListItemBinding) :
        RecyclerView.ViewHolder(itemBinding.root) {

        fun bind(entity: ExperienceEntity) {
            itemBinding.textViewPosition.text = entity.position
            itemBinding.textViewCompany.text = entity.company
            itemBinding.textViewLocation.text = entity.location
            itemBinding.textViewDescription.text = entity.description

            val endDate : String = entity.endDate ?: itemBinding.root.context.getString(R.string.present)
            itemBinding.textViewStartDate.text = "${entity.startDate} - $endDate"

            if(clickableItems) {
                itemBinding.root.setOnClickListener {

                    if(selectedExperiences.contains(entity)) {
                        unselectSkill(it, entity)
                    } else {
                        selectSkill(it, entity)
                    }

//                    itemsSelected?.onItemsSelected(selectedExperiences)
                }
            }
        }

        private fun unselectSkill(
            it: View?,
            experienceEntity: ExperienceEntity
        ) {
            selectedExperiences.remove(experienceEntity)
            it?.textview_skill?.background = it?.context?.resources?.getDrawable(R.drawable.skill_item_background)
            it?.textview_skill?.setTextColor(ContextCompat.getColor(it.context, R.color.skillTextColor))
        }

        private fun selectSkill(
            it: View?,
            experienceEntity: ExperienceEntity
        ) {
            selectedExperiences.add(experienceEntity)
            it?.textview_skill?.background = it?.context?.resources?.getDrawable(R.drawable.skill_item_selected_background)
            it?.textview_skill?.setTextColor(ContextCompat.getColor(it.context, android.R.color.white))
        }

    }
}