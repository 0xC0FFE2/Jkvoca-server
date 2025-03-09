package nanu.swdg.jkvoca.domain.vocab.query.service

import nanu.swdg.jkvoca.domain.vocab.exception.VocabNotFoundException
import nanu.swdg.jkvoca.domain.vocab.query.dto.response.VocabInfoResponse
import nanu.swdg.jkvoca.domain.vocab.query.repository.VocabQueryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetVocabInfoService(
    private val vocabQueryRepository: VocabQueryRepository
) {
    fun execute(vocabId: String): VocabInfoResponse {
        val vocabIdUUID = runCatching { UUID.fromString(vocabId) }
            .getOrElse { throw VocabNotFoundException }

        return vocabQueryRepository.findById(vocabIdUUID)
            .map(VocabInfoResponse::from)
            .orElseThrow { VocabNotFoundException }
    }
}