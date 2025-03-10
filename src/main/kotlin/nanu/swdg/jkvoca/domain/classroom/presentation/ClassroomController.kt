package nanu.swdg.jkvoca.domain.classroom.presentation

import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomCreateRequest
import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomUpdateRequest
import nanu.swdg.jkvoca.domain.classroom.command.service.*
import nanu.swdg.jkvoca.domain.classroom.query.dto.response.ClassroomResponse
import nanu.swdg.jkvoca.domain.classroom.query.service.GetAllClassroomService
import nanu.swdg.jkvoca.domain.classroom.query.service.GetClassroomInfoService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*
import java.util.UUID

@RestController
@RequestMapping("/v1/classroom")
class ClassroomController(
    private val classroomCreateService: ClassroomCreateService,
    private val classroomUpdateService: ClassroomUpdateService,
    private val getAllClassroomService: GetAllClassroomService,
    private val getClassroomInfoService: GetClassroomInfoService,
    private val classroomDeleteService: ClassroomDeleteService,
    private val classroomNextWordsService: ClassroomNextWordsService,
    private val classroomPreviousWordsService: ClassroomPreviousWordsService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClassroom(@RequestBody request: ClassroomCreateRequest) {
        classroomCreateService.createClassroom(request)
    }

    @PutMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateClassroom(
        @RequestBody request: ClassroomUpdateRequest,
        @PathVariable classroomId: String
    ) {
        classroomUpdateService.updateClassroom(classroomId, request)
    }

    @DeleteMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    fun deleteClassroom(@PathVariable classroomId: String) {
        classroomDeleteService.deleteClassroom(classroomId)
    }


    @GetMapping
    @ResponseStatus(HttpStatus.OK)
    fun getAllClassrooms(): List<ClassroomResponse> {
        return getAllClassroomService.execute()
    }

    @GetMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.OK)
    fun getClassroomInfo(@PathVariable classroomId: String): ClassroomResponse {
        return getClassroomInfoService.execute(UUID.fromString(classroomId))
    }

    @PutMapping("/{classroomId}/next")
    @ResponseStatus(HttpStatus.OK)
    fun moveToNextWords(@PathVariable classroomId: String) {
        classroomNextWordsService.moveToNextWords(classroomId)
    }

    @PutMapping("/{classroomId}/prev")
    @ResponseStatus(HttpStatus.OK)
    fun moveToPreviousWords(@PathVariable classroomId: String) {
        classroomPreviousWordsService.moveToPreviousWords(classroomId)
    }
}