package nanu.swdg.jkvoca.domain.word.command.service

import nanu.swdg.jkvoca.domain.word.command.repository.WordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class WordDeleteService(
    private val wordRepository: WordRepository
) {

    @Transactional
    fun deleteWord(wordId: UUID) {
        val word = wordRepository.findById(wordId)
            .orElseThrow { IllegalArgumentException("존재하지 않는 단어입니다: $wordId") }

        val vocabId = word.vocabId
        val deletedWordIndex = word.wordIndex

        wordRepository.deleteById(wordId)

        wordRepository.decrementWordIndexesAfterDeletion(vocabId, deletedWordIndex)
    }
}