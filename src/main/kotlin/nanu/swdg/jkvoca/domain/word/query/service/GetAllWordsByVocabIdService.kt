package nanu.swdg.jkvoca.domain.word.query.service

import jakarta.transaction.Transactional
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordResponse
import nanu.swdg.jkvoca.domain.word.query.repository.WordQueryRepository
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetAllWordsByVocabIdService(
    private val wordRepository: WordQueryRepository
) {

    @Transactional()
    fun execute(vocabId: String): List<WordResponse> {
        val words = wordRepository.findByVocabIdOrderByWordIndex(UUID.fromString(vocabId))

        return words.map { WordResponse.from(it) }
    }
}