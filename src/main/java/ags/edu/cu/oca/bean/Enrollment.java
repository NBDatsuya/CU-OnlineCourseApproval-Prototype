package ags.edu.cu.oca.bean;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;


@Data
@AllArgsConstructor
@NoArgsConstructor
public class Enrollment {
    private String uuid;
    private Course course;
    private User applicant;
    private User examineUser;
    private String status;
    private String aDate;
    private String eDate;

    public String getStatusText(){
        switch (status){
            case "approved":
                return "通过";
            case "refused":
                return "不通过";
            case "submitted":
                return "待审批";
            default:
                return "未知状态";
        }
    }
}
