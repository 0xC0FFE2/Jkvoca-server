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
@RequestMapping("/v2/classroom")
class ClassroomControllerV2(
    private val classroomCreateService: ClassroomCreateService,
    private val classroomUpdateService: ClassroomUpdateService,
    private val getAllClassroomService: GetAllClassroomService,
    private val getClassroomInfoService: GetClassroomInfoService,
    private val classroomDeleteService: ClassroomDeleteService,
    private val classroomNextWordsService: ClassroomNextWordsService,
    private val classroomPreviousWordsService: ClassroomPreviousWordsService
) {
    @GetMapping("/info/{classroomId}")
    @ResponseStatus(HttpStatus.OK)
    fun getClassroomInfo(@PathVariable classroomId: String): ClassroomResponse {
        return getClassroomInfoService.execute(UUID.fromString(classroomId))
    }
}