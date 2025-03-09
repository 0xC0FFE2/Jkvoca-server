package nanu.swdg.jkvoca.domain.vocab.command.service

import jakarta.transaction.Transactional
import nanu.swdg.jkvoca.domain.vocab.command.repository.VocabRepository
import nanu.swdg.jkvoca.domain.word.command.repository.WordRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class VocabDeleteService(
    private val vocabRepository: VocabRepository,
    private val wordRepository: WordRepository
) {
    @Transactional
    fun deleteVocab(vocabId: String) {
        val vocabIdUUID = UUID.fromString(vocabId)
        val vocab = vocabRepository.findById(vocabIdUUID).orElseThrow {
            throw IllegalArgumentException("Vocab not found with id: $vocabId")
        }

        vocabRepository.delete(vocab)
    }
}
