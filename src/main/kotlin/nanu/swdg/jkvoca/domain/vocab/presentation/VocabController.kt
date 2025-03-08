package nanu.swdg.jkvoca.domain.vocab.presentation

import jakarta.validation.Valid
import nanu.swdg.jkvoca.domain.vocab.command.dto.request.VocabCreateRequest
import nanu.swdg.jkvoca.domain.vocab.command.service.VocabCreateService
import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/vocab")
class VocabController(
    private val vocabCreateService: VocabCreateService
) {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createVocab(@Valid @RequestBody request: VocabCreateRequest) {
        vocabCreateService.createVocab(request)
    }
}