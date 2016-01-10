package com.fidelity.thor;



import javax.sql.DataSource;

import org.springframework.jdbc.core.JdbcTemplate;

public class JdbcUserDao {
	 DataSource dataSource;
	   
		

	 public void setDataSource(DataSource dataSource) {
	 	this.dataSource = dataSource;
	 }

	 	public void saveUserDetails(Object target) {
	 		JdbcTemplate jdbcTemplate=new JdbcTemplate(dataSource);
	 		Userpojo userpojo=(Userpojo) target;
	 		String sql="Insert into raam(Firstname,lastname,Gender,State,dob,number,email,password)values(?,?,?,?,?,?,?,?)";
	 		jdbcTemplate.update(sql,
	 				new Object[]{userpojo.getFname(),userpojo.getLname(),userpojo.getGender(),userpojo.getState(),userpojo.getDob(),userpojo.getPho(),userpojo.getEmail(),userpojo.getPass(),userpojo.getCpass()});
	 	}
}
