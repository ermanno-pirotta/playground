# apache-wordpress-tomcat

## Description

The content of this directory shows how to setup an Apache configuration that serves wordpress and proxies some request to a tomcat server.

A typical scenario is when you want to have custom business logic built on java technologies and running on tomcat, and your cms and static content managed in wordpress.

The setup is pretty straightforward:
- wordpress is served on the http port 81 instead of 80 by the apache server.
- tomcat listen to the 8009 port
- apache listen on the port 80

![Alt text](network_infrastructure.png?raw=true “main setup“)

When a request comes, based on the url of the request apache proxies it to tomcat or serves wordpress content.

The example configuration in this project is setup to serve all content under /blog as wordpress content, while the root of the site being served by Tomcat.
  

## Content

- wp.conf   : basic configuration for serving wordpress on port 81
- site.conf : main configuration of the site, where the proxy rules are defined

## Requirements
- [apache mod_rewrite](http://httpd.apache.org/docs/current/mod/mod_rewrite.html)
- [apache mod_proxy_ajp](https://httpd.apache.org/docs/2.4/mod/mod_proxy_ajp.html)
