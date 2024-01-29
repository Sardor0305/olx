package uz.pdp.olx.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import uz.pdp.olx.enitiy.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long> {
}
