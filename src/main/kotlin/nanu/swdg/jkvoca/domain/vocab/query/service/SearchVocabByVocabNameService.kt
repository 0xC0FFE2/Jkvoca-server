package nanu.swdg.jkvoca.domain.vocab.query.service

import jakarta.transaction.Transactional
import nanu.swdg.jkvoca.domain.vocab.exception.VocabNotFoundException
import nanu.swdg.jkvoca.domain.vocab.query.dto.response.VocabInfoResponse
import nanu.swdg.jkvoca.domain.vocab.query.repository.VocabQueryRepository
import org.springframework.stereotype.Service

@Service
class SearchVocabByVocabNameService(
    private val vocabQueryRepository: VocabQueryRepository
) {
    @Transactional
    fun execute(vocabName: String): List<VocabInfoResponse> {
        val vocabs = vocabQueryRepository.findByNameContainingIgnoreCase(vocabName)
        if (vocabs.isEmpty()) {
            throw VocabNotFoundException
        }

        return vocabs.map {
            VocabInfoResponse(
                vocabId = it.id.toString(),
                vocabName = it.name,
                vocabCategory = it.category,
                vocabDescription = it.description,
                vocabLevel = it.level,
                wordCount = it.count
            )
        }
    }
}