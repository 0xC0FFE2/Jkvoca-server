package nanu.swdg.jkvoca.domain.word.presentation

import jakarta.validation.Valid
import nanu.swdg.jkvoca.domain.word.command.dto.request.WordCreateRequest
import nanu.swdg.jkvoca.domain.word.command.dto.request.WordUpdateRequest
import nanu.swdg.jkvoca.domain.word.command.service.WordCreateService
import nanu.swdg.jkvoca.domain.word.command.service.WordDeleteService
import nanu.swdg.jkvoca.domain.word.command.service.WordUpdateService
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordPageResponse
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordResponse
import nanu.swdg.jkvoca.domain.word.query.service.GetAllWordsByVocabIdService
import nanu.swdg.jkvoca.domain.word.query.service.GetPagedWordsService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/words")
class WordController(
    private val wordCreateService: WordCreateService,
    private val wordUpdateService: WordUpdateService,
    private val wordDeleteService: WordDeleteService,
    private val getAllWordsByVocabIdService: GetAllWordsByVocabIdService,
    private val getPagedWordsService: GetPagedWordsService
) {

    @PostMapping()
    @ResponseStatus(HttpStatus.CREATED)
    fun createWord(@Valid @RequestBody request: WordCreateRequest) {
        wordCreateService.createWord(request)
    }

    @PutMapping()
    @ResponseStatus(HttpStatus.OK)
    fun updateWord(@Valid @RequestBody request: WordUpdateRequest) {
        wordUpdateService.updateWord(request)
    }

    @DeleteMapping("/{wordId}")
    fun deleteWord(@PathVariable wordId: UUID): ResponseEntity<Void> {
        wordDeleteService.deleteWord(wordId)
        return ResponseEntity.noContent().build()
    }

    @GetMapping("/vocab/list/all/{vocabId}")
    fun getWordsByVocabId(@PathVariable vocabId: String): ResponseEntity<List<WordResponse>> {
        val words = getAllWordsByVocabIdService.execute(vocabId)
        return ResponseEntity.ok(words)
    }

    @GetMapping("/vocab/list/{vocabId}")
    fun getWordsByVocabIdPaged(
        @PathVariable vocabId: String,
        @RequestParam(defaultValue = "0") page: Int,
        @RequestParam(defaultValue = "10") size: Int
    ): ResponseEntity<WordPageResponse> {
        val pagedWords = getPagedWordsService.execute(vocabId, page, size)
        return ResponseEntity.ok(pagedWords)
    }
}