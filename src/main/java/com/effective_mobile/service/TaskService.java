package com.effective_mobile.service;

import com.effective_mobile.aspect.annotation.CustomLoggingFinishedMethod;
import com.effective_mobile.aspect.annotation.CustomLoggingStartMethod;
import com.effective_mobile.dto.*;
import com.effective_mobile.exception.TaskNotFoundException;
import com.effective_mobile.exception.UserNotFoundException;
import com.effective_mobile.model.Comments;
import com.effective_mobile.model.Task;
import com.effective_mobile.model.UserInfo;
import com.effective_mobile.repository.CommentRepository;
import com.effective_mobile.repository.TaskRepository;
import com.effective_mobile.repository.UserInfoRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Сервис для управления задачами в системе.
 * Предоставляет методы для создания, получения, обновления и удаления задач,
 * а также для получения задач и комментариев по автору или исполнителю.
 *
 */
@Service
public class TaskService {

    /**
     * Репозиторий для доступа к задачам в базе данных.
     */
    private final TaskRepository taskRepository;

    /**
     * Репозиторий для доступа к информации о пользователях в базе данных.
     */
    private final UserInfoRepository userInfoRepository;

    /**
     * Репозиторий для доступа к комментариям в базе данных.
     */
    private final CommentRepository commentRepository;

    /**
     * Конструктор для инициализации репозиториев.
     *
     * @param taskRepository      репозиторий задач
     * @param userInfoRepository  репозиторий информации о пользователях
     * @param commentRepository репозиторий комментариев
     */
    public TaskService(TaskRepository taskRepository, UserInfoRepository userInfoRepository, CommentRepository commentRepository) {
        this.taskRepository = taskRepository;
        this.userInfoRepository = userInfoRepository;
        this.commentRepository = commentRepository;
    }

