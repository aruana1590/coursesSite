package kz.aruana.coursesSite.controllers;

import kz.aruana.coursesSite.dto.request.VideoDtoRequest;
import kz.aruana.coursesSite.dto.request.VideoDtoResponse;
import kz.aruana.coursesSite.entities.Videos;
import kz.aruana.coursesSite.service.VideosService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/videos")
@RequiredArgsConstructor

public class VideoController {
    private final VideosService videosService;

    @PostMapping("/create")
    public ResponseEntity<Videos> create(@Valid @RequestBody VideoDtoRequest videoDtoRequest) {
        Videos video = videosService.create(videoDtoRequest);
        return new ResponseEntity<>(video, HttpStatus.CREATED);
    }
    @PostMapping("/update/{name}")
    public ResponseEntity<Videos> update(@Valid @RequestBody VideoDtoRequest videoDtoRequest , @PathVariable
    String name) {
        Videos video = videosService.update(videoDtoRequest, name);
        return new ResponseEntity<>(video, HttpStatus.OK);
    }
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<HttpStatus> delete(@PathVariable(name = "id") Long id) {
        videosService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @GetMapping("/get-courses/{keyword}")
public ResponseEntity<List<VideoDtoResponse>> getByKeyword(@PathVariable(name="keyword") String keyword){
        Optional<List<Videos>> videos = videosService.getCoursesByKeyword(keyword);
    }
    }
