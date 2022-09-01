
package com.d.demo.controller;

import java.util.HashMap;

import java.util.List;
import java.util.Map;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.d.demo.entity.EmployeeMaster;


@RestController
@CrossOrigin(origins = "http://localhost:8080")
public class LoginController {
	

	@Autowired
	private SessionFactory sessionFactory;
	
	
	@RequestMapping(value = "/authenticate", 
			method = RequestMethod.POST)
	public Map userDetails(@RequestBody Map<String, Object> map) {
        
		System.out.print(map);
		Object id = map.get("id");
		
		Object name = map.get("name");
		List<EmployeeMaster> list = getEmployee();
		boolean isMatchFound = false;
		for(EmployeeMaster employee: list)
		{
			if (employee.getId() == Integer.parseInt(id.toString()) 
					&& employee.getName().equalsIgnoreCase(name.toString()))
			{
				isMatchFound = true;
				break;
			}
		}
		Map<String, Boolean> res = new HashMap<>();
		res.put("IsValidEmployye", isMatchFound);
				
		return res;
	}
	
	public List<EmployeeMaster> getEmployee() {
		Criteria criteria = sessionFactory.openSession().createCriteria(EmployeeMaster.class);//Query
		return criteria.list();
	}

}
