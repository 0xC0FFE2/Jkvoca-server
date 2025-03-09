package nanu.swdg.jkvoca.domain.word.command.service

import nanu.swdg.jkvoca.domain.vocab.command.repository.VocabRepository
import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import nanu.swdg.jkvoca.domain.word.command.dto.request.WordCreateRequest
import nanu.swdg.jkvoca.domain.word.command.repository.WordRepository
import nanu.swdg.jkvoca.domain.word.entity.Word
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class WordCreateService(
    private val wordRepository: WordRepository,
    private val vocabRepository: VocabRepository
) {

    @Transactional
    fun createWord(request: WordCreateRequest): UUID {
        val vocabId = request.vocabId
        val vocabIdUUID = UUID.fromString(vocabId)

        val vocab = vocabRepository.findById(vocabIdUUID)
            .orElseThrow { IllegalArgumentException("해당 ID의 단어장이 존재하지 않습니다: $vocabId") }

        val wordCount = wordRepository.countByVocabId(vocabIdUUID)

        val word = Word(
            vocabId = vocabIdUUID,
            english = request.english,
            korean = request.korean,
            example = request.example,
            pronunciation = request.pronunciation,
            difficulty = request.difficulty,
            wordIndex = wordCount
        )

        val savedWord = wordRepository.save(word)

        val updatedVocab = vocab.copy(count = wordCount + 1)
        vocabRepository.save(updatedVocab)

        return savedWord.id ?: throw IllegalStateException("저장된 단어의 ID가 없습니다.")
    }
}