package com.chars.muguildbusiness.model.entity;

import java.io.Serializable;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "item_types")
public class ItemType implements Serializable {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private Long item_type_id;
	@Column(unique = true, length = 20, nullable = false)
	private String name;
	@Column(nullable = false)
	private Boolean enabled;
	
	@OneToMany(mappedBy = "itemType", fetch = FetchType.LAZY, cascade = CascadeType.ALL)
	private List<ItemCategoryType> itemCategoryTypes;

	public Long getItem_type_id() {
		return item_type_id;
	}

	public void setItem_type_id(Long item_type_id) {
		this.item_type_id = item_type_id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Boolean getEnabled() {
		return enabled;
	}

	public void setEnabled(Boolean enabled) {
		this.enabled = enabled;
	}

	public List<ItemCategoryType> getItemCategoryTypes() {
		return itemCategoryTypes;
	}

	public void setItemCategoryTypes(List<ItemCategoryType> itemCategoryTypes) {
		this.itemCategoryTypes = itemCategoryTypes;
	}
	
	
}
