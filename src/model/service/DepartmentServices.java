package model.service;

import java.util.ArrayList;
import java.util.List;

import model.entities.Department;

public class DepartmentServices {
public List<Department> findAll(){
	List<Department> list = new ArrayList<Department>();
	list.add(new Department(1,"computer"));
	list.add(new Department(2,"eletronics"));
	list.add(new Department(3,"books"));
	return list;
	
}
}
