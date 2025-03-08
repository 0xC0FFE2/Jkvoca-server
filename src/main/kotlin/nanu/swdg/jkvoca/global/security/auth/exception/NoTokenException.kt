package nanu.swdg.jkvoca.global.security.auth.exception

import nanu.swdg.jkvoca.global.exception.CustomException
import nanu.swdg.jkvoca.global.exception.ErrorCode

object NoTokenException : CustomException(ErrorCode.EMPTY_ACCESS_TOKEN)