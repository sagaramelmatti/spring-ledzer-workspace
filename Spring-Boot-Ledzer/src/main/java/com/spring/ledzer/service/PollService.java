package com.spring.ledzer.service;

import com.spring.ledzer.exception.BadRequestException;
import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.*;
import com.spring.ledzer.payload.PagedResponse;
import com.spring.ledzer.payload.PollRequest;
import com.spring.ledzer.payload.InvoiceResponse;
import com.spring.ledzer.payload.VoteRequest;
import com.spring.ledzer.repository.InvoiceRepository;
import com.spring.ledzer.repository.UserRepository;
import com.spring.ledzer.security.UserPrincipal;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.time.Duration;
import java.time.Instant;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.function.Function;
import java.util.stream.Collectors;

@Service
public class PollService {

    @Autowired
    private InvoiceRepository pollRepository;

    @Autowired
    private UserRepository userRepository;

    private static final Logger logger = LoggerFactory.getLogger(PollService.class);

    private void validatePageNumberAndSize(int page, int size) {
       
    	/*
    	if(page < 0) {
            throw new BadRequestException("Page number cannot be less than zero.");
        }

        if(size > AppConstants.MAX_PAGE_SIZE) {
            throw new BadRequestException("Page size must not be greater than " + AppConstants.MAX_PAGE_SIZE);
        }
        */
    }


}
