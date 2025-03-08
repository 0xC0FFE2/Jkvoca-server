package nanu.swdg.jkvoca.global.external.nanuid.presentation.controller

import feign.FeignException
import nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.request.NanuIdAuthVerifyRequest
import nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.response.NanuIdTokenResponse
import nanu.swdg.jkvoca.global.security.auth.exception.NoPermissionException
import nanu.swdg.jkvoca.global.security.auth.exception.OAuthFailException
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "nanuAuthClient", url = "https://auth.nanu.cc", configuration = [NanuIdAuthClientErrorDecoder::class])
interface NanuIdAuthClient {
    @PostMapping("/oauth/code")
    fun getToken(
        @RequestBody verifyDto: NanuIdAuthVerifyRequest
    ): NanuIdTokenResponse

    @GetMapping("/auth/get/email")
    fun getAccountEmail(
        @RequestHeader("Authorization") bearerToken: String
    ): String
}

class NanuIdAuthClientErrorDecoder : feign.codec.ErrorDecoder {
    override fun decode(methodKey: String, response: feign.Response): Exception {
        return when (response.status()) {
            401 -> OAuthFailException
            else -> feign.codec.ErrorDecoder.Default().decode(methodKey, response)
        }
    }
}