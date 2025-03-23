package com.effective_mobile.enums;

/**
 * Перечисление уровней приоритета.
 *
 */
public enum Priority {
    /**
     * Высокий приоритет.
     */
    HIGH,
    /**
     * Средний приоритет.
     */
    MEDIUM,
    /**
     * Низкий приоритет.
     */
    LOW;

    /**
     * Возвращает строковое представление текущего значения перечисления.
     *
     * @return строковое представление
     */
    @Override
    public String toString() {
        return name();
    }
}
