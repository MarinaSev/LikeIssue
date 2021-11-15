package ru.netology.manager;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import ru.netology.domain.Issue;
import ru.netology.repository.IssueRepository;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class IssueManagerTest {

    IssueManager manager = new IssueManager(new IssueRepository());

    public Issue[] arrayAll(Collection<Issue> issue) {
        return issue.toArray(new Issue[issue.size()]);
    }

    private Issue first = new Issue(1, true, "Master", "xxx", 5, 16);
    private Issue second = new Issue(2, false, "Butterfly", "yyy", 4, 96);
    private Issue third = new Issue(3, true, "Worker", "zzz", 3, 3);
    private Issue forth = new Issue(4, true, "Master", "aaa", 1, 18);
    private Issue fifth = new Issue(5, false, "Worker", "bbb", 0, 1);

    @BeforeEach
    public void setUp(){
        Collection<Issue> issue = new ArrayList<>();
        manager.add(first);
        manager.add(second);
        manager.add(third);
        manager.add(forth);
        manager.add(fifth);
    }

    @Test
    public void shouldOpenById() {

        manager.openById(5);

        Issue[] expected = {first, third, forth, fifth};
        Issue[] actual = arrayAll(manager.findOpen());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldCloseById() {

        manager.closeById(1);

        Issue[] expected = {first, second, fifth};
        Issue[] actual = arrayAll(manager.findClosed());

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindOpen() {

        Issue[] expected = {first, third, forth};
        Issue[] actual = arrayAll(manager.findOpen());

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindClosed() {

        Issue[] expected = {second, fifth};
        Issue[] actual = arrayAll(manager.findClosed());

        assertArrayEquals(expected, actual);

    }

    @Test
    public void shouldFindByAuthorInOpen() {
        Collection<Issue> authors = manager.findByAuthor(true, "Master");

        Issue[] expected = {first, forth};
        Issue[] actual = arrayAll(authors);

        assertArrayEquals(expected, actual);
    }

    @Test
    public void shouldFindByAuthorInClosed() {
        Collection<Issue> authors = manager.findByAuthor(false, "Master");

        Issue[] expected = {};
        Issue[] actual = arrayAll(authors);

        assertArrayEquals(expected, actual);
    }
}