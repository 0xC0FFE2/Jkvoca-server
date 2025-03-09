package nanu.swdg.jkvoca.domain.classroom.command.dto.request

import jakarta.validation.constraints.NotBlank

data class ClassroomCreateRequest(
    @NotBlank
    val classroomName: String,
    @NotBlank
    val testCount : Int,
)
