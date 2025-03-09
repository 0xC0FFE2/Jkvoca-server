package nanu.swdg.jkvoca.domain.vocab.entity

import jakarta.persistence.*
import nanu.swdg.jkvoca.domain.word.entity.Word
import java.util.UUID

@Entity
@Table(name = "tbl_vocab")
data class Vocab(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(
        name = "vocab_id",
        columnDefinition = "BINARY(16)"
    )
    val id: UUID? = null,

    @Column(name = "vocab_name")
    val name: String,

    @Column(name = "word_count")
    val count: Int,

    @Column(name = "vocab_description")
    val description: String,

    @Column(name = "vocab_category")
    val category: String,

    @Column(name = "vocab_level")
    val level: String,
)