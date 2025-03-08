package nanu.swdg.jkvoca.domain.auth.presentation

import nanu.swdg.jkvoca.domain.auth.presentation.dto.request.OAuthCodeRequest
import nanu.swdg.jkvoca.domain.auth.presentation.dto.response.TokenResponse
import nanu.swdg.jkvoca.domain.auth.service.OAuthService
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@RestController
@RequestMapping("/v1/auth")
class AuthController (
    private val oAuthService: OAuthService
) {
    @PostMapping("/token")
    fun getToken(@RequestBody request: OAuthCodeRequest): ResponseEntity<TokenResponse> {
        val tokenResponse = oAuthService.verifyAndGetToken(request.code)
        return ResponseEntity.ok(tokenResponse)
    }
}