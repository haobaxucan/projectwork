package com.ecpss.User;

import lombok.Data;

import java.util.ArrayList;
import java.util.List;

@Data

public class Permission {
    private Integer id;
    private Integer pid;
    private String url;
    private String name;
    private boolean open;
    private List<Permission> list=new ArrayList();//设置子节点用的
}
