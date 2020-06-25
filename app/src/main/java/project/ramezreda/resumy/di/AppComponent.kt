package project.ramezreda.resumy.di

import android.app.Application
import android.content.Context
import dagger.Component
import project.ramezreda.resumy.ui.basicInfo.BasicInfoFragment
import project.ramezreda.resumy.ui.basicInfo.BasicInfoViewModel
import project.ramezreda.resumy.ui.education.EducationFragment
import project.ramezreda.resumy.ui.summary.SummaryFragment
import javax.inject.Singleton

@Component(modules = [ApplicationContextModule::class])
@Singleton
interface AppComponent {

    fun app() : Application
    fun context() : Context

    fun inject(viewModel: BasicInfoViewModel)

    fun inject(basicInfoFragment: BasicInfoFragment)
    fun inject(educationFragment: EducationFragment)
    fun inject(summaryFragment: SummaryFragment)

}