package com.d.demo.controller;

import java.util.ArrayList;

import java.util.HashMap;
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

import com.d.demo.entity.Dependant;
import com.d.demo.entity.EmployeeMaster;
import com.d.demo.entity.UserDetails;
import com.fasterxml.jackson.databind.ObjectMapper;

@Controller
public class DependantController {

	@Autowired
	private SessionFactory sessionFactory;

	@Autowired
	ObjectMapper objectMapper;

	@RequestMapping(value = "/fetch/dependants", method = RequestMethod.GET)
	@ResponseBody
	public Map fetchEmployee(@RequestParam(name = "id") int id, @RequestParam(name = "name") String name) {

		//List<Dependant> dependants2 = getDependants2((int) id, name);
		
		
		List<Map> dependants = getDependants((int) id, name);
		List<Map<String, Object>> allDependents = new ArrayList<>();
		
		for (Object map : dependants) {
			Map<String, Object> dependMap = new HashMap<String, Object>();
			dependMap.put("Relation", ((Object[])map)[3]);
			dependMap.put("Name", ((Object[])map)[2]);
			dependMap.put("Age", ((Object[])map)[1]);
			allDependents.add(dependMap);
		}
		Map<String, Object> response = new HashMap<>();
		response.put("data", allDependents);

		return response;
	}

	public List<Map> getDependants(int id, String name) {

		Query query = sessionFactory.openSession()
				.createNativeQuery("SELECT * FROM dependant where employeeMaster_id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

	public List<Dependant> getDependants2(int id, String name) {

		Query query = sessionFactory.openSession()
				.createNativeQuery("from dependant where employeeMaster.id = :id");
		query.setParameter("id", id);
		return query.getResultList();
	}

}
