package nanu.swdg.jkvoca.domain.classroom.command.service

import nanu.swdg.jkvoca.domain.classroom.command.dto.request.ClassroomUpdateRequest
import nanu.swdg.jkvoca.domain.classroom.command.repository.ClassroomRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClassroomUpdateService(
    private val classroomRepository: ClassroomRepository
) {
    @Transactional
    fun updateClassroom(classroomId: String, request: ClassroomUpdateRequest) {
        val classroomIdUUID = UUID.fromString(classroomId)
        val classroom = classroomRepository.findByIdOrNull(classroomIdUUID)
            ?: throw NoSuchElementException("Classroom not found")

        classroom.apply {
            request.classroomName?.let { classroomName = it }
            request.studyingVocab?.let { studyingVocabId = it;lastVocaId = 0 } //단어장 변경시 마지막 시험위치 변경
            request.testCount?.let { testCount = it }
        }
    }
}