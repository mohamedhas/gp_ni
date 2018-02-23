package fr.univ_smb.isc.m2.domain.customer;

import fr.univ_smb.isc.m2.config.Application;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.IntegrationTest;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.boot.test.TestRestTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import org.springframework.web.client.RestTemplate;

import java.net.URL;

import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

@RunWith(SpringJUnit4ClassRunner.class)
@SpringApplicationConfiguration(classes = Application.class)
@WebAppConfiguration
@IntegrationTest({"server.port=0"})
public class CustomerControllerTestIT {

    @Value("${local.server.port}")
    private int port;

    private RestTemplate template;

    @Before
    public void setUp() throws Exception {
        template = new TestRestTemplate();
    }

    @Test
    public void shouldGetCustomer() throws Exception {
        URL url = new URL("http://localhost:" + port + "/customers/3");
        ResponseEntity<String> response = template.getForEntity(url.toString(), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.OK));
        assertThat(response.getBody(), equalTo("{\"firstName\":\"Rod\",\"lastName\":\"Evans\",\"id\":3}"));
    }

    @Test
    public void shouldNotGetNonExistingCustomer() throws Exception {
        URL url = new URL("http://localhost:" + port + "/customers/8");
        ResponseEntity<String> response = template.getForEntity(url.toString(), String.class);
        assertThat(response.getStatusCode(), equalTo(HttpStatus.NOT_FOUND));
    }

}