package ru.netology.manager;

import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

public class IssueManager {

    private IssueRepository repo;

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


//    public Collection<Issue> findByLabel(boolean open, String label) {
//        Collection<Issue> result = new ArrayList<>();
//        if (open) {
//            Collection<Issue> issues = repo.getOpen(open);
//            for (Issue issue : issues) {
//                if (issue.getLabels().getComponent() ) {
//                    result.add(issue);
//                }
//            }
//        } else {
//            Collection<Issue> issues = repo.getClosed(open);
//            for (Issue issue : issues) {
//                if (issue.getAuthor() == author) {
//                    result.add(issue);
//                }
//            }
//        }
//        return result;
//    }

//    public Collection<Issue> findByAssignes(boolean open, String assignee) {
//        Collection<Issue> result = new ArrayList<>();
//        int lenght = 1;
//        int index = 0;
//        if (open) {
//            Collection<Issue> issues = repo.getOpen(open);
//            for (Issue issue : issues) {
//                if (issue.getAssignees().contains(assignee)) {
//                    Issue[] tmp = new Issue[lenght];
//                    System.arraycopy(result, 0, tmp, 0, lenght - 1);
//                    tmp[index] = issue;
//                    lenght++;
//                    index++;
//                }
//                Collection<Issue> tmp = result;
//            }
//        } else {
//            Collection<Issue> issues = repo.getClosed(open);
//            for (Issue issue : issues) {
//                if (issue.getAssignees().contains(assignee)) {
//                    Issue[] tmp = new Issue[lenght];
//                    System.arraycopy(result, 0, tmp, 0, lenght - 1);
//                    tmp[index] = issue;
//                    lenght++;
//                    index++;
//                }
//                Collection<Issue> tmp = result;
//            }
//        }
//        return result;
//    }


}
