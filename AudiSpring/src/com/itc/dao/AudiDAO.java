package com.itc.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;

import com.itc.beans.Audi;




public class AudiDAO {
	JdbcTemplate template;
	public void setTemplate(JdbcTemplate template) {
		this.template = template;
	}
	
	public List<Audi> getAllEvents()
	{
		return template.query("select * from audi",new RowMapper<Audi>(){
			public Audi mapRow(ResultSet rs, int row) throws SQLException {
				Audi audi=new Audi(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getInt(4), rs.getInt(5),rs.getString(6));
				return audi;
			}
		});
	}
	
	
	public Audi getEventBean(int eventid)
	{
		String sql="select * from audi where eventid=?";
		return template.queryForObject(sql, new Object[]{eventid},new BeanPropertyRowMapper<Audi>(Audi.class));
	}
	
	
	public int update(Audi a){
		String sql="update audi set quantity=quantity-"+a.getQuantity()+" where eventid="+a.getEventid();
		return template.update(sql);
	}
	
}
