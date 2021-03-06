package com.chars.muguildbusiness.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.chars.muguildbusiness.dto.OrderRequest;
import com.chars.muguildbusiness.dto.OrderResponse;
import com.chars.muguildbusiness.model.service.OrderService;

@RequestMapping("/api/order")
@RestController
public class OrderController {
	
	@Value("${pagination.pageSize}")
	private Byte pageSize;
	
	@Autowired
	private OrderService orderService;

	@GetMapping
	public ResponseEntity<Page<OrderResponse>> getAll(
			@RequestParam(name = "page", defaultValue = "0", required = false) int page){
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return new ResponseEntity<>(orderService.findAll(pageRequest), HttpStatus.OK);
	}
	
	@GetMapping("/by-item/{itemName}")
	public ResponseEntity<Page<OrderResponse>> getAllByItem(
			@PathVariable String itemName,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page){
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return new ResponseEntity<>(orderService.findAllByItemName(itemName, pageRequest), HttpStatus.OK);		
	}
	
	@GetMapping("/by-item-category/{itemCategoryName}")
	public ResponseEntity<Page<OrderResponse>> getAllByItemCategory(
			@PathVariable String itemCategoryName,
			@RequestParam(name = "page", defaultValue = "0", required = false) int page){
		PageRequest pageRequest = PageRequest.of(page, pageSize);
		return new ResponseEntity<>(orderService.findAllByItemCategoryName(itemCategoryName,pageRequest), HttpStatus.OK);				
	}
	
	@PostMapping
	public ResponseEntity<String> create(@RequestBody OrderRequest order){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		orderService.save(order, auth.getName());
		return new ResponseEntity<>("Order was created", HttpStatus.CREATED);
	}
	
	@GetMapping("/{id}")
	public ResponseEntity<OrderResponse> getOrder(@PathVariable Long id){
		return new ResponseEntity<>(orderService.findById(id), HttpStatus.OK);
	}
		
	@PutMapping
	public ResponseEntity<String> edit(@RequestBody OrderRequest order){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		orderService.edit(order, auth.getName());
		return new ResponseEntity<>("Order was edited", HttpStatus.OK);
	}
	
	@DeleteMapping("/{id}")
	public ResponseEntity<String> delete(@PathVariable Long id){
		Authentication auth = SecurityContextHolder.getContext().getAuthentication();
		orderService.delete(id, auth.getName());
		return new ResponseEntity<>("Order was deleted", HttpStatus.OK);
	}
	
}
