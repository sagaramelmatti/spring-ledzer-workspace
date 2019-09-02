package com.spring.ledzer.service;

import java.util.List;

import com.spring.ledzer.exception.ResourceNotFoundException;
import com.spring.ledzer.model.dto.ItemWiseSaleHistoryDTO;

public interface ItemWiseSaleHistoryService {
	
	public List<ItemWiseSaleHistoryDTO> getItemWiseSaleHistory() throws ResourceNotFoundException;

}
