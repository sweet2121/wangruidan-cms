package com.wrd.cms.service;

import java.util.List;

import com.github.pagehelper.PageInfo;
import com.wrd.cms.domain.Slide;
import com.wrd.cms.domain.User;

public interface SlideService {

	List<Slide> selects();
}
