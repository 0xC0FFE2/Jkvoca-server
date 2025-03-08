package nanu.swdg.jkvoca.domain.auth.presentation.dto.response

data class TokenResponse(
    val access_token : String,
    val refresh_token: String
)
