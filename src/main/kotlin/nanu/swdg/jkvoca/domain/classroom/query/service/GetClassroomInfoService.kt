package nanu.swdg.jkvoca.domain.classroom.query.service

import nanu.swdg.jkvoca.domain.classroom.query.dto.response.ClassroomResponse
import nanu.swdg.jkvoca.domain.classroom.query.repository.ClassroomQueryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetClassroomInfoService(
    private val classroomQueryRepository: ClassroomQueryRepository
) {
    fun execute(classroomId: UUID): ClassroomResponse {
        val classroom = classroomQueryRepository.findById(classroomId)
            .orElseThrow { NoSuchElementException("해당 ID의 클래스룸을 찾을 수 없습니다: $classroomId") }

        return ClassroomResponse.from(classroom)
    }
}