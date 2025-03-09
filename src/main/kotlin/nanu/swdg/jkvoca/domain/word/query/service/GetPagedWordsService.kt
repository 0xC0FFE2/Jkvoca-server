package nanu.swdg.jkvoca.domain.word.query.service

import jakarta.transaction.Transactional
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordPageResponse
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordResponse
import nanu.swdg.jkvoca.domain.word.query.repository.WordQueryRepository
import org.springframework.data.domain.PageRequest
import org.springframework.data.domain.Sort
import org.springframework.stereotype.Service
import java.util.*

@Service
class GetPagedWordsService (
    private val wordQueryRepository: WordQueryRepository
) {

    @Transactional()
    fun execute(vocabId: String, page: Int, size: Int ): WordPageResponse {

        val pageable = PageRequest.of(
            page,
            size,
            Sort.by(Sort.Direction.ASC, "wordIndex")
        )

        val wordPage = wordQueryRepository.findByVocabId(UUID.fromString(vocabId), pageable)

        val wordResponsePage = wordPage.map { WordResponse.from(it) }

        return WordPageResponse.from(wordResponsePage)
    }
}