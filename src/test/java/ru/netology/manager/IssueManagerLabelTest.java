package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.domain.Label;
import ru.netology.repository.IssueRepository;

import java.util.*;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;

class IssueManagerLabelTest {

    IssueManager manager = new IssueManager(new IssueRepository());

    public Issue[] arrayAll(Collection<Issue> issue) {
        return issue.toArray(new Issue[issue.size()]);
    }

    Set<String> assignees1 = new HashSet<>();

    Set<String> label1 = new HashSet<>();
    Set<String> label2 = new HashSet<>();
    Set<String> label3 = new LinkedHashSet<>();

    private Issue first = new Issue(1, true, "Master", label1, assignees1, "xxx", 5, 16);
    private Issue second = new Issue(2, false, "Butterfly", label2, assignees1, "yyy", 4, 96);
    private Issue third = new Issue(3, true, "Worker", label2, assignees1, "zzz", 3, 3);
    private Issue forth = new Issue(4, true, "Master", label3, assignees1, "aaa", 1, 18);
    private Issue fifth = new Issue(5, false, "Worker", label1, assignees1, "bbb", 0, 1);

    @BeforeEach
    public void setUp(){
        Collection<Issue> issue = new ArrayList<>();

        label1.add("Jupiter");
        label1.add("parameterized tests");
        label1.add("Maven Surefire");
        label2.add("Jupiter");
        label2.add("enhancement");
        label3.add("Kotlin");
        label3.add("reporing");

        assignees1.add("Butterfly");

        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @Test
    public void shouldFindByLabelIfOneInOpen() {
        Issue[] expected = {first};
        Issue[] actual = arrayAll(manager.findByLabel(true, "Maven Surefire"));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByLabelIfFewInClosed() {
        Issue[] expected = {second, fifth};
        Issue[] actual = arrayAll(manager.findByLabel(false, "Jupiter"));

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByLabelIfNooneInClosed() {
        Issue[] expected = {};
        Issue[] actual = arrayAll(manager.findByLabel(false, "Butterfly"));

        assertArrayEquals(expected, actual);
    }

}