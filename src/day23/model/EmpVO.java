package day23.model;

import java.sql.Date;

//DTO(Data Transfer Object)
//VO(Value Object)
public class EmpVO {
    private int employee_id;
    private String first_name;
    private String last_name;
    private String email;
    private String phone_number;
    private Date hire_date;
    private String job_id;
    private int salary;
    private double commission_pct;
    private int manager_id;
    private int department_id;

    public EmpVO() {
    }

    public EmpVO(int employee_id, String last_name, String email, Date hire_date, String job_id) {
        this.employee_id = employee_id;
        this.last_name = last_name;
        this.email = email;
        this.hire_date = hire_date;
        this.job_id = job_id;
    }

    public EmpVO(int employee_id, String first_name, String last_name, String email, String phone_number, Date hire_date, String job_id, int salary, double commission_pct, int manager_id, int department_id) {
        this.employee_id = employee_id;
        this.first_name = first_name;
        this.last_name = last_name;
        this.email = email;
        this.phone_number = phone_number;
        this.hire_date = hire_date;
        this.job_id = job_id;
        this.salary = salary;
        this.commission_pct = commission_pct;
        this.manager_id = manager_id;
        this.department_id = department_id;
    }

    public int getEmployee_id() {
        return employee_id;
    }

    public void setEmployee_id(int employee_id) {
        this.employee_id = employee_id;
    }

    public String getFirst_name() {
        return first_name;
    }

    public void setFirst_name(String first_name) {
        this.first_name = first_name;
    }

    public String getLast_name() {
        return last_name;
    }

    public void setLast_name(String last_name) {
        this.last_name = last_name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhone_number() {
        return phone_number;
    }

    public void setPhone_number(String phone_number) {
        this.phone_number = phone_number;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    public String getJob_id() {
        return job_id;
    }

    public void setJob_id(String job_id) {
        this.job_id = job_id;
    }

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public double getCommission_pct() {
        return commission_pct;
    }

    public void setCommission_pct(double commission_pct) {
        this.commission_pct = commission_pct;
    }

    public int getManager_id() {
        return manager_id;
    }

    public void setManager_id(int manager_id) {
        this.manager_id = manager_id;
    }

    public int getDepartment_id() {
        return department_id;
    }

    public void setDepartment_id(int department_id) {
        this.department_id = department_id;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmpVO ???????????? {");
        sb.append("employee_id=").append(employee_id);
        sb.append(", first_name='").append(first_name).append('\'');
        sb.append(", last_name='").append(last_name).append('\'');
        sb.append(", email='").append(email).append('\'');
        sb.append(", phone_number='").append(phone_number).append('\'');
        sb.append(", hire_date=").append(hire_date);
        sb.append(", job_id='").append(job_id).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", commission_pct=").append(commission_pct);
        sb.append(", manager_id=").append(manager_id);
        sb.append(", department_id=").append(department_id);
        sb.append('}');
        return sb.toString();
    }
}
