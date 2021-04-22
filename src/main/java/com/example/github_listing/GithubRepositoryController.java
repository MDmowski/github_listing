package com.example.github_listing;

import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;


@RestController
@Validated
public class GithubRepositoryController {
    @GetMapping("/list")
    public ResponseEntity<GithubRepository[]> listRepositories(@RequestParam(value = "username") String username) {
        return getResponseEntity(username);
    }

    @GetMapping("/stars")
    public ResponseEntity<Integer> getStars(@RequestParam String username) {
        ResponseEntity<GithubRepository[]> entity = getResponseEntity(username);
        int sum = 0;
        for (GithubRepository repo : Objects.requireNonNull(entity.getBody())) {
            sum += repo.getStargazers_count();
        }
        return new ResponseEntity<>(sum, HttpStatus.OK);

    }

    ResponseEntity<GithubRepository[]> getResponseEntity(@RequestParam("username") String username) {
        final String uri = String.format("https://api.github.com/users/%s/repos", username);
        RestTemplate restTemplate = new RestTemplate();
        if (username.isBlank())
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        try {
            return restTemplate.getForEntity(uri, GithubRepository[].class);
        } catch (final HttpClientErrorException e) {
            throw new ResponseStatusException(
                    e.getStatusCode()
            );
        }
    }
}