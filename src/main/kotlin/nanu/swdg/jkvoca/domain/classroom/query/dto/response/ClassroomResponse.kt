package nanu.swdg.jkvoca.domain.classroom.query.dto.response

import nanu.swdg.jkvoca.domain.classroom.entity.Classroom
import java.util.*

data class ClassroomResponse(
    val classroomId: UUID?,
    val classroomName: String?,
    val studyingVocabId: String?,
    val lastVocaId: Int?,
    val testCount: Int?
) {
    companion object {
        fun from(classroom: Classroom): ClassroomResponse {
            return ClassroomResponse(
                classroomId = classroom.classroomId,
                classroomName = classroom.classroomName,
                studyingVocabId = classroom.studyingVocabId,
                lastVocaId = classroom.lastVocaId,
                testCount = classroom.testCount
            )
        }
    }
}