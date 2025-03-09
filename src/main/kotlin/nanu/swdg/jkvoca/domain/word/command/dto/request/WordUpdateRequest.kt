package nanu.swdg.jkvoca.domain.word.command.dto.request

import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull
import nanu.swdg.jkvoca.domain.word.entity.enums.Difficulty
import java.util.UUID

data class WordUpdateRequest(
    @NotNull
    val id: UUID,

    @NotBlank
    val english: String,

    @NotBlank
    val korean: String,

    @NotBlank
    val example: String,

    @NotBlank
    val pronunciation: String,

    @NotNull
    val difficulty: Difficulty
)