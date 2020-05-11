package com.web.mvc;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.embedded.LocalServerPort;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.SpringBootTest.WebEnvironment;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = WebEnvironment.RANDOM_PORT)
public class WebTest {

    @LocalServerPort
    private int port;

    @Autowired
    private TestRestTemplate restTemplate;

    @Test
    public void landOnIndex() throws Exception {
        assertThat(this.restTemplate.getForObject("http://localhost:" + port + "/",
                String.class)).contains("URL");
    }
    
    @Test
    public void parseGitUrl() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/parse?url=https://github.com/login",null,
                String.class)).contains("Sign in to GitHub · GitHub");
    }
    
    @Test
    public void parseSpiegelUrl() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/parse?url=https://www.spiegel.de/meinspiegel/login.html",null,
                String.class)).contains("Mein SPIEGEL - SPIEGEL ONLINE");
    }
    
    
    @Test
    public void parseWrongUrl() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/parse?url=cbvc45ff",null,
                String.class)).contains("Url seems incorrect");
    }
    
    @Test
    public void parseNullUrl() throws Exception {
        assertThat(this.restTemplate.postForObject("http://localhost:" + port + "/parse?url=",null,
                String.class)).contains("Url can not be empty");
    }
}