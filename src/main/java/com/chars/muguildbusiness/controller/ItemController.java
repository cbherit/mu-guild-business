package com.chars.muguildbusiness.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.chars.muguildbusiness.dto.ItemOptionsAndTypes;
import com.chars.muguildbusiness.dto.ItemResponse;
import com.chars.muguildbusiness.model.service.ItemService;

import static org.springframework.http.ResponseEntity.status;

@RequestMapping("/api/item")
@RestController
public class ItemController {
	
	@Autowired
	private ItemService itemService;
	
	@GetMapping("/{itemName}")
	public ResponseEntity<List<ItemResponse>> getFirstResultsByName(@PathVariable String itemName){
		return status(HttpStatus.OK)
				.body(itemService.findFirstResultsByName(itemName));
	}

	@GetMapping("/options-and-types/{id}")
	public ResponseEntity<ItemOptionsAndTypes> getItemOptionsAndTypes(@PathVariable Long id){
		return status(HttpStatus.OK)
				.body(itemService.findItemOptionsAndTypes(id));
	}
}
