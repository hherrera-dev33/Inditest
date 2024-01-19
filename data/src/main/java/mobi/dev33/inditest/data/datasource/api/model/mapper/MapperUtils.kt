package mobi.dev33.inditest.data.datasource.api.model.mapper

import android.annotation.SuppressLint
import java.text.SimpleDateFormat
import java.util.Locale

object MapperUtils {

    @SuppressLint("ConstantLocale")
    private val apiDateFormat =
        SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss.SSS'Z'", Locale.getDefault())

    fun apiDateToTs(dateString: String?) =
        dateString?.let { apiDateFormat.parse(dateString)?.time } ?: 0L

}