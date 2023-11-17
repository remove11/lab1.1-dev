package kth.alex.demo.repository;
import kth.alex.demo.entity.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MessageRepository extends JpaRepository<Message, String> {
    @Query("select m from Message m join fetch m.receiver join fetch m.sender where m.id = ?1")
    public Optional<Message> findById(String id);

    @Query("select m from Message m join fetch m.receiver join fetch m.sender where (m.receiver.socialNr = ?1 and m.sender.socialNr = ?2) or (m.sender.socialNr = ?1 and m.receiver.socialNr = ?2) order by m.createdAt asc")
    public List<Message> findConversation(String p1Id, String p2Id);
    public List<Message> findListById(String id);
}
