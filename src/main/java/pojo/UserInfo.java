package pojo;

import java.util.Date;

public class UserInfo {
    private Integer id;

    private Date datetime;

    private String userid;

    private String passwd;

    private String useridMd5;

    private String clientIp;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getDatetime() {
        return datetime;
    }

    public void setDatetime(Date datetime) {
        this.datetime = datetime;
    }

    public String getUserid() {
        return userid;
    }

    public void setUserid(String userid) {
        this.userid = userid == null ? null : userid.trim();
    }

    public String getPasswd() {
        return passwd;
    }

    public void setPasswd(String passwd) {
        this.passwd = passwd == null ? null : passwd.trim();
    }

    public String getUseridMd5() {
        return useridMd5;
    }

    public void setUseridMd5(String useridMd5) {
        this.useridMd5 = useridMd5 == null ? null : useridMd5.trim();
    }

    public String getClientIp() {
        return clientIp;
    }

    public void setClientIp(String clientIp) {
        this.clientIp = clientIp == null ? null : clientIp.trim();
    }
}