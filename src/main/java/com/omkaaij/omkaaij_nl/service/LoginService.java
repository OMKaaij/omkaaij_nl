package com.omkaaij.omkaaij_nl.service;

import com.omkaaij.omkaaij_nl.model.Administrator;
import com.omkaaij.omkaaij_nl.model.Visitor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class LoginService {

    public LoginService() {
        super();
    }

    public boolean validVisitor (Visitor visitor, String password) {
        return visitor != null && visitor.getPassWord().equals(password);
    }

    public boolean validAdministrator (Administrator administrator, String password) {
        return administrator != null && administrator.getPassWord().equals(password);
    }

}
