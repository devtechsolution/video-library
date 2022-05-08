package org.as.devtechsolution.service;


import lombok.RequiredArgsConstructor;
import org.as.devtechsolution.domain.Video;
import org.as.devtechsolution.repository.VideoRepository;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

@Service
@RequiredArgsConstructor
public class VideoService {

    private final S3Service s3Service;
    private final VideoRepository videoRepository;

    public void uploadVideo(MultipartFile multipartFile) {
        // Upload file to AWS s3
        String videoUrl = s3Service.uploadFile(multipartFile);
        // Save video Data to Database
        var video = new Video();
        video.setVideoUrl(videoUrl);

        videoRepository.save(video);
    }
}