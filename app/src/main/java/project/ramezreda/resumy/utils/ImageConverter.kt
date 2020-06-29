package project.ramezreda.resumy.utils

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import java.io.ByteArrayOutputStream

object ImageConverter {
    fun bitmapToByteArray(bitmap: Bitmap) : ByteArray {
        val stream = ByteArrayOutputStream()
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(data: ByteArray?) : Bitmap? {
        data?.size?.let {
            return BitmapFactory.decodeByteArray(
                data, 0, data.size
            )
        }

        return null
    }
}