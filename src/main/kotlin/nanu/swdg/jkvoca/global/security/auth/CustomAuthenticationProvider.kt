package nanu.swdg.jkvoca.global.security.auth

import nanu.swdg.jkvoca.global.external.nanuid.presentation.controller.NanuIdAuthClient
import nanu.swdg.jkvoca.global.security.auth.exception.NoPermissionException
import nanu.swdg.jkvoca.global.security.auth.exception.NoTokenException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.stereotype.Component

@Component
class CustomAuthenticationProvider(
    private val nanuIdAuthClient: NanuIdAuthClient,
    @Value("\${auth.admin.email}") private val adminEmail: String
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.credentials as? String
            ?: throw NoTokenException

        return try {
            val email = nanuIdAuthClient.getAccountEmail("Bearer $token")

            if (email == adminEmail) {
                authentication.apply {
                    isAuthenticated = true
                }
            } else {
                throw NoPermissionException
            }
        } catch (e: Exception) {
            throw NoPermissionException
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return true
    }
}