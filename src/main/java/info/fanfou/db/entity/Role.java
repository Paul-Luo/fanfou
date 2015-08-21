package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    private Long roleId;

    private String roleName;

    private Date createdDatetime;

    private Date updatedDatetime;

    private static final long serialVersionUID = 1L;

    public Role(Long roleId, String roleName, Date createdDatetime, Date updatedDatetime) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    public Role() {
        super();
    }

    public Long getRoleId() {
        return roleId;
    }

    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    public String getRoleName() {
        return roleName;
    }

    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}