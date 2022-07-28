package com.bolappAPI.demo.payloads;

import javax.persistence.Column;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Pattern;
import javax.validation.constraints.Size;

import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@NoArgsConstructor
@Getter
@Setter
@Builder
public class UserDTO {

	public UserDTO(int id, @NotEmpty @Size(min = 4, message = "Minimum 4 characters required") String name,
			@Email(message = "Email format is not correct!!") String email, String password, @NotNull String about) {
		super();
		this.id = id;
		this.name = name;
		this.email = email;
		this.password = password;
		this.about = about;
	}

	private int id;
	
	@NotEmpty
	@Size(min = 4,message = "Minimum 4 characters required")
	private String name;
	
	@Email(message = "Email format is not correct!!")
	private String email;
	
//	@NotNull
//	@Size(min=8,message = "Password must be of 8 characters")
//	@Pattern(regexp = "(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\\\S+$).{8,}")
	private String password;
	
	@NotNull
	private String about;
	
	

}
