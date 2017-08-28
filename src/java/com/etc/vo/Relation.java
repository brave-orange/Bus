/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package com.etc.vo;

/**
 *
 * @author yongcheng
 */
public class Relation {
    private String stop;
    private String route;
    private String position;

    public Relation(String stop, String route, String position) {
        this.stop = stop;
        this.route = route;
        this.position = position;
    }

    public String getStop() {
        return stop;
    }

    public void setStop(String stop) {
        this.stop = stop;
    }

    public String getRoute() {
        return route;
    }

    public void setRoute(String route) {
        this.route = route;
    }

    public String getPosition() {
        return position;
    }

    public void setPosition(String position) {
        this.position = position;
    }
    
    
}
