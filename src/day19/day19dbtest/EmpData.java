package day19.day19dbtest;

import java.sql.Date;

public class EmpData {
    String first_name;
    String last_name;
    int salary;
    Date hire_date;

    public EmpData(String first_name, String last_name, int salary, Date hire_date) {
        this.first_name = first_name;
        this.last_name = last_name;
        this.salary = salary;
        this.hire_date = hire_date;
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

    public int getSalary() {
        return salary;
    }

    public void setSalary(int salary) {
        this.salary = salary;
    }

    public Date getHire_date() {
        return hire_date;
    }

    public void setHire_date(Date hire_date) {
        this.hire_date = hire_date;
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("EmpData{");
        sb.append("first_name='").append(first_name).append('\'');
        sb.append(", last_name='").append(last_name).append('\'');
        sb.append(", salary=").append(salary);
        sb.append(", hire_date=").append(hire_date);
        sb.append('}');
        return sb.toString();
    }
}
