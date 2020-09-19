package project.ramezreda.resumy.di

import android.app.Application
import android.content.Context
import dagger.Component
import project.ramezreda.resumy.notifications.Notification
import project.ramezreda.resumy.ui.MainActivity
import project.ramezreda.resumy.ui.fragments.BasicInfoFragment
import project.ramezreda.resumy.ui.viewModels.BasicInfoViewModel
import project.ramezreda.resumy.ui.fragments.EducationFragment
import project.ramezreda.resumy.ui.fragments.AddExperienceFragment
import project.ramezreda.resumy.ui.fragments.ExperienceFragment
import project.ramezreda.resumy.ui.viewModels.ExperienceViewModel
import project.ramezreda.resumy.ui.fragments.HomeFragment
import project.ramezreda.resumy.ui.fragments.SkillsFragment
import project.ramezreda.resumy.ui.viewModels.SkillsViewModel
import project.ramezreda.resumy.ui.fragments.SummaryFragment
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class, NotificationsModule::class])
@Singleton
interface AppComponent {

    fun app() : Application
    fun context() : Context

    fun inject(viewModel: BasicInfoViewModel)
    fun inject(viewModel: SkillsViewModel)
    fun inject(experienceViewModel: ExperienceViewModel)

    fun inject(homeFragment: HomeFragment)
    fun inject(basicInfoFragment: BasicInfoFragment)
    fun inject(educationFragment: EducationFragment)
    fun inject(summaryFragment: SummaryFragment)
    fun inject(skillsFragment: SkillsFragment)
    fun inject(addExperienceFragment: AddExperienceFragment)
    fun inject(experienceFragment: ExperienceFragment)

    fun inject(mainActivity: MainActivity)
    fun inject(notification: Notification)
}