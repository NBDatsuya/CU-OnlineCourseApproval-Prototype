package ags.edu.cu.oca.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.UUID;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class User {
    private String uuid;
    private String uName;
    private String realName;
    private String password;
    private String role;

    public String getRoleText(){
        return parseRole(role);
    }
    public static String parseRole(String role){
        switch (role){
            case "stu":
                return "学生";
            case "tea":
                return "教师";
            case "adm":
                return "管理员";
            default:
                return "未知";
        }
    }
}
