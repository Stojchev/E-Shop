package com.example.eshopapplication.entity.Enum;

import lombok.*;
import org.springframework.security.core.GrantedAuthority;

import javax.persistence.*;
import javax.persistence.Table;
import java.util.List;



public enum Role implements GrantedAuthority
{
    USER,
    ADMIN;

    @Override
    public String getAuthority() {
        return name();
    }
}