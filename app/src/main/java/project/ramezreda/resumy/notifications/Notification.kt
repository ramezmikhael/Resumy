package project.ramezreda.resumy.notifications

import android.content.Context
import javax.inject.Inject

class Notification @Inject constructor(val context: Context) : INotification {

    val notification = ToastNotification(context)

    override fun showUpdateToast(result: Int?) {
        showToast(result?.toLong())
    }

    override fun showInsertToast(result: Long?) {
        showToast(result)
    }

    private fun showToast(result: Long?) {
        if(result?.compareTo(0)!! > 0) { // success
            notification.showSuccess()
        } else {    // failed
            notification.showFailed()
        }
    }
}