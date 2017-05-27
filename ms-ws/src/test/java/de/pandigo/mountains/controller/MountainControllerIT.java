package de.pandigo.mountains.controller;


import static org.hamcrest.CoreMatchers.is;
import static org.mockito.Matchers.anyLong;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.time.LocalDate;

import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import de.pandigo.mountains.MsTemplateProjectApplication;
import de.pandigo.domain.MountainEntity;
import org.wscale.mountains.service.MountainService;
import org.wscale.commons.test.AbstractRestIT;


/**
 * MountainControllerIT - Integration Test, here we don't want to test the services logic or the database, the purpose
 * of this test is to validate that the REST interface fulfills it's contract (structure and status codes).
 *
 * The contract ist defined in a RAML file located in: ms-ws/src/main/resources/raml/mountains.raml
 */

// At the top, we use the @SpringBootTest(classes = BookmarksApplication.class) annotation (from Spring Boot)
// to tell the SpringJUnit4ClassRunner (SpringRunner) where it should get information about the Spring application
// under test. In former versions of Spring Boot this was SpringApplicationConfiguration.
@SpringBootTest(classes = MsTemplateProjectApplication.class)
//@TestPropertySource(locations = { "classpath:/test.properties" })
public class MountainControllerIT extends AbstractRestIT {

    // Because we have created a bean definition in our RestITConfig for the creation of that object we will now get
    // an mockito mock object autowired for our test instead of the original service which is wired to the database.
    @Autowired
    private MountainService mountainService;

    @Test
    public void test() throws Exception {

        // Arrange
        final MountainEntity mountainEntity = new MountainEntity("Mount Rushmore", 2894, 2004, new String[] {"Ape Simpson", "Homer Simpson"}, LocalDate.now());

        when(this.mountainService.getMountain(anyLong())).thenReturn(mountainEntity);

        getMockMvc().perform(get("/mountains/"+1L))
                .andExpect(status().isOk())
                //.andExpect(ramlMatches())
                .andExpect(jsonPath("$.name", is("Mount Rushmore")));
    }

     /*@Test
    // Here we connect the Test with the RAML definition.
   @RamlDefinition(value = "classpath:/raml/foo.raml", baseUri = "http://{baseUri}")
    public void whenGetFooWithSuccess() throws Exception {

        // Arrange
        final Long fooID = 1L;
        final FooEntity fooEntity = new FooEntity();
        fooEntity.setFooId(fooID);
        fooEntity.setName("Foo name");
        fooEntity.setDescription("Foo description");
        fooEntity.setFooKind(FooType.ACCOUNTING);

        // With Mockito we mock the return value of the FooService, since FooService is a Singleton service it is
        // possible. Later when we access this object again indirectly with getMockMvc().perform() the same FooService
        // will be used with the mocked up value we set up here.
        when(this.fooService.getFooById(anyLong())).thenReturn(fooEntity);

        // Act & Assert
        getMockMvc().perform(get("/foos/"+fooID))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(ramlMatches())
                .andExpect(jsonPath("$.identifier", is(1)));
    }*/


    // TODO make failing tests as well!
/*
    @Test
    public void getAllMountains_returnsAllMountains() throws Exception {
        this.getMockMvc().perform(get("/mountains/")
                .contentType(this.getContentType()))
                .andExpect(status().isOk());
        // TODO add here raml test
    }

    @Test
    public void addMountain_mountainWasCreated() throws Exception {
        Mountain mountain = new Mountain("Mount Everest", 8848);

        this.getMockMvc().perform(post("/mountains/")
                .contentType(this.getContentType())
                .content(populateMockHttpOutputStringWith(mountain)))
                .andExpect(status().isCreated());
        // TODO add here raml test
    }

    @Test
    public void getMountain_mountainReturned() throws Exception {
        this.getMockMvc().perform(get("/mountains/1")
                .contentType(this.getContentType()))
                .andExpect(status().isOk());
        // TODO add here raml test
    }

    @Test
    public void updateFullMountain_mountainFullyUpdated() throws Exception {
        Mountain mountain = new Mountain("Mount Everest", 8847);
        this.getMockMvc().perform(put("/mountains/1")
                .contentType(this.getContentType())
                .content(populateMockHttpOutputStringWith(mountain)))
                .andExpect(status().isNoContent());
        // TODO add here raml test
    }

    @Test
    public void updatePartiallyMountain_mountainPartiallyUpdated() throws Exception {
        Mountain mountain = new Mountain();
        mountain.setAltitude(8849);
        this.getMockMvc().perform(patch("/mountains/1")
                .contentType(this.getContentType())
                .content(populateMockHttpOutputStringWith(mountain)))
                .andExpect(status().isNoContent());
        // TODO add here raml test
    }


    @Test
    public void deleteMountain_mountainDeleted() throws Exception {
        this.getMockMvc().perform(delete("/mountains/1")
                .contentType(this.getContentType()))
                .andExpect(status().isNoContent());
        // TODO add here raml test
    }
*/
}