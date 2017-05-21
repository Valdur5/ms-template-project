package de.pandigo.controller;

import de.pandigo.MsTemplateProjectApplication;
import de.pandigo.dto.Mountain;
import de.pandigo.test.AbstractRestIT;
import org.junit.Test;
import org.springframework.boot.test.context.SpringBootTest;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;


/**
 * MountainControllerIT - Integration Test, here we don't want to test the business logic or the database, the purpose
 * of this test is to validate that the REST interface fulfills it's contract (structure and status codes).
 *
 * The contract ist defined in a RAML file located in: ms-ws/src/main/resources/raml/mountains.raml
 */

// At the top, we use the @SpringBootTest(classes = BookmarksApplication.class) annotation (from Spring Boot)
// to tell the SpringJUnit4ClassRunner (SpringRunner) where it should get information about the Spring application
// under test. In former versions of Spring Boot this was SpringApplicationConfiguration.
@SpringBootTest(classes = MsTemplateProjectApplication.class)
public class MountainControllerIT extends AbstractRestIT {

    // TODO make failing tests as well!

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

}