package com.thong.service.impl;

import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.Arrays;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;

import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.event.EventListener;
import org.springframework.core.io.ClassPathResource;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.google.api.services.storage.Storage;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.Blob;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.cloud.storage.Bucket;
import com.google.cloud.storage.StorageOptions;
import com.thong.converter.ImageConverter;
import com.thong.converter.PostConverter;
import com.thong.dto.ImageDTO;
import com.thong.entity.Image;
import com.thong.entity.Post;
import com.thong.exception.PostNotFoundException;
import com.thong.repository.ImageRepository;
import com.thong.repository.PostRepository;
import com.thong.service.IImageService;

@Service
public class ImageService implements IImageService {


	@Autowired
	private ImageRepository imageRepository;

	@Autowired
	private PostRepository postRepository;

	@Autowired
	private ImageConverter imageConverter;

//	@EventListener
//	public void init(ApplicationReadyEvent event) {
//		// initialize Firebase
//		try {
//			ClassPathResource serviceAccount = new ClassPathResource("firebase.json");
//			FirebaseOptions options = FirebaseOptions.builder()
//					.setCredentials(GoogleCredentials.fromStream(serviceAccount.getInputStream()))
//					.setStorageBucket(bucketName).build();
//			FirebaseApp.initializeApp(options);
//		} catch (Exception ex) {
//			ex.printStackTrace();
//		}
//	}

	@Override
	public void save(int idPost, MultipartFile file) throws IOException {
//
//		Bucket bucket = StorageClient.getInstance().bucket();
//		String name = file.getName() + UUID.randomUUID();
//		Blob b= bucket.create(name, file.getInputStream(), file.getContentType());
//		String uid = "some-uid";
//		try {
//			String customToken = FirebaseAuth.getInstance().createCustomToken(uid);
//		} catch (FirebaseAuthException e) {
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		}
//	//	Blob b = bucket.get(name);
//		Image i = new Image();
//		i.setLink(b.getSelfLink());
//		imageRepository.save(i);
	}

	@Override
	public List<ImageDTO> findByPostId(int idPost) throws PostNotFoundException {
		// TODO Auto-generated method stub
		Post p = postRepository.findOneById(idPost).orElseThrow(() -> new PostNotFoundException("id not exist"));
		List<Image> i = imageRepository.findImageByPost(p);

		return i.stream().map(imageConverter::toDTO).collect(Collectors.toList());
	}

	@Transactional
	@Override
	public void delete(int[] id) {
		for (int i : id) {
			Image image = imageRepository.findOneById(i);
			Path p = Paths.get("uploads", image.getLink());
			try {
				Files.delete(p);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				System.out.println(e);
			}
			imageRepository.deleteById(i);
		}

	}

}
