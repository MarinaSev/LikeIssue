package ru.netology.repository;

import ru.netology.domain.Issue;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueRepository {
    private final Collection<Issue> issues = new ArrayList();

    public void save (Issue issue) {
        issues.add(issue);
    }

    public Collection<Issue> getAll() {
        return issues;
    }

    public Issue getById(int id) {
        for (Issue issue : issues) {
            if (issue.getId() == id) {
                return issue;
            }
        }
        return null;
    }

    public Collection<Issue> getOpen() {
        Collection<Issue> result = new ArrayList<>();
            for (Issue issue : getAll()) {
                if (issue.isOpen()) {
                    result.add(issue);
                }
        }
        return result;
    }

    public Collection<Issue> getClosed() {
        Collection<Issue> result = new ArrayList<>();
        for (Issue issue : getAll()) {
            if (!issue.isOpen()) {
                result.add(issue);
            }
        }
        return result;
    }

//    public void removeById(int id) {
//        issues.removeIf(element -> element.getId() == id);
//    }

}
