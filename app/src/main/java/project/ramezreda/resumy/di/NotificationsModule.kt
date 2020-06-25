package project.ramezreda.resumy.di

import android.content.Context
import dagger.Module
import dagger.Provides
import project.ramezreda.resumy.notifications.INotification
import project.ramezreda.resumy.notifications.Notification

@Module
class NotificationsModule(val context: Context) {
    @Provides
    fun providesNotification() : INotification = Notification(context)
}