package nanu.swdg.jkvoca.global.security.filter

import jakarta.servlet.FilterChain
import jakarta.servlet.http.HttpServletRequest
import jakarta.servlet.http.HttpServletResponse
import nanu.swdg.jkvoca.global.security.auth.AuthProvider
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken
import org.springframework.security.core.context.SecurityContextHolder
import org.springframework.stereotype.Component
import org.springframework.web.filter.OncePerRequestFilter

@Component
class JwtAuthenticationFilter(
    private val authProvider: AuthProvider
) : OncePerRequestFilter() {

    companion object {
        private val PUBLIC_PATHS = listOf(
            "/v1/auth/token",
            "/v1/vocab/read",
            "/v1/vocab/info",
            "/v1/vocab/search/",
            "/v1/words/vocab/list"
        )
        private const val BEARER_PREFIX = "Bearer "
    }

    override fun doFilterInternal(
        request: HttpServletRequest,
        response: HttpServletResponse,
        filterChain: FilterChain
    ) {
        val path = request.servletPath

        if (isPublicPath(path)) {
            filterChain.doFilter(request, response)
            return
        }

        processAuthentication(request)
        filterChain.doFilter(request, response)
    }

    private fun isPublicPath(path: String): Boolean {
        return PUBLIC_PATHS.any { path.startsWith(it) }
    }

    private fun processAuthentication(request: HttpServletRequest) {
        try {
            val authHeader = request.getHeader("Authorization") ?: return

            if (!authHeader.startsWith(BEARER_PREFIX)) return

            val token = authHeader.substring(BEARER_PREFIX.length)
            val authToken = UsernamePasswordAuthenticationToken(null, token, null)
            val authentication = authProvider.authenticate(authToken)

            SecurityContextHolder.getContext().authentication = authentication
        } catch (e: Exception) {
        }
    }
}