package net.javaguides.springboot.repository;

import net.javaguides.springboot.entity.Post;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface PostRepository extends JpaRepository<Post, Long> {
    Optional<Post> findByUrl(String url);

    @Query("SELECT p FROM Post p " +
            "WHERE p.title ILIKE CONCAT('%', :query, '%')" +
            "OR p.shortDescription ILIKE CONCAT('%', :query, '%')")
    List<Post> searchPosts(String query);

    @Query(value = "select * from posts p where p.created_by = :id",
            nativeQuery = true)
    List<Post> findPostsByUser(Long id);
}
