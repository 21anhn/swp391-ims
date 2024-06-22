package com.swp391.ims_application.payload;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskCompletionDTO {
    private int internId;
    private int programId;
    private long completedTasks;
    private long totalTasks;

    public TaskCompletionDTO(int internId, int programId, long completedTasks, long totalTasks) {
        this.internId = internId;
        this.programId = programId;
        this.completedTasks = completedTasks;
        this.totalTasks = totalTasks;
    }

    // Add getters and setters here
    public int getInternId() {
        return internId;
    }

    public void setInternId(int internId) {
        this.internId = internId;
    }

    public int getProgramId() {
        return programId;
    }

    public void setProgramId(int programId) {
        this.programId = programId;
    }

    public long getCompletedTasks() {
        return completedTasks;
    }

    public void setCompletedTasks(long completedTasks) {
        this.completedTasks = completedTasks;
    }

    public long getTotalTasks() {
        return totalTasks;
    }

    public void setTotalTasks(long totalTasks) {
        this.totalTasks = totalTasks;
    }
}
