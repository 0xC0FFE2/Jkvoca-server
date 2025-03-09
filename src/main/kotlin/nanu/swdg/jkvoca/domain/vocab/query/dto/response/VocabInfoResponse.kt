package nanu.swdg.jkvoca.domain.vocab.query.dto.response

import nanu.swdg.jkvoca.domain.vocab.entity.Vocab

data class VocabInfoResponse(
    val vocabId : String,
    val vocabName: String,
    val vocabCategory: String,
    val vocabDescription: String,
    val vocabLevel: String,
) {
    companion object {
        fun from(vocab: Vocab): VocabInfoResponse {
            return VocabInfoResponse(
                vocabId = vocab.id.toString(),
                vocabName = vocab.name,
                vocabCategory = vocab.category,
                vocabDescription = vocab.description,
                vocabLevel = vocab.level
            )
        }
    }
}