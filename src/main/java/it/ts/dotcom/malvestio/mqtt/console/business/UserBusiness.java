package it.ts.dotcom.malvestio.mqtt.console.business;

import it.ts.dotcom.malvestio.mqtt.console.model.mapper.UserMapper;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 *
 * @author David
 */
@Component
public class UserBusiness {

    private static final Logger logger = Logger.getLogger(UserBusiness.class);

    @Autowired
    UserMapper userMapper;

    public String get(int i) {
        logger.trace("business.get()");

        return "TODO";
    }

}
