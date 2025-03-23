package com.effective_mobile.repository;

import com.effective_mobile.model.Comments;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;


/**
 * Репозиторий для доступа к комментариям в базе данных.
 * <p>
 * Этот интерфейс расширяет JpaRepository, что позволяет выполнять стандартные операции CRUD
 * для сущности Comments. Также включает методы для удаления комментариев по идентификатору задачи
 * и для получения текстов комментариев, связанных с определенной задачей.
 * </p>
 */
@Repository
public interface CommentRepository extends JpaRepository<Comments, Long> {

    /**
     * Удаляет все комментарии, связанные с указанным идентификатором задачи.
     *
     * @param taskId идентификатор задачи, для которой нужно удалить комментарии
     */
    void deleteByTaskId(Long taskId);

    /**
     * Получает список текстов комментариев, связанных с указанным идентификатором задачи.
     *
     * @param taskId идентификатор задачи, для которой нужно получить тексты комментариев
     * @return список текстов комментариев, связанных с указанной задачей
     */
    @Query(value = "SELECT text FROM comments WHERE task_id = :taskId", nativeQuery = true)
    List<String> getCommentTexts(@Param("taskId") Long taskId);
}