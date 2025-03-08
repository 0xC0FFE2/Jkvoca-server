package nanu.swdg.jkvoca.global.external.nanuid.presentation.controller

import nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.request.NanuIdAuthVerifyRequest
import nanu.swdg.jkvoca.global.external.nanuid.presentation.dto.response.NanuIdTokenResponse
import org.springframework.cloud.openfeign.FeignClient
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestBody
import org.springframework.web.bind.annotation.RequestHeader

@FeignClient(name = "nanuAuthClient", url = "https://auth.nanu.cc")
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