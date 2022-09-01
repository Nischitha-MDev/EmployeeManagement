package com.d.demo.controller;

import java.util.List;

import java.util.Map;

import javax.persistence.Query;

import org.hibernate.Criteria;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.d.demo.entity.EmployeeMaster;
import com.d.demo.entity.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class EmployeeController {

	@Autowired
	private SessionFactory sessionFactory;
	
	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = "/fetch/employee", method = RequestMethod.GET)
	@ResponseBody
	public Map fetchEmployee(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name) {

		List<EmployeeMaster> employeeList = getEmployee(id, name);
		if (employeeList.size() > 0) {
			
			return objectMapper.convertValue(employeeList.get(0), Map.class);//converting emplo mast to map
		}
		return null;
	}

	public List getUserDetails() {
		Criteria criteria = sessionFactory.openSession().createCriteria(UserDetails.class);
		return criteria.list();
	}

	public List<EmployeeMaster> getEmployee(int id, String name) {
		Query criteria = sessionFactory.openSession().createQuery("from EmployeeMaster where id=:id and name=:name");
		criteria.setParameter("id", id);
		criteria.setParameter("name", name);
		return criteria.getResultList();
	}

}
