
package nanu.swdg.jkvoca.domain.word.query.service

import nanu.swdg.jkvoca.domain.classroom.query.repository.ClassroomQueryRepository
import nanu.swdg.jkvoca.domain.word.query.dto.response.WordResponse
import nanu.swdg.jkvoca.domain.word.query.repository.WordQueryRepository
import org.springframework.stereotype.Service
import java.util.UUID

@Service
class GetWordsForClassroomService(
    private val classroomQueryRepository: ClassroomQueryRepository,
    private val wordQueryRepository: WordQueryRepository
) {
    fun execute(classroomId: UUID): List<WordResponse> {

        val classroom = classroomQueryRepository.findById(classroomId)
            .orElseThrow { NoSuchElementException("해당 ID의 클래스룸을 찾을 수 없습니다: $classroomId") }

        val vocabId = try {
            UUID.fromString(classroom.studyingVocabId)
        } catch (e: IllegalArgumentException) {
            println("UUID 변환 실패: ${e.message}")
            return emptyList()
        }

        val startIndex = classroom.lastVocaId ?: 0
        val endIndex = (classroom.lastVocaId ?: 0) + (classroom.testCount ?: 10)

        val words = wordQueryRepository.findByVocabIdAndIndexRange(vocabId, startIndex, endIndex)

        return words.map { WordResponse.from(it) }
    }
}