package com.daomingedu.talentgame.mvp.model.entity;

import java.io.Serializable;

/**
 * Description
 * Created by jianhongxu on 4/14/21
 */
public class UploadVideoAppPage implements Serializable {


    String APIUrl;
    String testSignId;
    String studentId;
    Integer limitNum;
    Integer isShowImport; //是否开启导入本地视频（1是0否）
    String folderName;//视频保存的文件夹名 False(为null则业务不变，统一默认文件夹内)

    public Integer getIsShowImport() {
        return isShowImport;
    }

    public void setIsShowImport(Integer isShowImport) {
        this.isShowImport = isShowImport;
    }

    public String getAPIUrl() {
        return APIUrl;
    }

    public String getFolderName() {
        return folderName;
    }

    public void setFolderName(String folderName) {
        this.folderName = folderName;
    }

    public void setAPIUrl(String APIUrl) {
        this.APIUrl = APIUrl;
    }

    public String getTestSignId() {
        return testSignId;
    }

    public void setTestSignId(String testSignId) {
        this.testSignId = testSignId;
    }

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public Integer getLimitNum() {
        return limitNum;
    }

    public void setLimitNum(Integer limitNum) {
        this.limitNum = limitNum;
    }
}
