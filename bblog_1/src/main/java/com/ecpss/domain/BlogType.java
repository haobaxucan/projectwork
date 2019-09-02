package com.ecpss.domain;

import lombok.Data;

import javax.persistence.*;
import javax.validation.constraints.NotBlank;

/**
 * Created by xc on 2019/8/24.
 */
@Data
@Entity
@Table(name = "type")
public class BlogType {
    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;
  
    private String name;
    
    
}
