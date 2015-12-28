package info.fanfou.common;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.SimpleMappingExceptionResolver;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

/**
 * author : chaoluo
 * date : 2015/6/5
 * depiction :
 */
public class CustomSimpleMappingExceptionResolver extends SimpleMappingExceptionResolver {


    private Logger logger = LoggerFactory.getLogger(CustomSimpleMappingExceptionResolver.class);

    public ModelAndView doResolveException(HttpServletRequest request, HttpServletResponse response, Object handler,Exception ex) {
        logger.error("error", ex);
        String errorCode = "";
        String errorMsg = "";
        int status = 409;
        errorMsg = ex.getMessage();
        assemblyGeneralResponse(response, errorMsg, errorCode, status);
        return null;
    }

    private void assemblyGeneralResponse(HttpServletResponse response, String errorMsg, String errorCode, int status) {
        Map<String, Object> responseMsg = new HashMap<String, Object>();
        responseMsg.put("msg", errorMsg);
        responseMsg.put("success", Boolean.FALSE);
        responseMsg.put("errCode", errorCode);
        ObjectMapper mapper = new ObjectMapper();
        try {
            response.setContentType("application/json;charset=utf-8");
            response.setStatus(status);
            PrintWriter writer = response.getWriter();
            writer.write(mapper.writeValueAsString(responseMsg));
            writer.flush();
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
