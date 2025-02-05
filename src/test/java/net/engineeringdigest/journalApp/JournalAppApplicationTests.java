//package net.engineeringdigest.journalApp;
//
//import org.junit.jupiter.api.BeforeAll;
//import org.junit.jupiter.api.BeforeEach;
//import org.junit.jupiter.api.Test;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.boot.test.context.SpringBootTest;
//import org.springframework.boot.test.web.server.LocalServerPort;
//import org.springframework.test.context.jdbc.Sql;
//import org.springframework.web.client.RestTemplate;
//
//@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
//class JournalAppApplicationTests {
//
//	@Test
//	void contextLoads() {
//	}
//
//	@LocalServerPort
//	private int port;
//
//	private String baseUrl = "localhost";
//
//	private static RestTemplate restTemplate;
//
////	@Autowired
////	private TestH2Repo testH2Repo;
//
//	@BeforeAll
//	public static void init() {
//		restTemplate = new RestTemplate();
//	}
//
//	@BeforeEach
//	public void setUp() {
//		baseUrl = baseUrl.concat(":").concat(port+"").concat("/journal");
//	}
//
//	@Test
//	public void testgetAll() {
//		restTemplate.getForObject(baseUrl + "journal", );
//	}
//
//
//
//}
