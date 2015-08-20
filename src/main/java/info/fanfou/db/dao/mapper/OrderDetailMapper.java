package info.fanfou.db.dao.mapper;

import info.fanfou.db.entity.OrderDetail;
import info.fanfou.db.entity.OrderDetailExample;
import java.util.List;
import org.apache.ibatis.annotations.Param;

public interface OrderDetailMapper {
    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int countByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int deleteByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int deleteByPrimaryKey(Long detaileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int insert(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int insertSelective(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    List<OrderDetail> selectByExample(OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    OrderDetail selectByPrimaryKey(Long detaileId);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByExampleSelective(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByExample(@Param("record") OrderDetail record, @Param("example") OrderDetailExample example);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByPrimaryKeySelective(OrderDetail record);

    /**
     * This method was generated by MyBatis Generator.
     * This method corresponds to the database table ORDER_DETAIL
     *
     * @mbggenerated Thu Aug 20 23:39:47 CST 2015
     */
    int updateByPrimaryKey(OrderDetail record);
}