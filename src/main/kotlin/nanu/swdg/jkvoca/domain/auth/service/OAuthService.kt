package nanu.swdg.jkvoca.domain.auth.service

import nanu.swdg.jkvoca.domain.auth.presentation.dto.response.TokenResponse
import nanu.swdg.jkvoca.global.external.nanuid.presentation.controller.NanuIdAuthClient
import nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.request.NanuIdAuthVerifyRequest
import org.springframework.beans.factory.annotation.Value
import org.springframework.stereotype.Service

@Service
class OAuthService(
    private val nanuIdAuthClient: NanuIdAuthClient,
    @Value("\${auth.application-secret}") private val applicationSecret: String
) {
    fun verifyAndGetToken(code: String): TokenResponse {
        val verifyRequest = NanuIdAuthVerifyRequest(
            authCode = code,
            applicationSecret = applicationSecret
        )

        val tokenResponse = nanuIdAuthClient.getToken(verifyRequest)

        return TokenResponse(
            access_token = tokenResponse.access_token,
            refresh_token = tokenResponse.refresh_token
        )
    }
}