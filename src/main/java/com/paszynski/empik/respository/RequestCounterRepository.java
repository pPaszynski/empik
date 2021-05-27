package com.paszynski.empik.respository;

import com.paszynski.empik.domain.RequestCounter;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RequestCounterRepository extends JpaRepository<RequestCounter, String> {

  RequestCounter findByLogin(String login);
}
