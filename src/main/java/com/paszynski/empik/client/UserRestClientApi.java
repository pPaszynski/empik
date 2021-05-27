package com.paszynski.empik.client;

import com.paszynski.empik.client.dto.GithubUserDto;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

public interface UserRestClientApi {

  @GetMapping("/users/{login")
  public ResponseEntity<GithubUserDto> getUserByLogin(@PathVariable String login);
}
