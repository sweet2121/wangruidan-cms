package com.wrd.cms.dao;

import java.util.List;

import javax.jws.soap.SOAPBinding.Use;

import com.wrd.cms.domain.Slide;
import com.wrd.cms.domain.User;

public interface SlideDao {

	List<Slide> selects();
}
