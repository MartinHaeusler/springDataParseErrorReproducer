package com.example.demo;

import com.example.demo.model.MyEntity;
import com.example.demo.service.MyEntityService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class DemoApplicationTests {

	@Autowired
	private MyEntityService service;

	@Test
	void contextLoads() {
	}

	@Test
	public void canWorkWithSoftDeletion(){
		var entity1 = new MyEntity("foo");
		var entity2 = new MyEntity("bar");
		var entity3 = new MyEntity("baz");

		this.service.save(entity1);
		this.service.save(entity2);
		this.service.save(entity3);

		assertEquals(3, this.service.getAll().size());

		entity1 = this.service.softDelete(entity1);
		assertNotNull(entity1.getDeleted());

		assertEquals(2, this.service.getAll().size());
		assertTrue(this.service.getById(entity1.getId()).isPresent());

		this.service.hardDeleteAllSoftDeletedEntities();

		assertFalse(this.service.getById(entity1.getId()).isPresent());
	}

}
