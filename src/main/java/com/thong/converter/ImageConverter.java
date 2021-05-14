package com.thong.converter;

import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.dto.ImageDTO;
import com.thong.entity.Image;
import com.thong.exception.PostNotFoundException;
import com.thong.repository.PostRepository;

@Component
public class ImageConverter implements IConverter<Image, ImageDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private PostRepository postRepository;
	
	@Override
	public Image toEntity(ImageDTO o) throws Exception {
		// TODO Auto-generated method stub
		Image i = modelMapper.map(o, Image.class);
		i.setPost(postRepository.findOneById(o.getIdPost()).orElseThrow(()-> new PostNotFoundException("id post not found")));
		return i;
	}

	@Override
	public ImageDTO toDTO(Image o) {
		// TODO Auto-generated method stub
		ImageDTO i = modelMapper.map(o, ImageDTO.class);
		i.setIdPost(o.getPost().getId());
		return i;
	}

}
