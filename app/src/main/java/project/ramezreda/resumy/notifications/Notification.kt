package project.ramezreda.resumy.notifications

import android.content.Context
import javax.inject.Inject

class Notification @Inject constructor(context: Context) {
    fun showToast(notification: IUINotification) {
        notification.showToastMessage()
    }
}