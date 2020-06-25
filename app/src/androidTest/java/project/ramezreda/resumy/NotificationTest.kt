package project.ramezreda.resumy

import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.platform.app.InstrumentationRegistry
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import project.ramezreda.resumy.notifications.INotification
import project.ramezreda.resumy.notifications.Notification

@RunWith(AndroidJUnit4::class)
class NotificationTest {
    lateinit var notification: INotification

    @Before
    fun setup() {
        val appContext = InstrumentationRegistry.getInstrumentation().targetContext
        notification = Notification(appContext)
    }

    @Test
    fun successToastShouldBeCalled() {
        // TODO
    }
}