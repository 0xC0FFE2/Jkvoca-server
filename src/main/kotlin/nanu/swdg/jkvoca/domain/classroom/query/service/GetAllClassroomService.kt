package nanu.swdg.jkvoca.domain.classroom.query.service

import nanu.swdg.jkvoca.domain.classroom.query.dto.response.ClassroomResponse
import nanu.swdg.jkvoca.domain.classroom.query.repository.ClassroomQueryRepository
import org.springframework.stereotype.Service

@Service
class GetAllClassroomService(
    private val classroomQueryRepository: ClassroomQueryRepository
) {
    fun execute(): List<ClassroomResponse> {
        val classrooms = classroomQueryRepository.findAll()
        return classrooms.map { classroom -> ClassroomResponse.from(classroom) }
    }
}