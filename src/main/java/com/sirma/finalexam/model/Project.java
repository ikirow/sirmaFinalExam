package com.sirma.finalexam.model;

public class Project {
    private int projectID;
    private long longestDuration;

    public Project(int projectID) {
        this.projectID = projectID;
        this.longestDuration = 0;
    }

    public int getProjectID() {
        return projectID;
    }

    public long getLongestDuration() {
        return longestDuration;
    }

    public void updateDuration(long duration) {
        if (duration > longestDuration) {
            longestDuration = duration;
        }
    }
}
