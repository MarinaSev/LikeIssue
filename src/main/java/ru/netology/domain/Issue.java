package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue {
    private int id;
    private boolean open;
    private String author;
//    private Label labels;
    private Set<String> labels = new HashSet<>();
    private Set<String> assignees = new HashSet<>();
    private String title;
    private int createdDaysAgo;
    private int resentlyUpdated;


    public Issue(int id, boolean open, String author, String title, int createdDaysAgo, int resentlyUpdated) {
        this.id = id;
        this.open = open;
        this.author = author;
        this.title = title;
        this.createdDaysAgo = createdDaysAgo;
        this.resentlyUpdated = resentlyUpdated;
    }

    public class CreatedDaysComparator implements Comparator <Issue> {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o2.getCreatedDaysAgo() - o1.getCreatedDaysAgo();
        }
    }

    public class ResentlyUpdatedComparator implements Comparator <Issue> {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o2.getResentlyUpdated() - o1.getResentlyUpdated();
        }
    }
}