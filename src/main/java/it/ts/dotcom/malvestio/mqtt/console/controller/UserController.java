package it.ts.dotcom.malvestio.mqtt.console.controller;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import it.ts.dotcom.malvestio.mqtt.console.business.UserBusiness;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "controller per utente", description = "esempi utente")
@RestController
@RequestMapping("/users")
public class UserController {

    private static final Logger logger = Logger.getLogger(UserController.class);
    
    @Autowired
    UserBusiness userBusiness;

    @ApiOperation(value = "Esempio di connessione db")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public String get(@ApiParam(name = "id", value = "The id of the user", required = true) @PathVariable Integer id) {
        logger.trace("controller.get()");
        logger.info("ciao dal logger " + Math.random());

        return "TODO";
    }

}
