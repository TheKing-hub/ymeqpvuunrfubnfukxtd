package com.example.ymeqpvuunrfubnfukxtd;

import com.example.ymeqpvuunrfubnfukxtd.service.implementation.model_service_impl.CallOneServiceImpl;
import com.example.ymeqpvuunrfubnfukxtd.service.model_service.CallOneService;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import org.testng.Assert;


@SpringBootTest
class YmeqpvuunrfubnfukxtdApplicationTests {
	@Test
	void contextLoads() {
	}

	// Тестирование номера телефона
	@Test
	public void testPhoneNumber() {
		CallOneService callOneService = new CallOneServiceImpl(null);
		Boolean fail0 = callOneService.checkPhoneNumber("1a2b3c");
		Boolean fail1 = callOneService.checkPhoneNumber("+770730664461");
		Boolean fail2 = callOneService.checkPhoneNumber("+770730664");
		Boolean success0 = callOneService.checkPhoneNumber("+77475643125");
		Boolean success1 = callOneService.checkPhoneNumber("87475643125");
		Assert.assertEquals(fail0, false, "Phone number shouldn't have letters!");
		Assert.assertEquals(fail1, false, "Phone number shouldn't be longer than 12!");
		Assert.assertEquals(fail2, false, "Phone number shouldn't be less than 11!");
		Assert.assertEquals(success0, true, "Phone number with + and length 12 should be correct!");
		Assert.assertEquals(success1, true, "Phone number with length 11 should be correct!");
	}


	// Тестирование имени
	@Test
	public void testName() {
		CallOneService callOneService = new CallOneServiceImpl(null);
		Boolean fail0 = callOneService.checkName("P2P");
		Boolean fail1 = callOneService.checkName("O'Donnell");
		Boolean fail2 = callOneService.checkName("Jones-smith");
		Boolean success0 = callOneService.checkName("Askhat");
		Boolean success1 = callOneService.checkName("Zhandos");
		Assert.assertEquals(fail0, false, "Name shouldn't have a number in it!");
		Assert.assertEquals(fail1, false, "Name shouldn't have a ' symbol!");
		Assert.assertEquals(fail2, false, "Name shouldn't have a - symbol!");
		Assert.assertEquals(success0, true, "Name is written correctly!");
		Assert.assertEquals(success1, true, "Name is written correctly!");
	}

	//Тестирование даты
	@Test
	public void testDate() {
		CallOneService callOneService = new CallOneServiceImpl(null);
		Boolean fail0 = callOneService.checkDate("01-01-1800");
		Boolean fail1 = callOneService.checkDate("10-09-2040");
		Boolean fail2 = callOneService.checkDate("11-15-2012");
		Boolean success0 = callOneService.checkDate("02-02-2020");
		Boolean success1 = callOneService.checkDate("21-07-2017");
		Assert.assertEquals(fail0, false, "This date is less than 1900 year!");
		Assert.assertEquals(fail1, false, "This date is more than now!");
		Assert.assertEquals(fail2, false, "This date has incorrect month!");
		Assert.assertEquals(success0, true, "This date is between 1900 and now!");
		Assert.assertEquals(success1, true, "This date is between 1900 and now!");
	}
}
