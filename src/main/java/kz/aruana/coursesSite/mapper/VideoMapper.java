package kz.aruana.coursesSite.mapper;

import kz.aruana.coursesSite.dto.request.VideoDtoRequest;
import kz.aruana.coursesSite.dto.request.VideoDtoResponse;
import kz.aruana.coursesSite.entities.Videos;

public class VideoMapper {
    public static VideoDtoResponse videoToDto(Videos video){
        VideoDtoResponse videoDtoResponse=new VideoDtoResponse();

        videoDtoResponse.setCourseName(video.getCourseName());
        videoDtoResponse.setSection(video.getSection());
        videoDtoResponse.setAuthor(video.getAuthor());
        videoDtoResponse.setCourseInfo(video.getCourseInfo());
        videoDtoResponse.setPrice(video.getPrice());
        videoDtoResponse.setRating(video.getRating());
        videoDtoResponse.setSold(video.getSold());
        videoDtoResponse.setReviews(video.getReviews());
        return videoDtoResponse;


    }
}
