package com.paszynski.empik.client.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.*;

@Builder
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class GithubUserDto {

  private Long id;
  private String login;
  private String name;
  private String type;
  @JsonProperty("avatar_url")
  private String avatarUrl;
  @JsonProperty(required = true)
  private Long followers;
  @JsonProperty("created_at")
  private String createdAt;
  @JsonProperty(value = "public_repos", required = true)
  private Long publicRepos;
}
