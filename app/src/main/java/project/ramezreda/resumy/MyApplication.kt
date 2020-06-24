package project.ramezreda.resumy

import android.app.Application
import project.ramezreda.resumy.di.AppComponent
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent

object MyApplication : Application() {

        val component: AppComponent = DaggerAppComponent.builder()
            .applicationContextModule(ApplicationContextModule(this))
            .build()
}