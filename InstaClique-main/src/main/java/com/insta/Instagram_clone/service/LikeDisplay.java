package com.insta.Instagram_clone.service;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.insta.Instagram_clone.model.*;
import com.insta.Instagram_clone.repository.*;

@Service
// public class LikeDisplay {
public class LikeDisplay implements Display_Interface<Integer, Integer> {
	@Autowired
	public Post_interface post_interface;

	public Integer displaylike(Integer postId) {
		List<Integer> lsl = post_interface.findByPostId(postId).getLikes();
		Integer sum = 0;
		for (int i = 0; i < lsl.size(); i++) {
			if (lsl.get(i) != -1) {
				sum = sum + 1;
			}
		}
		return sum;
	}

	@Override
	public Map<Integer, Integer> display(Integer postId) {
		// TODO Auto-generated method stub
		List<Integer> lsl = post_interface.findByPostId(postId).getLikes();
		Integer sum = 0;
		for (int i = 0; i < lsl.size(); i++) {
			if (lsl.get(i) != -1) {
				sum = sum + 1;
			}
		}
		Map<Integer, Integer> mp = new HashMap<Integer, Integer>();
		mp.put(postId, sum);
		return mp;
		// return sum;
		// return null;
	}
}
