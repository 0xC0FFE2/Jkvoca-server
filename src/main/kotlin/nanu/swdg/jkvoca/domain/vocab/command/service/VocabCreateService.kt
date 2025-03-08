package nanu.swdg.jkvoca.domain.vocab.command.service

import jakarta.transaction.Transactional
import nanu.swdg.jkvoca.domain.vocab.command.dto.request.VocabCreateRequest
import nanu.swdg.jkvoca.domain.vocab.command.repository.VocabRepository
import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import org.springframework.stereotype.Service

@Service
class VocabCreateService(
    private val vocabRepository: VocabRepository,
) {
    @Transactional
    fun createVocab(request : VocabCreateRequest) {
        val newVocab = Vocab(
            name = request.vocabName,
            category = request.vocabCategory,
            description = request.vocabDescription,
            level = request.vocabLevel,
            count = 0
        )

        vocabRepository.save(newVocab)
    }
}