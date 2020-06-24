package project.ramezreda.resumy.di

import android.app.Application
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(val application: Application) {

    @Provides
    fun providesApplicationContext() : Application = application
}