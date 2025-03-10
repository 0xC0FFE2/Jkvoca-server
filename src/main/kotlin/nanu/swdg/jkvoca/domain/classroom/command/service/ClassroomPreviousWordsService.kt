package nanu.swdg.jkvoca.domain.classroom.command.service

import nanu.swdg.jkvoca.domain.classroom.command.repository.ClassroomRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClassroomPreviousWordsService(
    private val classroomRepository: ClassroomRepository
) {
    @Transactional
    fun moveToPreviousWords(classroomId: String) {
        val classroomIdUUID = UUID.fromString(classroomId)
        val classroom = classroomRepository.findByIdOrNull(classroomIdUUID)
            ?: throw NoSuchElementException("교실을 찾을 수 없습니다")

        val testCount = classroom.testCount ?: 10
        val currentIndex = classroom.lastVocaId ?: 0

        val previousIndex = if (currentIndex - testCount < 0) {
            0
        } else {
            currentIndex - testCount
        }

        classroom.lastVocaId = previousIndex
    }
}