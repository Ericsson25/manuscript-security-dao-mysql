package manuscript.system.security.login;

import java.util.List;

import org.springframework.stereotype.Repository;

import manuscript.system.security.login.mapper.LoginMapper;

@Repository
public class LoginDaoImpl implements LoginDao {

	private LoginMapper loginMapper;

	public LoginDaoImpl(LoginMapper loginMapper) {
		this.loginMapper = loginMapper;
	}

	@Override
	public String loadUserIdByUsername(String username) {
		return loginMapper.loadUserIdByUsername(username);
	}

	@Override
	public String loadPasswordByUserId(String userId) {
		return loginMapper.loadPasswordByUserId(userId);
	}

	@Override
	public List<String> loadAuthorityListByUserId(String userId) {
		return loginMapper.loadAuthorityListByUserId(userId);
	}

}
