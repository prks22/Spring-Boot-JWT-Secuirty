# Spring-Boot-JWT-Secuirty
This project of spring boot application contain layer base architecture and JWT security implementation.

This project contains the code for secuirty of springboot application which generally using authencation and authorization relam.

First step is to generate the JWT token when user name and password match, for this there is getToken() method of TokenAuthentication class which is triggerd by "/usermang/v1/login" url path.
After obtaining token you can access any service using this token.
ex- Authorization Bearer xxxxxxxxxxxxxxxxxxxxxxxxxTokenXXXXXXXXXXXXXXXXXXXXX
