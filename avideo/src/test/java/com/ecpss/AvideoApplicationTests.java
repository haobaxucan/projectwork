package com.ecpss;

import com.ecpss.domain.Video;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import javax.sql.DataSource;

@RunWith(SpringRunner.class)
@SpringBootTest
public class AvideoApplicationTests {
	@Autowired
	public DataSource dataSource;
	
	@PersistenceContext
	private EntityManager em;
	
	/**
	 		System.out.println(dataSource);
			String jpql="from Video v where v.id= :id";
	 		Query query = em.createQuery(jpql).setParameter("id", 1);
	 
	 */
	@Test
	public void contextLoads() {

		String jpql="from Video v where v.id= ?1";
		Query query = em.createQuery(jpql).setParameter(1, 1);
//		query.setFirstResult(1);
//		query.setMaxResults(10);  分页
		Video video = (Video) query.getResultList().stream().findFirst().orElse(null);
		System.err.println(video.getPrice());
	}

}
