package info.fanfou.db.entity;

import java.io.Serializable;
import java.util.Date;

public class Role implements Serializable {
    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.ROLE_ID
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    private Long roleId;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.ROLE_NAME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    private String roleName;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.CREATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    private Date createdDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database column ROLE.UPDATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    private Date updatedDatetime;

    /**
     * This field was generated by MyBatis Generator.
     * This field corresponds to the database table ROLE
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    private static final long serialVersionUID = 1L;

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLE
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public Role(Long roleId, String roleName, Date createdDatetime, Date updatedDatetime) {
        this.roleId = roleId;
        this.roleName = roleName;
        this.createdDatetime = createdDatetime;
        this.updatedDatetime = updatedDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ROLE
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public Role() {
        super();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.ROLE_ID
     *
     * @return the value of ROLE.ROLE_ID
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public Long getRoleId() {
        return roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.ROLE_ID
     *
     * @param roleId the value for ROLE.ROLE_ID
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public void setRoleId(Long roleId) {
        this.roleId = roleId;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.ROLE_NAME
     *
     * @return the value of ROLE.ROLE_NAME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public String getRoleName() {
        return roleName;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.ROLE_NAME
     *
     * @param roleName the value for ROLE.ROLE_NAME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public void setRoleName(String roleName) {
        this.roleName = roleName == null ? null : roleName.trim();
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.CREATED_DATETIME
     *
     * @return the value of ROLE.CREATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public Date getCreatedDatetime() {
        return createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.CREATED_DATETIME
     *
     * @param createdDatetime the value for ROLE.CREATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public void setCreatedDatetime(Date createdDatetime) {
        this.createdDatetime = createdDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method returns the value of the database column ROLE.UPDATED_DATETIME
     *
     * @return the value of ROLE.UPDATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public Date getUpdatedDatetime() {
        return updatedDatetime;
    }

    /**
     * This method was generated by MyBatis Generator.
     * This method sets the value of the database column ROLE.UPDATED_DATETIME
     *
     * @param updatedDatetime the value for ROLE.UPDATED_DATETIME
     *
     * @mbggenerated Fri Aug 21 09:03:01 CST 2015
     */
    public void setUpdatedDatetime(Date updatedDatetime) {
        this.updatedDatetime = updatedDatetime;
    }
}