package info.fanfou.db.custom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Date;

/**
 * author : lch
 * date : 2015/4/11
 * depiction :
 */
@Repository
public class FeedBackDao {
    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int saveFeedBack(String content, String ip) {
        String sql = "INSERT INTO FEEDBACK(create_date, content, ip) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, new Object[] {new Date(), content, ip});
    }
}
