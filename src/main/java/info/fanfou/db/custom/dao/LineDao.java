package info.fanfou.db.custom.dao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

/**
 * author : lch
 * date : 2015/4/10
 * depiction :
 */
@Repository
public class LineDao {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public List<Map<String, Object>> selectLinePath(final Integer lineNumber) {
        String sql = "SELECT * FROM LINE l LEFT JOIN STATION s ON l.station_id = s.id WHERE l.LINE_NUMBER = ? ORDER BY arrival_time";
        return jdbcTemplate.queryForList(sql, new Object[]{lineNumber});
    }


    public List<Map<String, Object>> selectAllLinePath() {
        String sql = "SELECT * FROM LINE l LEFT JOIN STATION s ON l.station_id = s.id ORDER BY  l.line_number, arrival_time";
        return jdbcTemplate.queryForList(sql);
    }
}
