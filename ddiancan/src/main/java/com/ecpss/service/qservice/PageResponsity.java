package com.ecpss.service.qservice;

import com.ecpss.domain.ProductInfo;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 *
 */
public interface PageResponsity extends JpaRepository<ProductInfo,Integer>{
}
