package info.fanfou.db.dao.mapper;

import info.fanfou.db.entity.User;
import info.fanfou.db.entity.UserExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface UserMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int countByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int deleteByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int deleteByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int insert(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int insertSelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    List<User> selectByExample(UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    User selectByPrimaryKey(Long userId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByExampleSelective(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByExample(@Param("record") User record, @Param("example") UserExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByPrimaryKeySelective(User record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table USER
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByPrimaryKey(User record);
}