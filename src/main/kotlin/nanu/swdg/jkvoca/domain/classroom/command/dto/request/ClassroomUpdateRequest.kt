package nanu.swdg.jkvoca.domain.classroom.command.dto.request

import jakarta.validation.constraints.NotBlank

data class ClassroomUpdateRequest(
    val classroomName: String?,
    val studyingVocab: String?,
    val testCount : Int?,
)
