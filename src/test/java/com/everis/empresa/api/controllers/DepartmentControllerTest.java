package com.everis.empresa.api.controllers;

import com.everis.empresa.api.entities.Department;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.servlet.MockMvc;

import java.util.Collections;
import java.util.List;

import static org.mockito.BDDMockito.given;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(DepartmentController.class)
public class DepartmentControllerTest {

  @Autowired
  private MockMvc mockMvc;

  @MockBean
  private DepartmentController departmentController;

  private Department department;

  @Test
  public void getAll() throws Exception {
    department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    List<Department> departments = Collections.singletonList(department);
    given(departmentController.getAll()).willReturn(departments);

    this.mockMvc.perform(get("/departments"))
            .andExpect(status().isOk())
            .andExpect(content().json("[{'departmentId' : 1, 'name' : 'Technology', 'ubication' : '2A'}]"));
  }

  @Test
  public void create() throws Exception {
    department = new Department();
    department.setName("Technology");
    department.setUbication("2A");

    departmentController.create(department);

    this.mockMvc.perform(post("/departments")
    .contentType(MediaType.APPLICATION_JSON)
    .content(asJsonString(department)))
            .andExpect(status().isCreated());
  }

  @Test
  public void findById() throws Exception {
    department = new Department();
    department.setDepartmentId((long)1);
    department.setName("Technology");
    department.setUbication("2A");

    given(departmentController.findById((long)1)).willReturn(department);

    this.mockMvc.perform(get("/departments/1"))
            .andExpect(status().isOk())
            .andExpect(content().json("{'departmentId' : 1, 'name' : 'Technology', 'ubication' : '2A'}"));
  }

  @Test
  public void findByIdNotFound() throws Exception {
    given(departmentController.findById(10L)).willReturn(new Department());
    this.mockMvc.perform(get("/department/10"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void update() throws Exception {
    Department updatedDepartment = new Department();
    updatedDepartment.setDepartmentId(1L);
    updatedDepartment.setName("Mechanic");
    updatedDepartment.setUbication("2B");

    departmentController.update(updatedDepartment);

    this.mockMvc.perform(put("/departments")
            .contentType(MediaType.APPLICATION_JSON)
            .content(asJsonString(updatedDepartment)))
            .andExpect(status().isOk());
  }

  @Test
  public void updateNotFound() throws Exception {
    Department updatedDepartment = new Department();
    updatedDepartment.setDepartmentId(1L);
    updatedDepartment.setName("Mechanic");
    updatedDepartment.setUbication("2B");

    departmentController.update(updatedDepartment);

    this.mockMvc.perform(put("/department/10"))
            .andExpect(status().isNotFound());
  }

  @Test
  public void deleteDepartment() throws Exception {
    department = new Department();
    department.setDepartmentId(1L);
    department.setName("Technology");
    department.setUbication("2A");

    departmentController.delete(department.getDepartmentId());

    this.mockMvc.perform(delete("/departments/1"))
            .andExpect(status().isNoContent());
  }

  @Test
  public void deleteNotFound() throws Exception {
    departmentController.delete(10L);

    this.mockMvc.perform(delete("/department/10"))
            .andExpect(status().isNotFound());
  }

  public static String asJsonString(Object object) {
    try {
      return new ObjectMapper().writeValueAsString(object);
    } catch (Exception e) {
      throw new RuntimeException(e);
    }
  }

}