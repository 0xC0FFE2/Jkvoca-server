package nanu.swdg.jkvoca.domain.classroom.command.service

import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomCreateRequest
import nanu.swdg.jkvoca.domain.classroom.command.repository.ClassroomRepository
import nanu.swdg.jkvoca.domain.classroom.entity.Classroom
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
class ClassroomCreateService(
    private val classroomRepository: ClassroomRepository
) {
    @Transactional
    fun createClassroom(request: ClassroomCreateRequest
    ) {
        val classroom = Classroom(
            classroomName = request.classroomName,
            studyingVocabId = "DEFAULT",
            lastVocaId = 0,
            testCount = request.testCount
        )
        classroomRepository.save(classroom)
    }
}