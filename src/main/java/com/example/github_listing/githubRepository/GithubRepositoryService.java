package com.example.github_listing.githubRepository;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.server.ResponseStatusException;

import java.util.Objects;

@Service
class GithubRepositoryService {

    int getStars(String username) {
        ResponseEntity<GithubRepository[]> entity = getResponseEntity(username);
        int sum = 0;
        for (GithubRepository repo : Objects.requireNonNull(entity.getBody())) {
            sum += repo.getStargazers_count();
        }
        return sum;
    }

    GithubRepository[] listRepositories(String username) {
        return getResponseEntity(username).getBody();
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
