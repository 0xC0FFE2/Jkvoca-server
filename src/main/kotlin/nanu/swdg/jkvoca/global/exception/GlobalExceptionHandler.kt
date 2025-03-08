package nanu.swdg.jkvoca.global.exception

import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.ErrorResponse
import org.springframework.web.bind.annotation.ExceptionHandler
import org.springframework.web.bind.annotation.RestControllerAdvice
import org.springframework.web.context.request.ServletWebRequest
import org.springframework.web.context.request.WebRequest
import java.time.LocalDateTime

@RestControllerAdvice
class GlobalExceptionHandler {
    @ExceptionHandler(CustomException::class)
    fun handleCustomException(
        e: CustomException,
        request: WebRequest,
    ): ResponseEntity<nanu.swdg.jkvoca.global.exception.ErrorResponse> {
        val errorResponse =
            ErrorResponse(
                statusCode = e.statusCode,
                message = e.message,
                timestamp = LocalDateTime.now(),
                path = (request as ServletWebRequest).request.requestURI,
                method = (request).request.method,
            )
        return ResponseEntity(errorResponse, HttpStatus.valueOf(e.statusCode))
    }
}