package com.example.TransactionSystem.empty;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import org.springframework.stereotype.Component;

import java.io.Serializable;

@Data
@Component
public class User implements Serializable {
    private String use_name;
    private String use_password;
    private String use_ActualName;
    private String use_age;
    private String use_address;
    private String use_email;
    private String use_uid;
    private String use_ipcard;
    private  String use_time;
    private  String use_sex;

    public User(){

    }
    @JsonCreator
    public User(@JsonProperty("use_name")String use_name,@JsonProperty("use_password")String use_password){
        this.use_name = use_name;
        this.use_password = use_password;
    }
    @JsonCreator
    public User(
            @JsonProperty("use_id") int use_id,
            @JsonProperty("use_name") String use_name,
            @JsonProperty("use_password") String use_password ,
            @JsonProperty("use_ActualName") String use_ActualName,
            @JsonProperty("use_age") String use_age,
            @JsonProperty("use_address") String use_address,
            @JsonProperty("use_email") String use_email,
            @JsonProperty("use_ipcard") String use_ipcard,
            @JsonProperty("use_time") String use_time,
            @JsonProperty("use_uid") String use_uid,
            @JsonProperty("use_sex") String use_sex){
        this.use_name = use_name;
        this.use_password = use_password;
        this.use_ActualName = use_ActualName;
        this.use_age = use_age;
        this.use_address = use_address;
        this.use_email = use_email;
        this.use_ipcard =use_ipcard;
        this.use_time = use_time;
        this.use_sex = use_sex;
    }


}
