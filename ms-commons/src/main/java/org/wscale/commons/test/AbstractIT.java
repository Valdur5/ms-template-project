package org.wscale.commons.test;

import java.io.IOException;
import java.nio.charset.Charset;
import java.util.Arrays;

import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.mock.http.MockHttpOutputMessage;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import static org.junit.Assert.assertNotNull;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.webAppContextSetup;

// The @WebAppConfiguration annotation tells JUnit that this is a unit test for Spring MVC web components and
// should thus run under a WebApplicationContext variety, not a standard ApplicationContext implementation.
@WebAppConfiguration

// When a class is annotated with RunWith or extends a class annotated
// with RunWith JUnit will invoke the class it references to run the
// tests in that class instead of the runner built into JUnit.

// SpringRunner is a custom extension of JUnit's BlockJUnit4ClassRunner which provides functionality of the
// Spring TestContext Framework to standard JUnit tests by means of the TestContextManager and associated support
// classes and annotations.
@RunWith(SpringRunner.class)
public abstract class AbstractIT {

    // We define here the content type of the payload for our POST requests and the content type we get back from
    // GET requests. A client might decide which parser to invoke based on the Content-Type header in the request message
    private final MediaType contentType = new MediaType(MediaType.APPLICATION_JSON.getType(),
            MediaType.APPLICATION_JSON.getSubtype(),
            Charset.forName("utf8"));

    // The MockMvc is the center piece: all tests will invariably go through the MockMvc type to mock HTTP requests
    // against the service.
    private MockMvc mockMvc;

    // The Jackson 2 Http message converter provides us the possibility to easily convert the Java objects into HTTP
    // messages, this includes to convert a payload Java object to put into the body as a JSON string.
    private HttpMessageConverter mappingJackson2HttpMessageConverter;

    // Get the reference to the WebApplicationContext which is derived from the ApplicationContext.
    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    // Depending on the Converter JARs in the classpath the HttpMessageConverter Array is filled this method is called
    // from spring and we try to find the message converter we are looking for to store in our local variable.
    private void setConverters(final HttpMessageConverter<?>[] converters) {
        this.mappingJackson2HttpMessageConverter = Arrays.stream(converters)
                .filter(hmc -> hmc instanceof MappingJackson2HttpMessageConverter)
                .findAny()
                .orElse(null);

        assertNotNull("the JSON message converter must not be null",
                this.mappingJackson2HttpMessageConverter);
    }

    @Before
    public void setup() throws Exception {
        // Build a MockMvc instance using the given, fully initialized refreshed WebApplicationContext. The
        // org.springframework.web.servlet.DispatcherServlet will use the context to discover Spring MVC infrastructure
        // and application controllers in it. The context must have been configured with a javax.servlet.ServletContext.
        this.mockMvc = webAppContextSetup(this.webApplicationContext).build();
    }

    public MediaType getContentType() {
        return contentType;
    }

    public MockMvc getMockMvc() {
        return mockMvc;
    }

    // Creates a HTTP string which contains the header with the media type JSON Application and as the body the given
    // object as a json string. This method is used to test the post request since they are the requests in this example
    // which require a JSON payload in their request body.
    protected String populateMockHttpOutputStringWith(final Object o) throws IOException {
        final MockHttpOutputMessage mockHttpOutputMessage = new MockHttpOutputMessage();
        this.mappingJackson2HttpMessageConverter.write(
                o, MediaType.APPLICATION_JSON, mockHttpOutputMessage);
        return mockHttpOutputMessage.getBodyAsString();
    }

}
