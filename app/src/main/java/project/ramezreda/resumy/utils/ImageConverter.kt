package project.ramezreda.resumy.utils

import android.R.attr.maxHeight
import android.R.attr.maxWidth
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.util.Log
import java.io.ByteArrayOutputStream


object ImageConverter {

    private const val fixedSize = 640.0

    fun bitmapToByteArray(bitmap: Bitmap): ByteArray {
        val resizedBitmap = resizeBitmap(bitmap)

        val stream = ByteArrayOutputStream()
        resizedBitmap.compress(Bitmap.CompressFormat.JPEG, 100, stream)
        return stream.toByteArray()
    }

    fun byteArrayToBitmap(data: ByteArray?): Bitmap? {
        data?.size?.let {
            return BitmapFactory.decodeByteArray(
                data, 0, data.size
            )
        }

        return null
    }

    private fun resizeBitmap(bitmap: Bitmap): Bitmap {
        Log.d("Original W&H", "Width ${bitmap.width} - Height ${bitmap.height}")

        var width = 0.0
        var height = 0.0

        if (bitmap.width > bitmap.height) {
            height = fixedSize
            width = (fixedSize * 100 / bitmap.height) * bitmap.width / 100
        } else {
            width = fixedSize
            height = (fixedSize * 100 / bitmap.width) * bitmap.height / 100
        }
        Log.d("W&H", "Width $width - Height $height")
        return Bitmap.createScaledBitmap(bitmap, width.toInt(), height.toInt(), false)
    }

}