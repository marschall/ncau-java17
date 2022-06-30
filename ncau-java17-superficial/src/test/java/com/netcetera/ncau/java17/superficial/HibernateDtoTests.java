package com.netcetera.ncau.java17.superficial;

import static org.junit.jupiter.api.Assertions.assertEquals;

import java.time.LocalDate;
import java.time.OffsetDateTime;
import java.time.ZoneOffset;
import java.util.List;

import org.hibernate.transform.AliasToBeanConstructorResultTransformer;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import jakarta.persistence.EntityManager;
import jakarta.persistence.EntityManagerFactory;
import jakarta.persistence.Persistence;

class HibernateDtoTests {

  private EntityManagerFactory entityManagerFactory;

  @BeforeEach
  void setUp() {
    this.entityManagerFactory = Persistence.createEntityManagerFactory("dto-projection-test");
  }

  @AfterEach
  void tearDown() {
    this.entityManagerFactory.close();
  }

  @Test
  void jpaApi() {
    EntityManager entityManager = this.entityManagerFactory.createEntityManager();
    try {
      OffsetDateTime fromTimestamp = LocalDate.of(2020, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC);
      List<PostDTO> postDTOs = entityManager.createQuery(
              """
              SELECT new com.netcetera.ncau.java17.superficial.PostDTO(
                 p.id,
                 p.title
              )
              FROM Post p
              WHERE p.createdOn > :fromTimestamp
              """, PostDTO.class)
              .setParameter("fromTimestamp", fromTimestamp)
              .getResultList();
      assertEquals(1, postDTOs.size());
      PostDTO postDTO = postDTOs.get(0);
      assertEquals(new PostDTO(1, "The best way to map a projection query to a DTO"), postDTO);
    } finally {
      entityManager.close();
    }
  }

  @Test
  void hibernateApi() throws ReflectiveOperationException {
    EntityManager entityManager = this.entityManagerFactory.createEntityManager();
    try {
      OffsetDateTime fromTimestamp = LocalDate.of(2020, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC);
      List<PostDTO> postDTOs = entityManager.createQuery( // JPL
              """
              SELECT
                p.id,
                p.title
              FROM Post p
              WHERE p.createdOn > :fromTimestamp
              """)
              .setParameter("fromTimestamp", fromTimestamp)
              .unwrap(org.hibernate.query.Query.class)
              .setTupleTransformer(new AliasToBeanConstructorResultTransformer<>(PostDTO.class.getConstructor(Integer.class, String.class)))
              .getResultList();
      assertEquals(1, postDTOs.size());
      PostDTO postDTO = postDTOs.get(0);
      assertEquals(new PostDTO(1, "The best way to map a projection query to a DTO"), postDTO);
    } finally {
      entityManager.close();
    }
  }
  
  @Test
  void hibernateApiNativeSql() throws ReflectiveOperationException {
    EntityManager entityManager = this.entityManagerFactory.createEntityManager();
    try {
      OffsetDateTime fromTimestamp = LocalDate.of(2020, 1, 1).atStartOfDay().atOffset(ZoneOffset.UTC);
      List<PostDTO> postDTOs = entityManager.createNativeQuery( // SQL
              """
              SELECT
                p.id,
                p.title
              FROM post p
              WHERE p.created_on > :fromTimestamp
              """)
          .setParameter("fromTimestamp", fromTimestamp)
          .unwrap(org.hibernate.query.Query.class)
          .setTupleTransformer(new AliasToBeanConstructorResultTransformer<>(PostDTO.class.getConstructor(Integer.class, String.class)))
          .getResultList();
      assertEquals(1, postDTOs.size());
      PostDTO postDTO = postDTOs.get(0);
      assertEquals(new PostDTO(1, "The best way to map a projection query to a DTO"), postDTO);
    } finally {
      entityManager.close();
    }
  }

}
