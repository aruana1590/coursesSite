package kz.aruana.coursesSite.service;

import kz.aruana.coursesSite.dto.request.VideoDtoRequest;
import kz.aruana.coursesSite.entities.Videos;
import kz.aruana.coursesSite.exceptions.AlreadyExists;
import kz.aruana.coursesSite.exceptions.DeleteException;
import kz.aruana.coursesSite.exceptions.NotFoundException;
import kz.aruana.coursesSite.exceptions.RecordNotCreatedException;
import kz.aruana.coursesSite.repositories.VideosRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@Log4j2
@RequiredArgsConstructor
public class VideosServiceImpl implements VideosService {

    private final VideosRepository videosRepository;
    private final UsersService usersService;

    @Override
    public Optional<Videos> getById(Long id) {
        return videosRepository.findById(id);
    }

    @Override
    public Videos getByIdThrowException(Long id) {
        return this.getById(id).orElseThrow(() -> new NotFoundException("There is no courses with such id"));
    }

    @Override
    public Optional<Videos> getByCourseName(String courseName) {
        return videosRepository.findByCourseName(courseName);
    }

    @Override
    public Videos getByCourseNameThrowException(String courseName) {
        return this.getByCourseName(courseName).orElseThrow(() -> new NotFoundException("There is no courses with such name"));
    }

    public Videos save(Videos video) {
        return videosRepository.save(video);
    }

    @Override
    public Videos create(VideoDtoRequest videoDtoRequest) {
        Videos video = new Videos();

        Optional<Videos> courseName = this.getByCourseName(videoDtoRequest.getCourseName());
        if (courseName.isPresent()) throw new AlreadyExists("Such course already exists");
        try {
            video.setCourseName(videoDtoRequest.getCourseName());
            video.setSection(videoDtoRequest.getSection());
            video.setAuthor(videoDtoRequest.getAuthor());
            video.setCourseInfo(videoDtoRequest.getCourseInfo());
            video.setPrice(videoDtoRequest.getPrice());
            video.setSold(videoDtoRequest.getSold());
            video.setRating(videoDtoRequest.getRating());
            video.setReviews(videoDtoRequest.getReviews());
            return this.save(video);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new RecordNotCreatedException("Your record was not created");
        }
    }

    @Override
    public Videos update(VideoDtoRequest videoDtoRequest, String courseName) {
        Videos video = this.getByCourseNameThrowException(courseName);
        try {
            video.setCourseName(videoDtoRequest.getCourseName());
            video.setSection(videoDtoRequest.getSection());
            video.setAuthor(videoDtoRequest.getAuthor());
            video.setCourseInfo(videoDtoRequest.getCourseInfo());
            video.setPrice(videoDtoRequest.getPrice());
            video.setSold(videoDtoRequest.getSold());
            video.setRating(videoDtoRequest.getRating());
            video.setReviews(videoDtoRequest.getReviews());
            return this.save(video);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new NotFoundException("Обновление невозможно!");
        }
    }

    @Override
    public void delete(Long id) {
        Videos video = this.getByIdThrowException(id);
        try {
            videosRepository.delete(video);
        } catch (Exception e) {
            log.error(e.getMessage());
            throw new DeleteException("Произошла ошибка, запись не удалена!");
        }
    }
    public Optional<List<Videos>> getCoursesByKeyword(String keyword) {
        return videosRepository.findByKeyword(keyword);
    }
    public List <Videos> getCoursesByKeywordThrowExeption(String keyword){
        return this.getCoursesByKeyword(keyword).orElseThrow(()-> new NotFoundException("There is no courses with such word"));
    }
}




