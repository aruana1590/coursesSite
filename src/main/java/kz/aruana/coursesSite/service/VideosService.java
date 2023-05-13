package kz.aruana.coursesSite.service;

import kz.aruana.coursesSite.dto.request.VideoDtoRequest;
import kz.aruana.coursesSite.entities.Videos;

import java.util.List;
import java.util.Optional;

public interface VideosService {
    //  public VideosServiceImpl(VideosRepository videosRepository) {
      //    this.videosRepository = videosRepository;
    //  }
    Optional<Videos> getById(Long id);

    Videos getByIdThrowException(Long id);

    Optional<Videos> getByCourseName(String courseName);

    Videos getByCourseNameThrowException(String courseName);

    Videos save(Videos video);

    Videos create(VideoDtoRequest videoDtoRequest);


    Videos update(VideoDtoRequest videoDtoRequest, String courseName);

    void delete(Long id);

    public Optional<List<Videos>> getCoursesByKeyword(String keyword);
    public List<Videos> getCoursesByKeywordThrowExeption(String keyword);
}
