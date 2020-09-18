package project.ramezreda.resumy.utils

import android.content.Context
import project.ramezreda.resumy.R
import javax.inject.Inject

class MonthsConverter @Inject constructor(val context: Context) {

    fun monthNumberToShortName(monthNumber: Int): String? {
        val months = context.resources.getStringArray(R.array.month_short_names)
        return convert(monthNumber, months)
    }

    fun monthNumberToFullName(monthNumber: Int): String? {
        val months = context.resources.getStringArray(R.array.month_full_names)
        return convert(monthNumber, months)
    }

    private fun convert(monthNumber: Int, namesArray: Array<String>) : String? {
        if(monthNumber in 1..12) {
            return namesArray[monthNumber - 1]
        }
        return null
    }
}