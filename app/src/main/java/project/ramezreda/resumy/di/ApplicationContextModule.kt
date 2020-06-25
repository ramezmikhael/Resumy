package project.ramezreda.resumy.di

import android.app.Application
import android.content.Context
import dagger.Module
import dagger.Provides

@Module
class ApplicationContextModule(val application: Application, val context: Context) {

    @Provides
    fun providesApplicationContext() : Application = application

    @Provides
    fun providesContext() : Context = context
}