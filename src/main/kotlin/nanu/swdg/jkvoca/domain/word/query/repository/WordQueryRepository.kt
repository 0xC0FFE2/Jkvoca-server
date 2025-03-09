package nanu.swdg.jkvoca.domain.word.query.repository

import nanu.swdg.jkvoca.domain.word.entity.Word
import org.springframework.data.domain.Page
import org.springframework.data.domain.PageRequest
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface WordQueryRepository : JpaRepository<Word, UUID> {
    fun findByVocabIdOrderByWordIndex(vocabId: UUID): List<Word>

    fun findByVocabId(vocabId: UUID, pageable: PageRequest): Page<Word>
}