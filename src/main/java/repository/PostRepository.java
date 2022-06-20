package repository;

import model.Post;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

//@Repository
public interface PostRepository extends JpaRepository<Post, Long> {
    Page<Post> findAllByTitleContaining(String title, Pageable pageable);

    Page<Post> findAllByOrderByLikes(Pageable pageable);

    Page<Post> findAllByOrderByCreateAt(Pageable pageable);

    @Query(value = "SELECT * FROM post p ORDER BY p.createAt DESC LIMIT 4", nativeQuery = true)
    List<Post> findFourOfNewPost();

    @Query(value = "select * from post where title like ?1 and (createAt between ?2 and ?3)", nativeQuery = true)
    Page<Post> findAllByTitleContainingAndCreateAtBetween(String title, String begin, String end, Pageable pageable);

    @Query(value = "select * from post where createAt between ?1 and ?2", nativeQuery = true)
    Page<Post> findAllByCreateAtBetween(String begin, String end, Pageable pageable);
}