    /**
     * Создает новую задачу на основе предоставленных данных.
     *
     * @param createTaskDto объект, содержащий данные для создания задачи
     * @return DTO объект, содержащий информацию о созданной задаче
     * @throws UserNotFoundException если не найден пользователь с указанным email автора или исполнителя
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto createTask(CreateTaskDto createTaskDto) {
        Task taskForDb = new Task();
        taskForDb.setTitle(createTaskDto.getTitle());
        taskForDb.setDescription(createTaskDto.getDescription());
        taskForDb.setPriority(createTaskDto.getPriority());

        UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(createTaskDto.getEmailAssignee())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email не найден: " +
                        createTaskDto.getEmailAssignee()));

        taskForDb.setAssignee(userInfoFromDbAssignee);

        UserInfo userInfoFromDbAuthor = userInfoRepository.findByEmail(createTaskDto.getEmailAuthor())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email не найден: " +
                        createTaskDto.getEmailAuthor()));
        taskForDb.setAuthor(userInfoFromDbAuthor);

        taskForDb.setStatus(createTaskDto.getStatus());

        Task taskFromDb = taskRepository.save(taskForDb);

        TaskFromDbDto taskFromDbDto = new TaskFromDbDto();
        taskFromDbDto.setId(taskFromDb.getId());
        taskFromDbDto.setTitle(taskFromDb.getTitle());
        taskFromDbDto.setDescription(taskFromDb.getDescription());
        taskFromDbDto.setStatus(taskFromDb.getStatus());
        taskFromDbDto.setPriority(taskFromDb.getPriority());
        taskFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
        taskFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());

        return taskFromDbDto;
    }

    /**
     * Получает задачу по ее идентификатору, включая связанные комментарии.
     *
     * @param id идентификатор задачи
     * @return DTO объект, содержащий информацию о задаче и ее комментариях
     * @throws TaskNotFoundException если задача с указанным идентификатором не найдена
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskWithCommentsFromDbDto getTaskById(Long id) {
        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с идентификатором не найдена: " + id));

        TaskWithCommentsFromDbDto taskWithCommentsFromDbDto = new TaskWithCommentsFromDbDto();
        taskWithCommentsFromDbDto.setId(taskFromDb.getId());
        taskWithCommentsFromDbDto.setDescription(taskFromDb.getDescription());
        taskWithCommentsFromDbDto.setPriority(taskFromDb.getPriority());
        taskWithCommentsFromDbDto.setStatus(taskFromDb.getStatus());
        taskWithCommentsFromDbDto.setTitle(taskFromDb.getTitle());
        taskWithCommentsFromDbDto.setEmailAssignee(taskFromDb.getAssignee().getEmail());
        taskWithCommentsFromDbDto.setEmailAuthor(taskFromDb.getAuthor().getEmail());

        List<String> stringList = commentRepository.getCommentTexts(id);
        List<CommentTextFromDbDto> commentsList = stringList.stream()
                .map(CommentTextFromDbDto::new)
                .collect(Collectors.toList());

        taskWithCommentsFromDbDto.setComments(commentsList);

        return taskWithCommentsFromDbDto;
    }

    /**
     * Обновляет существующую задачу на основе предоставленных данных.
     *
     * @param id          идентификатор задачи, которую необходимо обновить
     * @param updateTaskDto объект, содержащий данные для обновления задачи
     * @return DTO объект, содержащий информацию об обновленной задаче
     * @throws TaskNotFoundException если задача с указанным идентификатором не найдена
     * @throws UserNotFoundException если не найден пользователь с указанным email исполнителя
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTask(Long id, UpdateTaskDto updateTaskDto) {
        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с идентификатором не найдена: " + id));

        UserInfo userInfoFromDbAssignee = userInfoRepository.findByEmail(updateTaskDto.getEmailAssignee())
                .orElseThrow(() -> new UserNotFoundException("Пользователь с email не найден: " +
                        updateTaskDto.getEmailAssignee()));

        // Обновление полей задачи
        taskFromDb.setAssignee(userInfoFromDbAssignee);
        taskFromDb.setDescription(updateTaskDto.getDescription());
        taskFromDb.setPriority(updateTaskDto.getPriority());
        taskFromDb.setStatus(updateTaskDto.getStatus());
        taskFromDb.setTitle(updateTaskDto.getTitle());

        // Сохранение обновленной задачи в базе данных
        Task updatedTask = taskRepository.save(taskFromDb);

        // Создание DTO для возвращаемой информации
        TaskFromDbDto updatedTaskDTO = new TaskFromDbDto();
        updatedTaskDTO.setId(updatedTask.getId());
        updatedTaskDTO.setDescription(updatedTask.getDescription());
        updatedTaskDTO.setPriority(updatedTask.getPriority());
        updatedTaskDTO.setStatus(updatedTask.getStatus());
        updatedTaskDTO.setTitle(updatedTask.getTitle());
        updatedTaskDTO.setEmailAssignee(updatedTask.getAssignee().getEmail());
        updatedTaskDTO.setEmailAuthor(updatedTask.getAuthor().getEmail());

        return updatedTaskDTO;
    }

    /**
     * Обновляет статус существующей задачи.
     *
     * @param id            идентификатор задачи, статус которой необходимо обновить
     * @param statusTaskDto объект, содержащий новый статус задачи
     * @return DTO объект, содержащий информацию об обновленной задаче
     * @throws TaskNotFoundException если задача с указанным идентификатором не найдена
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public TaskFromDbDto updateTaskStatus(Long id, StatusTaskDto statusTaskDto) {
        Task taskFromDb = taskRepository.findById(id)
                .orElseThrow(() -> new TaskNotFoundException("Задача с идентификатором не найдена: " + id));

        // Обновление статуса задачи
        taskFromDb.setStatus(statusTaskDto.getStatus());

        // Сохранение обновленной задачи в базе данных
        Task updatedStatusTask = taskRepository.save(taskFromDb);

        // Создание DTO для возвращаемой информации
        TaskFromDbDto updatedStatusDTO = new TaskFromDbDto();
        updatedStatusDTO.setId(updatedStatusTask.getId());
        updatedStatusDTO.setDescription(updatedStatusTask.getDescription());
        updatedStatusDTO.setPriority(updatedStatusTask.getPriority());
        updatedStatusDTO.setStatus(updatedStatusTask.getStatus());
        updatedStatusDTO.setTitle(updatedStatusTask.getTitle());
        updatedStatusDTO.setEmailAssignee(updatedStatusTask.getAssignee().getEmail());
        updatedStatusDTO.setEmailAuthor(updatedStatusTask.getAuthor().getEmail());

        return updatedStatusDTO;
    }

    /**
     * Удаляет задачу по ее идентификатору.
     *
     * @param id идентификатор задачи, которую необходимо удалить
     * @throws TaskNotFoundException если задача с указанным идентификатором не найдена
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public void deleteTask(Long id) {
        if (!taskRepository.existsById(id)) {
            throw new TaskNotFoundException("Попытка удалить несуществующую задачу с идентификатором: " + id);
        }
        commentRepository.deleteByTaskId(id);
        taskRepository.deleteById(id);
    }

    /**
     * Получает список задач и комментариев, автором которых является указанный пользователь, с применением пагинации.
     *
     * @param authorId идентификатор автора задач
     * @param page     номер страницы
     * @param size     размер страницы
     * @return Page объект, содержащий список задач и комментариев, а также информацию о пагинации
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public Page<TaskAndCommentsForAuthorFromDbDto> getTasksAndCommentsByAuthor(Long authorId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получение страниц задач по автору
        Page<Task> tasksPage = taskRepository.findByAuthorId(authorId, pageable);

        // Преобразование задач в DTO объекты
        return tasksPage.map(task -> {
            List<String> comments = task.getComments().stream()
                    .map(Comments::getText)
                    .collect(Collectors.toList());

            return new TaskAndCommentsForAuthorFromDbDto(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getAuthor().getEmail(),
                    comments);
        });
    }

    /**
     * Получает список задач и комментариев, исполнителем которых является указанный пользователь, с применением пагинации.
     *
     * @param assigneeId идентификатор исполнителя задач
     * @param page       номер страницы
     * @param size       размер страницы
     * @return Page объект, содержащий список задач и комментариев, а также информацию о пагинации
     */
    @CustomLoggingStartMethod
    @CustomLoggingFinishedMethod
    public Page<TaskAndCommentsForAssigneeFromDbDto> getTasksAndCommentsByAssignee(Long assigneeId, int page, int size) {
        Pageable pageable = PageRequest.of(page, size);

        // Получение страниц задач по исполнителю
        Page<Task> tasksPage = taskRepository.findByAssigneeId(assigneeId, pageable);

        // Преобразование задач в DTO объекты
        return tasksPage.map(task -> {
            List<String> comments = task.getComments().stream()
                    .map(Comments::getText)
                    .collect(Collectors.toList());

            return new TaskAndCommentsForAssigneeFromDbDto(
                    task.getId(),
                    task.getTitle(),
                    task.getDescription(),
                    task.getStatus(),
                    task.getPriority(),
                    task.getAssignee().getEmail(),
                    comments);
        });
    }
}

