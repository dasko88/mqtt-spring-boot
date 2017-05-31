package hello.controller;

import hello.bean.User;
import hello.model.mapper.UserMapper;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@Api(value = "controller per utente", description = "esempi utente")
@RestController
@RequestMapping("/users")
public class UserController {

    @Autowired
    UserMapper userMapper;

    @ApiOperation(value = "Esempio di connessione db")
    @RequestMapping(value = "/{id}", method = RequestMethod.GET, produces = "application/json")
    public User get(@ApiParam(name = "id", value = "The id of the user", required = true) @PathVariable Integer id) {
        System.out.println("user()");

        User user = userMapper.get(id);

        return user;
    }

    @ApiOperation(value = "Esempio POST")
    @RequestMapping(method = RequestMethod.POST, consumes = "application/json", produces = "application/json")
    public User post(@ApiParam(name = "user", value = "The new user", required = true) @RequestBody User user) {
        System.out.println("user()" + user);
        user.setId(user.getId() * 10);
        return user;
    }

}
