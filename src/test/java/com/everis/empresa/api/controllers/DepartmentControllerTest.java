package com.everis.empresa.api.controllers;

import com.everis.empresa.api.entities.Department;
import com.everis.empresa.api.services.DepartmentService;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Arrays;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest
public class DepartmentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DepartmentService departmentService;

  @Test
  public void getAll() throws Exception {
    Department department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    List<Department> departments = Arrays.asList(department);
    given(departmentService.findAll()).willReturn(departments);

    this.mockMvc.perform(get("/departments"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{'departmentId' : 1, 'name' : 'Technology', 'ubication' : '2A'}]"));
  }

  @Test
  public void create() throws Exception {
    Department department = new Department();
    department.setName("Technology");
    department.setUbication("2A");

    this.mockMvc.perform(post("/departments")
    .contentType(MediaType.APPLICATION_JSON)
    .content(asJsonString(department)))
            .andExpect(status().isCreated());
  }

  @Test
  public void findById() throws Exception {
    Department department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    given(departmentService.findById((long)1)).willReturn(department);

    this.mockMvc.perform(get("/departments/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'departmentId' : 1, 'name' : 'Technology', 'ubication' : '2A'}"));
  }

  @Test
  public void update() throws Exception {
    Department department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    Department updatedDepartment = new Department();
    updatedDepartment.setName("Mechanic");
    updatedDepartment.setUbication("2B");


  }

  @Test
  public void delete() {
  }

  public static String asJsonString(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}