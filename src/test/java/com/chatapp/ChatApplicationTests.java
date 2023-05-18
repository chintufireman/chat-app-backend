package com.chatapp;




import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest//to spin up application
class ChatApplicationTests {
	public Calculator c = new Calculator();
	@Test
	void contextLoads() {
	}
	@Test
	void testSum() {
		
		//expected result
		int expectedResult = 180;
		
		//actual result
		int actual= c.doSum(80, 100);
		
		assertThat(actual).isEqualTo(expectedResult);
		
	}
	
	@Test
	void testMultiply() {
		
		//expected result
		int expectedResult = 6;
		
		//actual result
		int actual= c.doMultiply(2, 3);
		
		assertThat(actual).isEqualTo(expectedResult);
		
	}

}
