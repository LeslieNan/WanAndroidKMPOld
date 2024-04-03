package core_base.util

import kotlinx.datetime.Clock
import kotlinx.datetime.LocalDateTime
import kotlinx.datetime.TimeZone
import kotlinx.datetime.format
import kotlinx.datetime.format.DateTimeComponents
import kotlinx.datetime.format.DateTimeFormat
import kotlinx.datetime.format.FormatStringsInDatetimeFormats
import kotlinx.datetime.format.byUnicodePattern
import kotlinx.datetime.format.format
import kotlinx.datetime.toLocalDateTime


object TimeUtil {

    @OptIn(FormatStringsInDatetimeFormats::class)
    val publishSdf = LocalDateTime.Format {
        byUnicodePattern("yyyy-MM-dd HH:mm")
    }

    init {

    }

}