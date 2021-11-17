package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IssueManagerAssigneeTest {

    IssueManager manager = new IssueManager(new IssueRepository());

    public Issue[] arrayAll(Collection<Issue> issue) {
        return issue.toArray(new Issue[issue.size()]);
    }

    Set<String> assignees1 = new HashSet<>();
    Set<String> assignees2 = new HashSet<>();
    Set<String> assignees3 = new HashSet<>();

    Set<String> label1 = new HashSet<>();

    private Issue first = new Issue(1, true, "Master", label1, assignees1, "xxx", 5, 16);
    private Issue second = new Issue(2, false, "Butterfly", label1, assignees3, "yyy", 4, 96);
    private Issue third = new Issue(3, true, "Worker", label1, assignees2, "zzz", 3, 3);
    private Issue forth = new Issue(4, true, "Master", label1, assignees2, "aaa", 1, 18);
    private Issue fifth = new Issue(5, false, "Worker", label1, assignees2, "bbb", 0, 1);
    private Issue sixth = new Issue(6, false, "Worker", label1, assignees3, "yyy", 0, 1);

    @BeforeEach
    public void setUp(){
        Collection<Issue> issue = new ArrayList<>();

        label1.add("Jupiter");

        assignees1.add("Butterfly");
        assignees1.add("Worker");
        assignees2.add("Butterfly");
        assignees3.add("Master");

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @Test
    public void shouldFindByAssigneeIfOneInClosed() {
        Issue[] expected = {second};
        Issue[] actual = arrayAll(manager.findByAssignees(false, "Master"));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByAssigneeIfFewInOpen() {
        Issue[] expected = {first, third, forth};
        Issue[] actual = arrayAll(manager.findByAssignees(true, "Butterfly"));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSorSortInAssigneesByUpdatedIfOpen() {

        Issue[] expected = {third, first, forth};
        Issue[] actual = manager.sortInAssignees(true, "Butterfly", Comparator.comparing(Issue::getResentlyUpdated));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldSorSortInAssigneesByCreatedIfClosed() {

        manager.add(sixth);

        Issue[] expected = {sixth, second};
        Issue[] actual = manager.sortInAssignees(false, "Master", Comparator.comparing(Issue::getCreatedDaysAgo));

        assertArrayEquals(expected, actual);
    }

}