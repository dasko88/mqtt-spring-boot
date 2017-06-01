package hello.business;

import hello.bean.User;
import hello.model.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Component
public class UserBusiness {

    private static Logger logger = Logger.getLogger(UserBusiness.class);

    @Autowired
    UserMapper userMapper;

    public User get(int i) {
        logger.trace("business.get()");

        return userMapper.get(i);
    }

}
