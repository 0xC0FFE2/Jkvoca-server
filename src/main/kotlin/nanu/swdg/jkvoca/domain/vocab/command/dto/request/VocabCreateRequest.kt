package nanu.swdg.jkvoca.domain.vocab.command.dto.request

import jakarta.validation.constraints.NotBlank

data class VocabCreateRequest(
    @NotBlank()
    val vocabName: String,
    @NotBlank()
    val vocabCategory: String,
    @NotBlank()
    val vocabDescription: String,
    @NotBlank()
    val vocabLevel: String,
)
