package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.*;

public class IssueManager {

    private final IssueRepository repo;

    public IssueManager(IssueRepository repo) {
        this.repo = repo;
    }

    public void add(Issue issue) {
        repo.save(issue);
    }

    public void closeById(int id) {
        Issue issue = repo.getById(id);
        issue.setOpen(false);
    }

    public void openById(int id) {
        Issue issue = repo.getById(id);
        issue.setOpen(true);
    }

    public Collection<Issue> findOpen() {
        Collection<Issue> issues = repo.getOpen();
        return issues;
    }

    public Collection<Issue> findClosed() {
        Collection<Issue> issues = repo.getClosed();
        return issues;
    }

    public Collection<Issue> findByAuthor(boolean open, String author) {
        Collection<Issue> result = new ArrayList<>();
        if (open) {
            for (Issue issue : repo.getOpen()) {
                if (issue.getAuthor() == author) {
                    result.add(issue);
                }
            }
        } else {
            for (Issue issue : repo.getClosed()) {
                    if (issue.getAuthor() == author) {
                        result.add(issue);
                    }
            }
        }
        return result;
    }

        public Collection<Issue> findByAssignees(boolean open, String assignee) {
        Collection<Issue> result = new ArrayList<>();
        if (open) {
            for (Issue issue : repo.getOpen()) {
                if (issue.getAssignees().contains(assignee)) {
                    result.add(issue);
                }
            }
        } else {
            for (Issue issue : repo.getClosed()) {
                if (issue.getAssignees().contains(assignee)) {
                    result.add(issue);
                }
            }
        }
        return result;
    }

    public Collection<Issue> findByLabel(boolean open, String label) {
        Collection<Issue> result = new ArrayList<>();
        if (open) {
            for (Issue issue : repo.getOpen()) {
                if (issue.getLabels().contains(label)) {
                    result.add(issue);
                }
            }
        } else {
            for (Issue issue : repo.getClosed()) {
                if (issue.getLabels().contains(label)) {
                    result.add(issue);
                }
            }
        }
        return result;
    }

    public Issue[] sortAll(boolean open, Comparator<Issue> comparator) {
        if (open) {
            Collection<Issue> issues = repo.getOpen();
            Issue[] resul = issues.toArray(new Issue[issues.size()]);
            ;
            Arrays.sort(resul, comparator);
            return resul;
        } else {
            Collection<Issue> issues = repo.getClosed();
            Issue[] resul = issues.toArray(new Issue[issues.size()]);
            ;
            Arrays.sort(resul, comparator);
            return resul;
        }
    }

    public Issue[] sortInAuthors(boolean open, String author, Comparator<Issue> comparator) {
        Issue[] issues = findByAuthor(open,author).toArray(new Issue[findByAuthor(open,author).size()]);
        Arrays.sort(issues, comparator);
        return issues;
    }

    public Issue[] sortInAssignees(boolean open, String assignee, Comparator<Issue> comparator) {
        Issue[] issues = findByAssignees(open,assignee).toArray(new Issue[findByAssignees(open,assignee).size()]);
        Arrays.sort(issues, comparator);
        return issues;
    }

}
