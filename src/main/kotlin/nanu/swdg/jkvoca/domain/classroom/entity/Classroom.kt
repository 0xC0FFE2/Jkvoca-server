package nanu.swdg.jkvoca.domain.classroom.entity

import jakarta.persistence.*
import java.util.*

@Entity
@Table(name = "tbl_classroom")
data class Classroom(
    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    @Column(name = "classroom_id")
    val classroomId: UUID? = null,

    @Column(name = "classroom_name")
    var classroomName: String? = null,

    @Column(name = "studying_vocab_id")
    var studyingVocabId: String? = null,

    @Column(name = "last_voca_id")
    var lastVocaId: Int? = null,

    @Column(name = "test_count")
    var testCount: Int? = null
)