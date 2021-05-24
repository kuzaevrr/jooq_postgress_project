package com.jooq.postgress.project.jooq_postgress_project;

import com.jooq.postgress.project.jooq_postgress_project.parser.Parser;
import com.jooq.postgress.project.jooq_postgress_project.pojo.IncObject;
import com.jooq.postgress.project.jooq_postgress_project.service.ServiceJooq;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

import static junit.framework.TestCase.assertFalse;

import static org.junit.Assert.assertNotNull;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringBootTest
class JooqPostgresProjectApplicationTests {


    @Autowired
    ServiceJooq serviceJooq;
    @Autowired
    Parser parser;


    @Test
    void contextLoads() {
        parser.parseXLS(serviceJooq);
    }

    @Test
    public void findAllIncidents() {
        List<IncObject> incObjects = serviceJooq.getAllIncidents();
        assertNotNull(incObjects);
        assertFalse(incObjects.isEmpty());
        for (IncObject incObject : incObjects) {
            System.err.println(incObject);
        }
    }

    @Test
    void getIncident() {
        serviceJooq.getIncident("08");
    }

    @Test
    void deleteIncident() {
        serviceJooq.deleteIncident("80");
    }

    @Test
    void deleteAll(){
        serviceJooq.deleteAll();
    }

//    @Test
//    public void findPostById()  {
//        Post post = blogService.getPost(1);
//        assertNotNull(post);
//        System.out.println(post);
//        List<Comment> comments = post.getComments();
//        System.out.println(comments);
//
//    }
//
//    @Test
//    public void createPost() {
//        Post post = new Post(0, "My new Post",
//                "This is my new test post",
//                new Timestamp(System.currentTimeMillis()));
//        Post savedPost = blogService.createPost(post);
//        Post newPost = blogService.getPost(savedPost.getId());
//        assertEquals("My new Post", newPost.getTitle());
//        assertEquals("This is my new test post", newPost.getContent());
//    }
//
//    @Test
//    public void createComment() {
//        Integer postId = 1;
//        Comment comment = new Comment(0, postId, "User4",
//                "user4@gmail.com", "This is my new comment on post1",
//                new Timestamp(System.currentTimeMillis()));
//        Comment savedComment = blogService.createComment(comment);
//        Post post = blogService.getPost(postId);
//        List<Comment> comments = post.getComments();
//        assertNotNull(comments);
//        for (Comment comm : comments)
//        {
//            if(savedComment.getId() == comm.getId()){
//                assertEquals("User4", comm.getName());
//                assertEquals("user4@gmail.com", comm.getEmail());
//                assertEquals("This is my new comment on post1", comm.getContent());
//            }
//        }
//
//    }

}
