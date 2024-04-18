package br.com.will.payments.infrastructure.database.notifications

import br.com.will.payments.domain.entities.NotificationStatus
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query

interface NotificationsRepository : JpaRepository<NotificationEntity, Int> {

    @Query("select n from NotificationEntity n where n.status = ?1 order by n.createdAt asc LIMIT 100")
    fun findAllByStatus(status: NotificationStatus): List<NotificationEntity>
}