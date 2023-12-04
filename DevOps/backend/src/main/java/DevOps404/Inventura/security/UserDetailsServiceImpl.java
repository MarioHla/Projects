package DevOps404.Inventura.security;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import DevOps404.Inventura.DTOI.UserDTOI;
import DevOps404.Inventura.entity.User;


@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService{

	@Autowired
	UserDTOI userDao;

	@Override
	public User loadUserByUsername(String username) {	
		return userDao.getUserByName(username).get(0);
	}

}
