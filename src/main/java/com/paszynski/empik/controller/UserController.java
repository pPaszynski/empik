package com.paszynski.empik.controller;

import com.paszynski.empik.client.UserRestClientApi;
import com.paszynski.empik.client.dto.GithubUserDto;
import com.paszynski.empik.domain.User;
import com.paszynski.empik.exception.UserNotFoundException;
import com.paszynski.empik.service.RequestCounterService;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Optional;

import static com.paszynski.empik.util.UserConverter.convertToUser;

@RestController
public class UserController {

  private final UserRestClientApi userRestClientApi;
  private final RequestCounterService counterService;

  public UserController(UserRestClientApi userRestClient, RequestCounterService counterService) {
    this.userRestClientApi = userRestClient;
    this.counterService = counterService;
  }

  @GetMapping(value = "users/{login}", produces = MediaType.APPLICATION_JSON_VALUE)
  public User getUser(@PathVariable String login) {
    GithubUserDto githubUserDTO = getGithubUserDTO(login);
    User user = convertToUser(githubUserDTO);
    counterService.incrementCounter(login);
    return user;
  }

  private GithubUserDto getGithubUserDTO(String login) {
    return Optional.ofNullable(userRestClientApi.getUserByLogin(login).getBody())
        .orElseThrow(() -> new UserNotFoundException(login));
  }
}
