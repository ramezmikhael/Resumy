package project.ramezreda.resumy.ui.home

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import project.ramezreda.resumy.repository.BasicInfoRepository
import project.ramezreda.resumy.repository.ExperienceRepository
import project.ramezreda.resumy.repository.SkillsRepository
import project.ramezreda.resumy.roomdb.entities.BasicInfoEntity
import project.ramezreda.resumy.roomdb.entities.ExperienceEntity
import project.ramezreda.resumy.roomdb.entities.SkillsEntity

class HomeViewModel(application: Application) : AndroidViewModel(application) {

    private var basicInfoRepository: BasicInfoRepository? = null
    private var skillsRepository: SkillsRepository? = null
    private var experienceRepository: ExperienceRepository? = null

    var basicInfo: LiveData<List<BasicInfoEntity?>>? = null
    var skills: LiveData<List<SkillsEntity?>>? = null
    var experience: LiveData<List<ExperienceEntity?>>? = null

    init {
        basicInfoRepository = BasicInfoRepository(application)
        skillsRepository = SkillsRepository(application)
        experienceRepository = ExperienceRepository(application)

        basicInfo = basicInfoRepository?.getAll()
        skills = skillsRepository?.getAll()
        experience = experienceRepository?.getAll()
    }
}