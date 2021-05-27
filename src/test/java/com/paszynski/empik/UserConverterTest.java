package com.paszynski.empik;

import com.paszynski.empik.client.dto.GithubUserDto;
import com.paszynski.empik.domain.User;
import com.paszynski.empik.util.UserConverter;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

class UserConverterTest {

  @Test
  void shouldCorrectlyConvertUser() {
    GithubUserDto githubUserDto = createGithubUserDto();
    User user = UserConverter.convertToUser(githubUserDto);
    Assertions.assertEquals(createExpectedUserDto(), user);
  }

  private User createExpectedUserDto() {
    return User.builder().id(583231L).login("octocat").name("The Octocat").type("User")
        .avatarUrl("https://avatars.githubusercontent.com/u/583231?v=4").createdAt("2011-01-25T18:44:36Z")
        .calculations(new BigDecimal(60)).build();
  }

  public static GithubUserDto createGithubUserDto() {
    return GithubUserDto.builder().id(583231L).login("octocat").name("The Octocat").type("User")
        .avatarUrl("https://avatars.githubusercontent.com/u/583231?v=4").createdAt("2011-01-25T18:44:36Z")
        .publicRepos(8L).followers(1L).build();
  }
}
