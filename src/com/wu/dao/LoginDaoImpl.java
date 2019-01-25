package com.wu.dao;

import java.util.List;

import org.hibernate.criterion.DetachedCriteria;
import org.hibernate.criterion.Restrictions;
import org.springframework.orm.hibernate5.support.HibernateDaoSupport;
import org.springframework.transaction.annotation.Transactional;

import com.wu.domain.User;

@Transactional
public class LoginDaoImpl extends HibernateDaoSupport implements LoginDao{

	@Override
	public User useLogin(String name,String password) {
		DetachedCriteria detachedCriteria = DetachedCriteria.forClass(User.class);
		detachedCriteria.add(Restrictions.eq("name", name));
		detachedCriteria.add(Restrictions.eq("password", password));
		List<User> list = (List<User>)getHibernateTemplate().findByCriteria(detachedCriteria);
		if(list.size()>0){
			return list.get(0);
		}
		return null;
		
	}

}
