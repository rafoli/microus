package org.microus.authserver.controller;

import java.security.Principal;

import org.springframework.web.bind.annotation.RequestMapping;

//@RestController
public class UserController {

  @RequestMapping("/user")
  public Principal user(Principal user) {
    return user;
  }

}