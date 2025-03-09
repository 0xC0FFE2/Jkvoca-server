package nanu.swdg.jkvoca.domain.vocab.presentation

import jakarta.validation.Valid
import nanu.swdg.jkvoca.domain.vocab.command.dto.request.VocabCreateRequest
import nanu.swdg.jkvoca.domain.vocab.command.service.VocabCreateService
import nanu.swdg.jkvoca.domain.vocab.command.service.VocabDeleteService
import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import nanu.swdg.jkvoca.domain.vocab.query.dto.response.VocabInfoResponse
import nanu.swdg.jkvoca.domain.vocab.query.service.GetAllVocabsService
import nanu.swdg.jkvoca.domain.vocab.query.service.GetVocabInfoService
import nanu.swdg.jkvoca.domain.vocab.query.service.SearchVocabByVocabNameService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/vocab")
class VocabController(
    private val vocabCreateService: VocabCreateService,
    private val getVocabInfoService: GetVocabInfoService,
    private val getAllVocabsService: GetAllVocabsService,
    private val vocabDeleteService: VocabDeleteService,
    private val searchVocabByVocabNameService: SearchVocabByVocabNameService

) {
    @PostMapping("/create")
    @ResponseStatus(HttpStatus.CREATED)
    fun createVocab(@Valid @RequestBody request: VocabCreateRequest) {
        vocabCreateService.createVocab(request)
    }

    @DeleteMapping("/delete/{vocabId}")
    @ResponseStatus(HttpStatus.OK)
    fun deleteVocab(@Valid @PathVariable vocabId : String) {
        vocabDeleteService.deleteVocab(vocabId)
    }

    @GetMapping("/read")
    @ResponseStatus(HttpStatus.OK)
    fun readVocab(@Valid @RequestBody request: VocabCreateRequest)  {
        vocabCreateService.createVocab(request)
    }

    @GetMapping("/info/{vocabId}")
    @ResponseStatus(HttpStatus.OK)
    fun readVocabInfo(@PathVariable vocabId : String) : VocabInfoResponse {
        return getVocabInfoService.execute(vocabId)
    }

    @GetMapping("/getAll")
    @ResponseStatus(HttpStatus.OK)
    fun readAllVocabs(): List<VocabInfoResponse> {
        return getAllVocabsService.execute()
    }

    @GetMapping("/search/{searchKey}")
    @ResponseStatus(HttpStatus.OK)
    fun searchVocabsByVocabName(@PathVariable searchKey : String):List<VocabInfoResponse> {
        return searchVocabByVocabNameService.execute(searchKey)
    }
}