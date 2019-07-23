package com.ecpss.spring.domain;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.xml.bind.annotation.*;
import java.util.List;

/**
 * Created by xc on 2019/7/16.
 */
@Data
@XmlRootElement(name = "c")
@XmlAccessorType(XmlAccessType.FIELD)
@NoArgsConstructor
public class CityList {
    @XmlElement(name = "d")
    private List<City> cityLists;
    
}
