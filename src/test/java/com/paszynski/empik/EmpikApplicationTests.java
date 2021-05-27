package com.paszynski.empik;

import com.paszynski.empik.respository.RequestCounterRepository;
import org.hamcrest.Matchers;
import org.junit.jupiter.api.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.ExpectedCount;
import org.springframework.test.web.client.MockRestServiceServer;
import org.springframework.test.web.client.match.MockRestRequestMatchers;
import org.springframework.test.web.client.response.MockRestResponseCreators;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultHandlers;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.web.client.RestTemplate;

import static com.paszynski.empik.data.EmpikIntegrationData.GITHUB_TEST_USER;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureMockMvc
class EmpikApplicationTests {

  @Autowired
  private MockMvc mockMvc;

  @Autowired
  private RequestCounterRepository repository;

  @Autowired
  private RestTemplate restTemplate;

  @Value("${github.api.url}")
  String url;

  private MockRestServiceServer mockServer;

  @BeforeEach
  void setUp() {
    mockServer = MockRestServiceServer.createServer(restTemplate);
  }

  @Test
  void shouldReturnCorrectDataAndSaveUserInDB() throws Exception {
    this.mockServer.expect(MockRestRequestMatchers.requestTo(url + "users/test"))
        .andRespond(MockRestResponseCreators.withSuccess(GITHUB_TEST_USER, MediaType.APPLICATION_JSON));
    sendCorrectRequest("test");
    Assertions.assertEquals(1, repository.findByLogin("test").getRequestCount());
  }

  @Test
  void shouldCorrectAddRequestCount() throws Exception {
    this.mockServer.expect(ExpectedCount.twice(), MockRestRequestMatchers.requestTo(url + "users/test2"))
        .andRespond(MockRestResponseCreators.withSuccess(GITHUB_TEST_USER, MediaType.APPLICATION_JSON));
    sendCorrectRequest("test2");
    sendCorrectRequest("test2");
    Assertions.assertEquals(2, repository.findByLogin("test2").getRequestCount());
  }

  @Test
  void shouldReturn404() throws Exception {
    this.mockServer.expect(ExpectedCount.twice(), MockRestRequestMatchers.requestTo(url + "users/test3"))
        .andRespond(MockRestResponseCreators.withStatus(HttpStatus.NOT_FOUND));

    this.mockMvc.perform(MockMvcRequestBuilders.get("/users/" + "test3")).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().is(404));
  }

  private void sendCorrectRequest(String login) throws Exception {
    this.mockMvc.perform(MockMvcRequestBuilders.get("/users/" + login)).andDo(MockMvcResultHandlers.print())
        .andExpect(MockMvcResultMatchers.status().is(200))
        .andExpect(MockMvcResultMatchers.jsonPath("$.calculations", Matchers.is(1.4)));
  }
}
