package nanu.swdg.jkvoca.domain.vocab.query.repository

import nanu.swdg.jkvoca.domain.vocab.entity.Vocab
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.UUID

@Repository
interface VocabQueryRepository : JpaRepository<Vocab, UUID>