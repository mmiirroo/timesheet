package org.timesheet.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Entity
@Table(name = "task")
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "task_employee", joinColumns = {@JoinColumn(name = "task_id")}, inverseJoinColumns = {@JoinColumn(name = "employee_id")})
    private List<Employee> assignedEmployees = new ArrayList<Employee>();

    @OneToOne
    @JoinColumn(name = "manager_id")
    private Manager manager;

    private boolean completed;

    private String description;

    public Task() {

    }

    public Task(String description, Manager manager, Employee... assignedEmployees) {
        this.manager = manager;
        this.assignedEmployees.addAll(Arrays.asList(assignedEmployees));
        this.completed = false;
        this.description = description;
    }

    public void addEmployee(Employee employee) {
        assignedEmployees.add(employee);
    }

    public void removeEmployee(Employee employee) {
        assignedEmployees.remove(employee);
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Task)) {
            return false;
        }
        Task otherTask = (Task) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getId(), otherTask.getId());
        return builder.isEquals();
    }

    public List<Employee> getAssignedEmployees() {
        return assignedEmployees;
    }

    public String getDescription() {
        return description;
    }

    public Long getId() {
        return id;
    }

    public Manager getManager() {
        return manager;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
    }

    public boolean isCompleted() {
        return completed;
    }

    public void setAssignedEmployees(List<Employee> assignedEmployees) {
        this.assignedEmployees = assignedEmployees;
    }

    public void setCompleted(boolean completed) {
        this.completed = completed;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setManager(Manager manager) {
        this.manager = manager;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}
