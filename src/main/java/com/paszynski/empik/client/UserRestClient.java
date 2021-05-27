package com.paszynski.empik.client;

import com.paszynski.empik.client.dto.GithubUserDto;
import com.paszynski.empik.exception.ClientException;
import com.paszynski.empik.exception.UserNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.client.RestTemplate;

public class UserRestClient implements UserRestClientApi {

  private final RestTemplate template;
  private final String url;

  public UserRestClient(RestTemplate template, String url) {
    this.template = template;
    this.url = url;
  }

  @Override
  public ResponseEntity<GithubUserDto> getUserByLogin(String login) {
    try {
      return template.getForEntity(url + "/users/" + login, GithubUserDto.class);
    } catch (HttpClientErrorException.NotFound exception) {
      throw new UserNotFoundException(login);
    } catch (Exception exception) {
      throw new ClientException();
    }
  }
}
