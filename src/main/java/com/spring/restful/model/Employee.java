package com.spring.restful.model;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;


@Entity
@Table(name = "employees")
@Getter
@Setter
@ToString
@EqualsAndHashCode
@NoArgsConstructor
@AllArgsConstructor
public class Employee {
    @Id
    private Long id;
    @NotNull @NotBlank @Size(min=2, max=30)
    private String name;
    @NotNull @NotBlank
    private String role;
    @NotNull(message = "Age argument is required") @Min(18) @Max(60)
    private Integer age; 
    
    // public Employee() {
    // }

    public Employee(String name, String role, Integer age) {
        this.name = name;
        this.role = role;
        this.age = age;
    }

    // public Long getId() {
    //     return this.id;
    // }

    // public String getName() {
    //     return this.name;
    // }

    // public String getRole() {
    //     return this.role;
    // }

    // public Integer getAge() {
    //     return this.age;
    // }

    // public void setId(Long id) {
    //     this.id = id;
    // }

    // public void setName(String name) {
    //     this.name = name;
    // }

    // public void setRole(String role) {
    //     this.role = role;
    // }

    // public void setAge(Integer age) {
    //     this.age = age;
    // }

    // @Override
    // public boolean equals(Object o) {

    //     if (this == o)
    //         return true;
    //     if (!(o instanceof Employee))
    //         return false;
    //     Employee employee = (Employee) o;
    //     return Objects.equals(this.id, employee.id) && Objects.equals(this.name, employee.name)
    //             && Objects.equals(this.role, employee.role) && Objects.equals(this.age, employee.age);
    // }

    // @Override
    // public int hashCode() {
    //     return Objects.hash(this.id, this.name, this.role, this.age);
    // }

    // @Override
    // public String toString() {
    //     return "Employee{" + "id=" + this.id + ", name='" + this.name + '\'' + ", role='" + this.role + '\'' + "age=" + this.id + '}';
    // }
}