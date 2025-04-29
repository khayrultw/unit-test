package com.khayrul.mob24.data.repo

import com.khayrul.mob24.data.model.Task

interface TaskRepo {
    fun getTasks(): List<Task>
    fun addTask(task: Task)
    fun getTask(id: Int): Task?
    fun update(id: Int, task: Task)
    fun delete(id: Int)
}