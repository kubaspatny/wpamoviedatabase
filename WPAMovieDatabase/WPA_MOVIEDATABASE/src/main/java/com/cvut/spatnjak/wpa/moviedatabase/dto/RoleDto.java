/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package com.cvut.spatnjak.wpa.moviedatabase.dto;

/**
 *
 * @author Kuba
 */
public class RoleDto {
    
    public String role_name;
    public BasicActorDto actor;

    public RoleDto() {
    }

    public RoleDto(String role_name, BasicActorDto actor) {
        this.role_name = role_name;
        this.actor = actor;
    }

    public String getRole_name() {
        return role_name;
    }

    public void setRole_name(String role_name) {
        this.role_name = role_name;
    }

    public BasicActorDto getActor() {
        return actor;
    }

    public void setActor(BasicActorDto actor) {
        this.actor = actor;
    }
    
}
