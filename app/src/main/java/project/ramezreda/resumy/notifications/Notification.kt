package project.ramezreda.resumy.notifications

import android.app.Application
import android.content.Context
import project.ramezreda.resumy.di.ApplicationContextModule
import project.ramezreda.resumy.di.DaggerAppComponent
import project.ramezreda.resumy.di.NotificationsModule
import javax.inject.Inject

class Notification @Inject constructor(val context: Context) : INotification {

    override fun showUpdateToast(result: Int?) {
        val notification = ToastNotification(context)

        if(result?.compareTo(0)!! > 0) { // success
            notification.showSuccess()
        } else {    // failed
            notification.showFailed()
        }
    }
}