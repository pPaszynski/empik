package com.paszynski.empik;

import com.paszynski.empik.client.UserRestClient;
import com.paszynski.empik.client.UserRestClientApi;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
@ComponentScan("com.paszynski.empik")
public class EmpikConfiguration {

  @Value("${github.api.url}")
  private String url;

  @Bean
  RestTemplate getRestTemplate() {
    return new RestTemplateBuilder().build();
  }

  @Bean
  UserRestClientApi getUserRestClient() {
    return new UserRestClient(getRestTemplate(), url);
  }
}
