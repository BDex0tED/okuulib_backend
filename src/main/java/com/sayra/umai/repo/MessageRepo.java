package com.sayra.umai.repo;

import com.sayra.umai.model.entity.work.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
//    List<Message> findByChatSessionIdOrderByCreated_atAsc(Long sessionId);

}
