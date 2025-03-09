package nanu.swdg.jkvoca.domain.classroom.presentation

import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomCreateRequest
import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomUpdateRequest
import nanu.swdg.jkvoca.domain.classroom.command.service.ClassroomCreateService
import nanu.swdg.jkvoca.domain.classroom.command.service.ClassroomUpdateService
import org.springframework.http.HttpStatus
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/v1/classroom")
class ClassroomController(
    private val classroomCreateService: ClassroomCreateService,
    private val classroomUpdateService: ClassroomUpdateService
) {
    @PostMapping
    @ResponseStatus(HttpStatus.CREATED)
    fun createClassroom(@RequestBody request: ClassroomCreateRequest) {
        classroomCreateService.createClassroom(request)
    }

    @PutMapping("/{classroomId}")
    @ResponseStatus(HttpStatus.OK)
    fun updateClassroom(@RequestBody request: ClassroomUpdateRequest,
                        @PathVariable classroomId: String) {
        classroomUpdateService.updateClassroom(classroomId,request)
    }
}