package project.ramezreda.resumy.notifications

import android.content.Context
import android.widget.Toast
import project.ramezreda.resumy.R
import javax.inject.Inject

class SuccessNotification @Inject constructor(val context: Context) : IUINotification {
    override fun showToastMessage() {
        Toast.makeText(context, context.getString(R.string.success), Toast.LENGTH_LONG).show()
    }
}