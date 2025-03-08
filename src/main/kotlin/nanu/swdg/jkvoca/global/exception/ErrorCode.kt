package nanu.swdg.jkvoca.global.exception

enum class ErrorCode(
    val status: Int,
    val message: String,
) {
    USER_NOT_FOUND(404, "사용자를 찾을 수 없습니다."),
    USER_INFO_FETCH_ERROR(500, "인증 서버 오류입니다."),
    USER_NO_PERMISSION(403,"접근 권한이 없습니다."),

    INVALID_ACCESS_TOKEN(400, "액세스 토큰이 만료되었거나 유효하지 않습니다."),
    EMPTY_ACCESS_TOKEN(400, "액세스 토큰이 비어 있습니다."),
    OAUTH_AUTH_FAIL(401,"OAuth 서비스 인증에 실패했습니다."),

    JWT_TOKEN_EXPIRED(401, "JWT 토큰이 만료되었습니다."),
    JWT_TOKEN_INVALID(401, "JWT 토큰이 유효하지 않습니다."),
}