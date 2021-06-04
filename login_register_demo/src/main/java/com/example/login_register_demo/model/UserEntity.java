package com.example.login_register_demo.model;

import com.fasterxml.jackson.annotation.JsonManagedReference;

import javax.persistence.*;
import javax.validation.constraints.*;
import java.util.List;
import javax.persistence.Column;

@Entity
@Table(name = "userentity")
public class UserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "userEntity_id")
    private Long id ;

    @NotBlank(message = "Không được để trống")
    @Size(min = 3,max = 30 ,message = "Ký tự từ 3 đến 30")
    private String userName ;

    @NotNull(message = "Không được để trống")
    private String passWord;

    private String roleName ;


    @Email(message = "Nhập sai định dạng")
    private String email ;

    @Pattern(regexp="(^$|[0-9]{10})",message = "Số điện thoại có 10 chữ số")
    private String phoneNumber ;

    @NotBlank(message = "Không được để trống")
    private String name ;

    private String address ;

    @OneToMany(mappedBy="userEntity",cascade = CascadeType.ALL)
    @JsonManagedReference
    private List<Note> note;

    public UserEntity() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getPassWord() {
        return passWord;
    }

    public void setPassWord(String passWord) {
        this.passWord = passWord;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPhoneNumber() {
        return phoneNumber;
    }

    public void setPhoneNumber(String phoneNumber) {
        this.phoneNumber = phoneNumber;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public List<Note> getNote() {
        return note;
    }

    public void setNote(List<Note> note) {
        this.note = note;
    }
}
