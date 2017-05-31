package hello.bean;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

/**
 *
 * @author David
 */
@ApiModel
public class User {

    @ApiModelProperty(position = 1, dataType = "java.lang.Integer", example = "1")
    private Integer id;
    @ApiModelProperty(position = 2, dataType = "java.lang.String", example = "mario@gmail.it")
    private String email;

    public User() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Override
    public String toString() {
        return "User{" + "id=" + id + ", email=" + email + '}';
    }

}
