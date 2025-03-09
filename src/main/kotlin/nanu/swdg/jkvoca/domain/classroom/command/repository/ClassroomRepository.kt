package nanu.swdg.jkvoca.domain.classroom.command.repository

import nanu.swdg.jkvoca.domain.classroom.entity.Classroom
import org.springframework.data.jpa.repository.JpaRepository
import java.util.UUID

interface ClassroomRepository : JpaRepository<Classroom, UUID>