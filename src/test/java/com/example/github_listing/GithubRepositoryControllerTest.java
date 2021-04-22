package com.example.github_listing;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.server.ResponseStatusException;

import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class GithubRepositoryControllerTest {

    @Test
    @DisplayName("should throw 404 Not Found exception when github user is not found")
    void getResponseEntity_throwsException_IfUserNotFound() {
        var controller = new GithubRepositoryController();
        var username = "$";
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> controller.getResponseEntity(username));
    }

    @Test
    @DisplayName("should throw 400 Bad request exception when username parameter is blank")
    void getStars_throwsException_IfParameterIsBlank() {
        var controller = new GithubRepositoryController();
        var username = "";
        assertThatExceptionOfType(ResponseStatusException.class)
                .isThrownBy(() -> controller.getResponseEntity(username));
    }

    @Test
    @DisplayName("should calculate the sum of stars in all user's repositories")
    void getStars_returnsSumOfAllStars() {
        // given
        GithubRepositoryController controller = new GithubRepositoryController();
        GithubRepositoryController spy = spy(controller);
        String username = "test";
        GithubRepository[] repositories = new GithubRepository[2];
        repositories[0] = new GithubRepository("test1", 1);
        repositories[1] = new GithubRepository("test2", 2);
        when(spy.getResponseEntity(username)).thenReturn(new ResponseEntity<>(repositories, HttpStatus.OK));
        // then
        assertThat(spy.getStars(username).getBody()).isEqualTo(3);
    }

    @Test
    @DisplayName("should list all user's repositories showing repository name and number of stars")
    void listRepositories_returnsListOfUserRepositories() {
        // given
        GithubRepositoryController controller = new GithubRepositoryController();
        GithubRepositoryController spy = spy(controller);
        String username = "test";
        GithubRepository[] repositories = new GithubRepository[2];
        repositories[0] = new GithubRepository("test1", 1);
        repositories[1] = new GithubRepository("test2", 2);
        when(spy.getResponseEntity(username)).thenReturn(new ResponseEntity<>(repositories, HttpStatus.OK));
        //then
        assertEquals(spy.listRepositories(username).getBody(), repositories);
    }
}