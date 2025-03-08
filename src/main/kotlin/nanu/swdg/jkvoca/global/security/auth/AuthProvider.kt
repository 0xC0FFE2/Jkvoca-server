package nanu.swdg.jkvoca.global.security.auth

import nanu.swdg.jkvoca.global.external.nanuid.presentation.controller.NanuIdAuthClient
import nanu.swdg.jkvoca.global.security.auth.exception.NoPermissionException
import nanu.swdg.jkvoca.global.security.auth.exception.NoTokenException
import nanu.swdg.jkvoca.global.security.auth.exception.OAuthFailException
import org.springframework.beans.factory.annotation.Value
import org.springframework.security.authentication.AuthenticationProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.Authentication
import org.springframework.security.core.AuthenticationException
import org.springframework.security.core.authority.SimpleGrantedAuthority
import org.springframework.stereotype.Component

@Component
class AuthProvider(
    private val nanuIdAuthClient: NanuIdAuthClient,
    @Value("\${auth.admin.email}") private val adminEmail: String
) : AuthenticationProvider {

    @Throws(AuthenticationException::class)
    override fun authenticate(authentication: Authentication): Authentication {
        val token = authentication.credentials as? String
            ?: throw NoTokenException

        return try {
            val email = nanuIdAuthClient.getAccountEmail("Bearer $token")
            println(email)
            println(adminEmail)

            if (email == adminEmail) {
                UsernamePasswordAuthenticationToken(
                    email,
                    token,
                    listOf(SimpleGrantedAuthority("ROLE_ADMIN"))
                )
            } else {
                throw NoPermissionException
            }
        } catch (e: Exception) {
            e.printStackTrace()
            throw OAuthFailException
        }
    }

    override fun supports(authentication: Class<*>): Boolean {
        return true
    }
}