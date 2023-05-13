package kz.aruana.coursesSite.repositories;

import kz.aruana.coursesSite.entities.Videos;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VideosRepository extends JpaRepository<Videos, Long> {

    Optional<Videos> findByCourseName(String courseName);

    Optional<List<Videos>>findByKeyword(String keyword);
}
