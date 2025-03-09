package nanu.swdg.jkvoca.domain.classroom.query.repository

import nanu.swdg.jkvoca.domain.classroom.entity.Classroom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClassroomQueryRepository : JpaRepository<Classroom, UUID>