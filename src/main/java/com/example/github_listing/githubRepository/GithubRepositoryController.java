package com.example.github_listing.githubRepository;

import lombok.AllArgsConstructor;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;


@RestController
@AllArgsConstructor
class GithubRepositoryController {

    private final GithubRepositoryApi api;

    @RequestMapping("/")
    public ResponseEntity<Map<String, String>> index() {
        HashMap<String, String> urls = new HashMap<>();
        urls.put("list", "/list?username={user}");
        urls.put("stars", "/stars?username={user}");
        return new ResponseEntity<>(urls, HttpStatus.OK);
    }

    @GetMapping("/list")
    ResponseEntity<GithubRepository[]> listRepositories(@RequestParam(value = "username") String username) {
        return new ResponseEntity<>(api.listRepositories(username), HttpStatus.OK);
    }

    @GetMapping("/stars")
    ResponseEntity<Integer> getStars(@RequestParam String username) {
        return new ResponseEntity<>(api.getStars(username), HttpStatus.OK);

    }


}
