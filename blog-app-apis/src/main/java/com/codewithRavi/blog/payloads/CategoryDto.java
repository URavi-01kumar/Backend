package com.codewithdurgesh.blog.payloads;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Setter
@Getter
public class CategoryDto {
	
	
	private Integer categoryId;
	@NotBlank
	@Size(min=4,message="minimum size of category is 4 Charecters")
	private String categoryTitle;
	@NotBlank
	@Size(min=10, message=" the size of description is 10 charectors at least")
	private String categoryDescription;
	

}
