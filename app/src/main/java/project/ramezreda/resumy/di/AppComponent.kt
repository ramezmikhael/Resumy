package project.ramezreda.resumy.di

import android.app.Application
import dagger.Component
import project.ramezreda.resumy.ui.basicInfo.BasicInfoFragment
import project.ramezreda.resumy.ui.education.EducationFragment
import project.ramezreda.resumy.ui.summary.SummaryFragment
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class])
@Singleton
interface AppComponent {

    fun app() : Application

    fun inject(basicInfoFragment: BasicInfoFragment)
    fun inject(educationFragment: EducationFragment)
    fun inject(summaryFragment: SummaryFragment)

}