package com.ujiuye.bean;

import java.util.Date;

/**
 * 学生实体类
 */
public class Student {
    private Integer id;
    private String sname;
    private String gender;
    private Date birthday;
    private String hobby;
    private String photo;

    public Student() {
    }

    public Student(String sname, String gender, Date birthday, String hobby, String photo) {
        this.sname = sname;
        this.gender = gender;
        this.birthday = birthday;
        this.hobby = hobby;
        this.photo = photo;
    }

    public Student(String sname, String gender) {
        this.sname = sname;
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Student{" +
                "id=" + id +
                ", sname='" + sname + '\'' +
                ", gender='" + gender + '\'' +
                ", birthday=" + birthday +
                ", hobby='" + hobby + '\'' +
                ", photo='" + photo + '\'' +
                '}';
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSname() {
        return sname;
    }

    public void setSname(String sname) {
        this.sname = sname;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public Date getBirthday() {
        return birthday;
    }

    public void setBirthday(Date birthday) {
        this.birthday = birthday;
    }

    public String getHobby() {
        return hobby;
    }

    public void setHobby(String hobby) {
        this.hobby = hobby;
    }

    public String getPhoto() {
        return photo;
    }

    public void setPhoto(String photo) {
        this.photo = photo;
    }
}
