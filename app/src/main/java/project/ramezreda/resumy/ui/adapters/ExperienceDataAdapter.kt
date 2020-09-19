package project.ramezreda.resumy.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import project.ramezreda.resumy.R
import project.ramezreda.resumy.databinding.ExperienceListItemBinding
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.utils.MonthsConverter
import javax.inject.Inject

class ExperienceDataAdapter @Inject constructor() :
    RecyclerView.Adapter<ExperienceDataAdapter.ExperienceViewHolder>() {

    @Inject
    lateinit var monthsConverter: MonthsConverter

    var experiences: List<ExperienceEntity?>? = null

    var clickListener: ExperienceClickListener? = null

    fun setData(experiences: List<ExperienceEntity?>) {
        this.experiences = experiences
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
            val context = itemBinding.root.context

            itemBinding.textViewPosition.text = entity.position
            itemBinding.textViewCompany.text = entity.company
            itemBinding.textViewLocation.text = entity.location
            itemBinding.textViewDescription.text = entity.description

            val endDate: String? =
                if (entity.endYear == null) "Present" else "${
                    entity.endMonth?.let { monthsConverter.monthNumberToShortName(it) }
                } ${entity.endYear}"

            itemBinding.textViewStartDate.text =
                "${entity.startMonth?.let { monthsConverter.monthNumberToShortName(it) }} ${entity.startYear} ${
                    context.getString(R.string.until)
                } $endDate"

            itemBinding.root.setOnClickListener {
                clickListener?.onExperienceSelected(entity)
            }
        }
    }
}

interface ExperienceClickListener {
    fun onExperienceSelected(experienceEntity: ExperienceEntity)
}