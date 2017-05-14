package manuscript.system.security.login.test;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import manuscript.system.security.login.LoginDao;
import manuscript.system.security.login.LoginDaoImpl;
import manuscript.system.security.login.mapper.LoginMapper;
import manuscript.test.dao.context.AbstractDaoTestContext;

@Configuration
@MapperScan("manuscript.system.security.login.mapper")
public class LoginMapperContext extends AbstractDaoTestContext {

	@Autowired
	private LoginMapper loginMapper;

	@Bean
	public LoginDao getLoginDao() {
		return new LoginDaoImpl(loginMapper);
	}

	@Bean
	public PasswordEncoder getPasswordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
