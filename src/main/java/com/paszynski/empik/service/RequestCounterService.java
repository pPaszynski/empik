package com.paszynski.empik.service;

import com.paszynski.empik.domain.RequestCounter;
import com.paszynski.empik.respository.RequestCounterRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RequestCounterService {

  private RequestCounterRepository repository;

  public RequestCounterService(RequestCounterRepository repository) {
    this.repository = repository;
  }

  public List<RequestCounter> getRequestCounters() {
    return repository.findAll();
  }

  public void incrementCounter(String login) {
    RequestCounter counter = Optional.ofNullable(repository.findByLogin(login)).orElse(new RequestCounter(login, 0L));
    counter.setRequestCount(counter.getRequestCount() + 1);
    repository.save(counter);
  }
}
