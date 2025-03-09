package nanu.swdg.jkvoca.domain.word.query.dto.response

import nanu.swdg.jkvoca.domain.word.entity.Word
import nanu.swdg.jkvoca.domain.word.entity.enums.Difficulty
import java.util.UUID

data class WordResponse(
    val id: UUID,
    val english: String,
    val korean: String,
    val example: String,
    val pronunciation: String,
    val difficulty: Difficulty,
    val wordIndex: Int
) {
    companion object {
        fun from(word: Word): WordResponse {
            return WordResponse(
                id = word.id!!,
                english = word.english,
                korean = word.korean,
                example = word.example,
                pronunciation = word.pronunciation,
                difficulty = word.difficulty,
                wordIndex = word.wordIndex
            )
        }
    }
}