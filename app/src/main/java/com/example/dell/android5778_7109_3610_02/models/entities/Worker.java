package com.example.dell.android5778_7109_3610_02.models.entities;

/**
 * Created by DELL on 09 ינואר 2018.
 */

public class Worker {

    private String username;
    private String password;

    public Worker(String name, String password) {
        this.username = name;
        this.password = password;
    }

    public String getName() {
        return username;
    }

    public void setName(String name) {
        this.username = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Worker worker = (Worker) o;

        if (username != null ? !username.equals(worker.username) : worker.username != null)
            return false;
        return getPassword() != null ? getPassword().equals(worker.getPassword()) : worker.getPassword() == null;
    }

    @Override
    public int hashCode() {
        int result = username != null ? username.hashCode() : 0;
        result = 31 * result + (getPassword() != null ? getPassword().hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Worker{" +
                "username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
