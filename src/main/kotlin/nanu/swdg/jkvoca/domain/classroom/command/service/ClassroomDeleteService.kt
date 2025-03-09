package nanu.swdg.jkvoca.domain.classroom.command.service

import nanu.swdg.jkvoca.domain.classroom.command.repository.ClassroomRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClassroomDeleteService(
    private val classroomRepository: ClassroomRepository
) {
    @Transactional
    fun deleteClassroom(classroomId: String) {
        val classroomIdUUID = UUID.fromString(classroomId)
        val classroom = classroomRepository.findByIdOrNull(classroomIdUUID)
            ?: throw NoSuchElementException("Classroom not found")

        classroomRepository.delete(classroom)
    }
}