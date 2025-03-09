package nanu.swdg.jkvoca.domain.vocab.query.service

import nanu.swdg.jkvoca.domain.vocab.query.dto.response.VocabInfoResponse
import nanu.swdg.jkvoca.domain.vocab.query.repository.VocabQueryRepository
import org.springframework.stereotype.Service

@Service
class GetAllVocabsService(
    private val vocabQueryRepository: VocabQueryRepository
) {
    fun execute(): List<VocabInfoResponse> {
        val vocabs = vocabQueryRepository.findAll()
        return vocabs.map { VocabInfoResponse.from(it) }
    }
}