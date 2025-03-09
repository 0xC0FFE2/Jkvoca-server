package nanu.swdg.jkvoca.domain.vocab.exception

import nanu.swdg.jkvoca.global.exception.CustomException
import nanu.swdg.jkvoca.global.exception.ErrorCode

object VocabNotFoundException : CustomException(ErrorCode.NOT_FOUND)