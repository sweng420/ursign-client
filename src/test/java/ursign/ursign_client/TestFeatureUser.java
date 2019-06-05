package ursign.ursign_client;

import static org.junit.Assert.*;

import java.awt.List;
import java.util.ArrayList;
import java.util.Calendar;

import org.junit.Test;
import ursign.ursign_client.User;

public class TestFeatureUser {
	
	private User user;
	
	@Test
	public void testConstructorWithInputs() {;
		String username = "username";
		Integer id = 5390;
		String email = "example@testemail.com";
		String realname = "Jeremy  Corbyn";
		Integer born = 1949;
		Integer age = (Calendar.getInstance().get(Calendar.YEAR) - born);
		List collections = null;
		List children = null;
		Boolean login_complete = false;
		List cookies = null;
		Integer credits = 137;
		
		user = new User(username, email, born, realname, id);
		
		assertEquals(user.getCredits(), credits);
		assertEquals(user.getAge(), age);
		assertEquals(user.getBorn(), born);
		assertEquals(user.getCookies(), cookies);
		assertEquals(user.getChildren(), children);
		assertEquals(user.getEmail(), email);
		assertEquals(user.getLogin_complete(), login_complete);
		assertEquals(user.getRealname(),realname);
		assertEquals(user.getUid(), id);
		assertEquals(user.getCollections(),collections);
		assertEquals(user.getUsername(),username);
		
	}

	@Test
	public void testConstructorWithNoInputs() {;
		
		user = new User();
		
		assertEquals(user.getCredits(), new Integer(0));
		assertEquals(user.getAge(), new Integer(0));
		assertEquals(user.getBorn(), new Integer(0));
		assertEquals(user.getCookies(), null);
		assertEquals(user.getChildren(), null);
		assertEquals(user.getEmail(), "");
		assertEquals(user.getLogin_complete(), false);
		assertEquals(user.getRealname(),"");
		assertEquals(user.getUid(), new Integer(-1));
		assertEquals(user.getCollections(), null);
		assertEquals(user.getUsername(),"");
	}
	
	@Test
	public void testSetMultimedia(){
		
	}
}
	
	