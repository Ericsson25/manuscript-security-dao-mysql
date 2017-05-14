package manuscript.system.security.login.test;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;
import org.springframework.transaction.annotation.Transactional;

import manuscript.system.security.login.LoginDao;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class, classes = LoginMapperContext.class)
@Transactional
public class LoginMapperTest {

	@Autowired
	private LoginDao loginDao;

	@Autowired
	private PasswordEncoder passwordEncoder;

	private static String dummyUserIdOne;
	private static String dummyUserUsernameOne;

	private static List<String> authorityList;

	@BeforeClass
	public static void init() {
		authorityList = new ArrayList<String>();

		dummyUserIdOne = "1";
		dummyUserUsernameOne = "dummyuserwithpassword";
	}

	@Test
	public void loadUserIdByUsernameWithResultTest() {
		String userId = loginDao.loadUserIdByUsername(dummyUserUsernameOne);

		Assert.assertNotNull("User Id must not be null", userId != null);
		Assert.assertEquals("1", userId);

	}

	@Test
	public void loadPasswordByUserIdWithResultTest() {
		String password = loginDao.loadPasswordByUserId(dummyUserIdOne);

		Assert.assertNotNull(password);

		String expectedPassword = "dummypassword";
		Assert.assertEquals(expectedPassword, password);
	}

	@Test
	public void loadAuthorityListByUserIdWithResultTest() {
		authorityList = loginDao.loadAuthorityListByUserId(dummyUserIdOne);

		List<String> expectedAuthority = new ArrayList<String>();
		expectedAuthority.add("AUTHOR_ROLE");
		expectedAuthority.add("REVIEWER_ROLE");

		Assert.assertNotNull("authority list must not be null", authorityList);
		Assert.assertTrue("authority list must contains 1 and 2", authorityList.containsAll(expectedAuthority));
	}

}
