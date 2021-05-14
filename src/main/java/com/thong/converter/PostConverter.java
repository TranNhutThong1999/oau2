package com.thong.converter;

import java.util.stream.Collectors;

import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.thong.Security.CustomUserDetail;
import com.thong.dto.AddressDTO;
import com.thong.dto.CommentDTO;
import com.thong.dto.DetailPostDTO;
import com.thong.dto.DistrictDTO;
import com.thong.dto.ImageDTO;
import com.thong.dto.KindOfRoomDTO;
import com.thong.dto.PostDTO;
import com.thong.dto.UserDTO;
import com.thong.entity.Address;
import com.thong.entity.DetailPost;
import com.thong.entity.District;
import com.thong.entity.KindOfRoom;
import com.thong.entity.Post;
import com.thong.exception.UserNotFoundException;
import com.thong.service.IKindOfRoomService;
import com.thong.service.IUserService;

@Component
public class PostConverter implements IConverter<Post, PostDTO> {
	@Autowired
	private ModelMapper modelMapper;
	
	@Autowired
	private IKindOfRoomService kindOfRoomService;
	
	@Autowired
	private CustomUserDetail customUserDetail;

	@Autowired
	private IUserService userService;
	
	@Autowired
	private ImageConverter imageConverter;
	
	@Autowired
	private UserConverter userConverter;
	
	@Override
	public Post toEntity(PostDTO o) {
		Post post = modelMapper.map(o, Post.class);
		
		DetailPost detail = modelMapper.map(o.getDetail(), DetailPost.class);
		detail.setId(o.getDetail().getId());
		detail.setPost(post);
		
		post.setDetail(detail);
		post.setKinhOfRoom(kindOfRoomService.findOneById(o.getKinhOfRoom().getId()));
		post.setUser(userService.findByUserName(customUserDetail.getPrincipleName()).get());
		
		Address ad = modelMapper.map(o.getAddress(), Address.class);
		ad.setId(o.getAddress().getId());
		ad.setDistrict(modelMapper.map(o.getAddress().getDistrict(), District.class));
		ad.setPost(post);
		
		post.setAddress(ad);
		// comment, image
		return post;
	}

	@Override
	public PostDTO toDTO(Post o) {
		PostDTO postDTO = modelMapper.map(o, PostDTO.class);
		postDTO.setKinhOfRoom(modelMapper.map(o.getKinhOfRoom(), KindOfRoomDTO.class));
		postDTO.setDetail(modelMapper.map(o.getDetail(), DetailPostDTO.class));
		postDTO.setUser(userConverter.toDTO(o.getUser())); 
		
		AddressDTO ad = modelMapper.map(o.getAddress(), AddressDTO.class);
		ad.setDistrict(modelMapper.map(o.getAddress().getDistrict(), DistrictDTO.class));
		postDTO.setAddress(ad);
		
		postDTO.setImage(o.getImage().stream().map(imageConverter::toDTO).collect(Collectors.toList()));

		postDTO.setComments(o.getComments().stream().map(e -> {
			return modelMapper.map(e, CommentDTO.class);
		}).collect(Collectors.toList()));

		return postDTO;
	}


}
