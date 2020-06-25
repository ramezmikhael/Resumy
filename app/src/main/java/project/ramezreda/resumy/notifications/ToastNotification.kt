package project.ramezreda.resumy.notifications

import android.content.Context
import android.widget.Toast
import project.ramezreda.resumy.R

class ToastNotification (val context: Context) {

    fun showSuccess() {
        Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG).show()
    }

    fun showFailed() {
        Toast.makeText(context, context.getString(R.string.fail), Toast.LENGTH_LONG).show()
    }
}