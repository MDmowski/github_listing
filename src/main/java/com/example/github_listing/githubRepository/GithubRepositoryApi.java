package com.example.github_listing.githubRepository;

import infrastructure.ModuleApi;
import lombok.AllArgsConstructor;

@AllArgsConstructor
@ModuleApi
public class GithubRepositoryApi {
    private final GithubRepositoryService service;

    public int getStars(String username) {
        return service.getStars(username);
    }

    public GithubRepository[] listRepositories(String username) {
        return service.listRepositories(username);
    }
}
