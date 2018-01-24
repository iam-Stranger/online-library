package by.loiko.library.controller;

import by.loiko.library.command.PageConstant;

/***
 Author: Aliaksei Loika
 Date: 29.12.2017
 ***/
public class Router {
    public enum RouteType {
        FORWARD, REDIRECT
    }

    private String pagePath = PageConstant.INDEX_PAGE;
    private RouteType routeType;

    public Router() {
        routeType = RouteType.FORWARD;
    }

    public Router(RouteType routeType) {
        this.routeType = routeType;
    }

    public String getPagePath() {
        return pagePath;
    }

    public void setPagePath(String pagePath) {
        this.pagePath = pagePath;
    }

    public RouteType getRouteType() {
        return routeType;
    }

    public void setRouteType(RouteType routeType) {
        ////  check null??
        this.routeType = routeType;
    }
}
