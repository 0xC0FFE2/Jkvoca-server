package nanu.swdg.jkvoca.global.exception

import java.time.LocalDateTime

data class ErrorResponse(
    val statusCode: Int,
    val message: String,
    val timestamp: LocalDateTime,
    val path: String,
    val method: String,
)
