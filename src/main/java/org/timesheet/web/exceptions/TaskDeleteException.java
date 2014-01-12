package org.timesheet.web.exceptions;

import org.timesheet.domain.Task;

/**
 * User: seiyaa
 * Date: 14-1-4
 * Time: 下午5:13
 * Git: https://github.com/seiyaa
 */
public class TaskDeleteException extends Exception {
    private static final long serialVersionUID = 1L;
    private Task task;

    public TaskDeleteException(Task task) {
        this.task = task;
    }

    public Task getTask() {
        return task;
    }

    public void setTask(Task task) {
        this.task = task;
    }

}
