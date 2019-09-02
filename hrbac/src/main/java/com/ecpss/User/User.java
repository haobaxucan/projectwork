package com.ecpss.User;

import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@NoArgsConstructor
public class User {

	private Integer id;
	private String username;
	private String account;
	private String password;
	private String email;
	private Date createtime;

	
}
