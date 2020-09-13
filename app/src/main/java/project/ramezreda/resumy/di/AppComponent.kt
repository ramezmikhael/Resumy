package project.ramezreda.resumy.di

import android.app.Application
import android.content.Context
import dagger.Component
import project.ramezreda.resumy.notifications.Notification
import project.ramezreda.resumy.ui.MainActivity
import project.ramezreda.resumy.ui.basicInfo.BasicInfoFragment
import project.ramezreda.resumy.ui.basicInfo.BasicInfoViewModel
import project.ramezreda.resumy.ui.education.EducationFragment
import project.ramezreda.resumy.ui.home.HomeFragment
import project.ramezreda.resumy.ui.skills.SkillsFragment
import project.ramezreda.resumy.ui.skills.SkillsViewModel
import project.ramezreda.resumy.ui.summary.SummaryFragment
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class, NotificationsModule::class])
@Singleton
interface AppComponent {

    fun app() : Application
    fun context() : Context

    fun inject(viewModel: BasicInfoViewModel)
    fun inject(viewModel: SkillsViewModel)

    fun inject(homeFragment: HomeFragment)
    fun inject(basicInfoFragment: BasicInfoFragment)
    fun inject(educationFragment: EducationFragment)
    fun inject(summaryFragment: SummaryFragment)
    fun inject(skillsFragment: SkillsFragment)

    fun inject(mainActivity: MainActivity)

    fun inject(notification: Notification)

}