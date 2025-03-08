package nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.request

data class NanuIdAuthVerifyRequest(
    val authCode : String,
    val applicationSecret : String,
)
