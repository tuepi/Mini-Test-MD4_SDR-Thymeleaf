package repository;

import model.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    List<Post> findAllByTitleContaining(String title);

    List<Post> findAllByOrderByLikes();

    @Query(value = "SELECT * FROM post p ORDER BY p.createAt DESC LIMIT 4", nativeQuery = true)
    List<Post> findFourOfNewPost();
}
