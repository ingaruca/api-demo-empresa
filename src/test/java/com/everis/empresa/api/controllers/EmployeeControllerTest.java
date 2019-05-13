package com.everis.empresa.api.controllers;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.entities.Employee;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(EmployeeController.class)
public class EmployeeControllerTest {

  @Autowired
  MockMvc mockMvc;

  @MockBean
  EmployeeController employeeController;

  private Employee employee;

  @Test
  public void getAll() throws Exception {
    List<Employee> employees = new ArrayList<>();
    given(employeeController.getAll()).willReturn(employees);

    this.mockMvc.perform(get("/employees"))
            .andExpect(status().isOk())
            .andExpect(content().json("[]"));
  }

  @Test
  public void findById() throws Exception {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setEmployeId((long)1);
    employee.setName("Luis Ingaruca");
    employee.setSalary(3000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("YES");
    given(employeeController.findById((long)1)).willReturn(employee);

    this.mockMvc.perform(get("/employees/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{\n" +
                    "        \"name\": \"Luis Ingaruca\",\n" +
                    "        \"salary\": 3000,\n" +
                    "        \"telephone\": 999888777,\n" +
                    "        \"department\": {\n" +
                    "            \"departmentId\": 1,\n" +
                    "            \"name\": \"Technology\",\n" +
                    "            \"ubication\": \"2B\"\n" +
                    "        },\n" +
                    "        \"boss\": \"YES\",\n" +
                    "        \"employeId\": 1\n" +
                    "    }"));
  }

  /*
  @Test
  public void findByIdNotFound() throws Exception {
    given(employeeController.findById(2L)).willReturn(new Employee());
    this.mockMvc.perform(get("/employees/2"))
            .andExpect(status().isNotFound());
  }*/

  @Test
  public void create() throws Exception {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setEmployeId((long)1);
    employee.setName("Luis Ingaruca");
    employee.setSalary(3000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("YES");

    employeeController.create(employee);

    this.mockMvc.perform(post("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(employee)))
            .andExpect(status().isCreated());
  }

  @Test
  public void update() throws Exception {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setEmployeId((long)1);
    employee.setName("Luis Ingaruca");
    employee.setSalary(3000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("YES");

    employeeController.update(employee);

    this.mockMvc.perform(put("/employees")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(employee)))
            .andExpect(status().isOk());
  }

  @Test
  public void deleteEmployee() throws Exception {
    Department department = new Department(1L, "Technology", "2B");
    employee = new Employee();
    employee.setEmployeId((long)1);
    employee.setName("Luis Ingaruca");
    employee.setSalary(3000.00);
    employee.setTelephone(999888777);
    employee.setDepartment(department);
    employee.setBoss("YES");

    employeeController.delete(employee.getEmployeId());

    this.mockMvc.perform(delete("/employees/1"))
            .andExpect(status().isNoContent());
  }

  /*@Test
  public void deleteNotFound() throws Exception {
    employeeController.delete(10L);

    this.mockMvc.perform(delete("/employees/10"))
            .andExpect(status().isNotFound());
  }*/

  public static String asJsonString(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }
}