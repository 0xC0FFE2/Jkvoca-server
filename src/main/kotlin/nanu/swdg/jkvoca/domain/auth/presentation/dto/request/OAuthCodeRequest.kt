package nanu.swdg.jkvoca.domain.auth.presentation.dto.request

import jakarta.validation.constraints.NotBlank

data class OAuthCodeRequest(
    @NotBlank
    val code: String
)
