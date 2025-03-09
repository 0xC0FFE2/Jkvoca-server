package nanu.swdg.jkvoca.domain.word.command.repository

import nanu.swdg.jkvoca.domain.word.entity.Word
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.data.repository.query.Param
import java.util.UUID

interface WordRepository : JpaRepository<Word, UUID> {

    fun countByVocabId(vocabId: UUID): Int

    @Modifying
    @Query("UPDATE Word w SET w.wordIndex = w.wordIndex - 1 WHERE w.vocabId = :vocabId AND w.wordIndex > :deletedIndex")
    fun decrementWordIndexesAfterDeletion(
        @Param("vocabId") vocabId: UUID,
        @Param("deletedIndex") deletedIndex: Int
    )
}