package nanu.swdg.jkvoca.domain.classroom.command.service

import nanu.swdg.jkvoca.domain.classroom.command.repository.ClassroomRepository
import nanu.swdg.jkvoca.domain.vocab.command.repository.VocabRepository
import org.springframework.data.repository.findByIdOrNull
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Service
class ClassroomNextWordsService(
    private val classroomRepository: ClassroomRepository,
    private val vocabRepository: VocabRepository
) {
    @Transactional
    fun moveToNextWords(classroomId: String) {
        val classroomIdUUID = UUID.fromString(classroomId)
        val classroom = classroomRepository.findByIdOrNull(classroomIdUUID)
            ?: throw NoSuchElementException("교실을 찾을 수 없습니다")

        val vocabId = try {
            UUID.fromString(classroom.studyingVocabId)
        } catch (e: IllegalArgumentException) {
            throw IllegalStateException("유효하지 않은 단어장 ID입니다")
        }

        val vocab = vocabRepository.findByIdOrNull(vocabId)
            ?: throw NoSuchElementException("단어장을 찾을 수 없습니다")

        val testCount = classroom.testCount ?: 10
        val currentIndex = classroom.lastVocaId ?: 0
        val totalWordCount = vocab.count

        val nextIndex = if (currentIndex + testCount >= totalWordCount) {
            currentIndex
        } else {
            currentIndex + testCount
        }

        classroom.lastVocaId = nextIndex
    }
}