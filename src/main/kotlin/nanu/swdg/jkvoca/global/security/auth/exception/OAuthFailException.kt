package nanu.swdg.jkvoca.global.security.auth.exception

import nanu.swdg.jkvoca.global.exception.CustomException
import nanu.swdg.jkvoca.global.exception.ErrorCode

object OAuthFailException : CustomException(ErrorCode.OAUTH_AUTH_FAIL)