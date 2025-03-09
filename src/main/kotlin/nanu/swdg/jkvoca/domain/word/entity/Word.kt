package nanu.swdg.jkvoca.domain.word.entity

import jakarta.persistence.*
import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import nanu.swdg.jkvoca.domain.word.entity.enums.Difficulty
import java.util.*

@Entity
@Table(name = "tbl_word")
data class Word(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
        name = "word_id",
        columnDefinition = "BINARY(16)"
    )
    val id: UUID? = null,

    @Column(name = "vocab_id", columnDefinition = "BINARY(16)")
    val vocabId: UUID,

    @Column(name = "english")
    val english: String,

    @Column(name = "korean")
    val korean: String,

    @Column(name = "example")
    val example: String,

    @Column(name = "pronunciation")
    val pronunciation: String,

    @Column(name = "difficulty")
    @Enumerated(EnumType.STRING)
    val difficulty: Difficulty,

    @Column(name = "vocab_index")
    val wordIndex: Int
)