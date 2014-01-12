package org.timesheet.domain;

import org.apache.commons.lang.builder.EqualsBuilder;
import org.apache.commons.lang.builder.HashCodeBuilder;
import org.apache.commons.lang.builder.ToStringBuilder;
import org.apache.commons.lang.builder.ToStringStyle;

import javax.persistence.*;

@Entity
@Table(name = "timesheet")
public class Timesheet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "employee_Id")
    private Employee who;

    @OneToOne
    @JoinColumn(name = "task_id")
    private Task task;

    private Integer hours;

    public Timesheet() {

    }

    public Timesheet(Employee who, Task task, Integer hours) {
        this.who = who;
        this.task = task;
        this.hours = hours;
    }

    @Override
    public boolean equals(Object obj) {
        if (!(obj instanceof Timesheet))
            return false;
        Timesheet otherTimesheet = (Timesheet) obj;
        EqualsBuilder builder = new EqualsBuilder();
        builder.append(getId(), otherTimesheet.getId());
        return builder.isEquals();
    }

    public Integer getHours() {
        return hours;
    }

    public Long getId() {
        return id;
    }

    public Task getTask() {
        return task;
    }

    public Employee getWho() {
        return who;
    }

    @Override
    public int hashCode() {
        HashCodeBuilder builder = new HashCodeBuilder();
        builder.append(getId());
        return builder.hashCode();
    }

    public void setHours(Integer hours) {
        this.hours = hours;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setTask(Task task) {
        this.task = task;
    }

    public void setWho(Employee who) {
        this.who = who;
    }

    @Override
    public String toString() {
        return ToStringBuilder.reflectionToString(this, ToStringStyle.SHORT_PREFIX_STYLE);
    }
}