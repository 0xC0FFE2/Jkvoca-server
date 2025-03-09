package nanu.swdg.jkvoca.domain.word.command.service

import nanu.swdg.jkvoca.domain.word.command.dto.request.WordUpdateRequest
import nanu.swdg.jkvoca.domain.word.command.repository.WordRepository
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class WordUpdateService(
    private val wordRepository: WordRepository
) {

    @Transactional
    fun updateWord(request: WordUpdateRequest): UUID {
        val word = wordRepository.findById(request.id)
            .orElseThrow { IllegalArgumentException("존재하지 않는 단어입니다: ${request.id}") }

        val updatedWord = word.copy(
            english = request.english,
            korean = request.korean,
            example = request.example,
            pronunciation = request.pronunciation,
            difficulty = request.difficulty
        )

        val savedWord = wordRepository.save(updatedWord)
        return savedWord.id ?: throw IllegalStateException("저장된 단어의 ID가 없습니다.")
    }
}