package nanu.swdg.jkvoca.domain.word.command.dto.request

import jakarta.validation.constraints.NotBlank
import nanu.swdg.jkvoca.domain.word.entity.enums.Difficulty

data class WordCreateRequest(
    @NotBlank
    val vocabId: String,
    @NotBlank()
    val english: String,
    @NotBlank()
    val korean: String,
    @NotBlank()
    val example: String,
    @NotBlank()
    val pronunciation: String,
    @NotBlank()
    val difficulty: Difficulty,
)
