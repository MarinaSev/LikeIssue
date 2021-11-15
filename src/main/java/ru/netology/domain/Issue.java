package ru.netology.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Collection;
import java.util.Comparator;
import java.util.HashSet;
import java.util.Objects;

@NoArgsConstructor
@AllArgsConstructor
@Data
public class Issue implements Comparable <Issue> {
    private int id;
    private boolean open;
    private String author;
//    private Label labels;
//    private HashSet<String> assignees;
    private String title;
    private int createdDaysAgo;
    private int resentlyUpdated;

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Issue issue = (Issue) o;
        return id == issue.id && open == issue.open && createdDaysAgo == issue.createdDaysAgo && resentlyUpdated == issue.resentlyUpdated && Objects.equals(author, issue.author) && Objects.equals(title, issue.title);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, open, author, title, createdDaysAgo, resentlyUpdated);
    }

    @Override
    public int compareTo(Issue o) {
        Issue p = (Issue) o;
        return resentlyUpdated - o.resentlyUpdated;
    }


    public class CreatedDaysComparator implements Comparator <Issue> {
        @Override
        public int compare(Issue o1, Issue o2) {
            return o1.getCreatedDaysAgo() - o2.getCreatedDaysAgo();
        }
    }
}