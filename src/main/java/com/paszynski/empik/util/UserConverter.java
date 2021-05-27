package com.paszynski.empik.util;

import com.paszynski.empik.client.dto.GithubUserDto;
import com.paszynski.empik.domain.User;

import java.math.BigDecimal;
import java.math.MathContext;

public class UserConverter {

  public static User convertToUser(GithubUserDto githubUserDto) {
    return User.builder()
            .id(githubUserDto.getId())
            .login(githubUserDto.getLogin())
            .name(githubUserDto.getName())
            .type(githubUserDto.getType())
            .avatarUrl(githubUserDto.getAvatarUrl())
            .createdAt(githubUserDto.getCreatedAt())
            .calculations(createCalculations(githubUserDto))
            .build();
  }

  private static BigDecimal createCalculations(GithubUserDto githubUserDto) {
    try {
      BigDecimal followers = new BigDecimal(githubUserDto.getFollowers());
      BigDecimal quotient = new BigDecimal(6).divide(followers, MathContext.DECIMAL128);
      BigDecimal sum = new BigDecimal(2 + githubUserDto.getPublicRepos());
      System.out.println("1 " + quotient.multiply(sum));
      return quotient.multiply(sum);
    } catch (Exception e) {
      return null;
    }
  }
}
