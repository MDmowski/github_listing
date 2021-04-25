package com.example.github_listing.githubRepository;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GithubRepositoryTest {

    @Test
    @DisplayName("getStars should throw 404 Not Found exception when github user is not found")
    void getStars_throwsException_IfUserNotFound() {
        var service = new GithubRepositoryService();
        var api = new GithubRepositoryApi(service);
        var username = "$";
        assertThatThrownBy(() -> api.getStars(username)).isInstanceOf(ResponseStatusException.class);
        try {
            api.getStars(username);
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    @DisplayName("listRepositories should throw 404 Not Found exception when github user is not found")
    void githubRepositoryApi_throwsException_IfUserNotFound() {
        var service = new GithubRepositoryService();
        var api = new GithubRepositoryApi(service);
        var username = "$";
        assertThatThrownBy(() -> api.listRepositories(username)).isInstanceOf(ResponseStatusException.class);
        try {
            api.listRepositories(username);
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus(), HttpStatus.NOT_FOUND);
        }
    }

    @Test
    @DisplayName("getStars should throw 400 Bad Request exception when username parameter is blank")
    void getStars_throwsException_IfParameterIsBlank() {
        var service = new GithubRepositoryService();
        var api = new GithubRepositoryApi(service);
        var username = "";
        assertThatThrownBy(() -> api.getStars(username)).isInstanceOf(ResponseStatusException.class);
        try {
            api.getStars(username);
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    @DisplayName("listRepositories should throw 400 Bad Request exception when username parameter is blank")
    void listRepositories_throwsException_IfParameterIsBlank() {
        var service = new GithubRepositoryService();
        var api = new GithubRepositoryApi(service);
        var username = "";
        assertThatThrownBy(() -> api.listRepositories(username)).isInstanceOf(ResponseStatusException.class);
        try {
            api.listRepositories(username);
        } catch (ResponseStatusException e) {
            assertEquals(e.getStatus(), HttpStatus.BAD_REQUEST);
        }
    }

    @Test
    @DisplayName("should calculate the sum of stars in all user's repositories")
    void getStars_returnsSumOfAllStars() {
        // given
        var service = new GithubRepositoryService();
        GithubRepositoryService spyService = spy(service);
        var api = new GithubRepositoryApi(spyService);
        String username = "test";
        GithubRepository[] repositories = new GithubRepository[2];
        repositories[0] = new GithubRepository("test1", 1);
        repositories[1] = new GithubRepository("test2", 2);
        when(spyService.getResponseEntity(username)).thenReturn(new ResponseEntity<>(repositories, HttpStatus.OK));
        // then
        assertThat(api.getStars(username)).isEqualTo(3);
    }

    @Test
    @DisplayName("should list all user's repositories showing repository name and number of stars")
    void listRepositories_returnsListOfUserRepositories() {
        // given
        var service = new GithubRepositoryService();
        GithubRepositoryService spyService = spy(service);
        var api = new GithubRepositoryApi(spyService);
        String username = "test";
        GithubRepository[] repositories = new GithubRepository[2];
        repositories[0] = new GithubRepository("test1", 1);
        repositories[1] = new GithubRepository("test2", 2);
        when(spyService.getResponseEntity(username)).thenReturn(new ResponseEntity<>(repositories, HttpStatus.OK));
        //then
        assertEquals(api.listRepositories(username), repositories);
    }
}

