# JWT-Tokenization-in-Spring-Boot-using-Spring-Security
JWT Token is used for Authentication and Authorization

For safe and secure authetication and authoriation betweeen two web-services JWT is mostly used.
This is the code where you can access the services with the valid JWT token.
There is a jar which i have generated of the project and uploaded with the project, you can download it and check how it works.

-> Double click on the jar and open it with the Java SE Library

-> Now open the postman and do a post request as below
http://localhost:9090/authenticate
and headers parameter should contain as 

Key         =              Value

===                       =====

Content-Type=              application/json

username    =              akash

password    =              akash

it will give a respose as:-

{
    "jwt": "eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTU4NzA1MDgyMCwiaWF0IjoxNTg3MDE0ODIwfQ.bHrMeAZAEzly-aR1h-IXSRORySaq8FnAh2fy8Ia3ujY"
}

then you need to pass that token for authorization

-> Now for accessing the "/hello" service data for ex:- http://localhost:9090/hello , it can be anything
you need to pass the jwt token with the given URL below
http://localhost:9090/hello as a GET request send along headers parameter

Key         =              Value

===                       =====

Content-Type=              application/json

Authorization = auth=eyJhbGciOiJIUzI1NiJ9.eyJzdWIiOiJha2FzaCIsImV4cCI6MTU4NzA1MDgyMCwiaWF0IjoxNTg3MDE0ODIwfQ.bHrMeAZAEzly-aR1h-IXSRORySaq8FnAh2fy8Ia3ujY

here "auth" is the identifier for the token you can give anthing you want
after GET request it will give a respose as:-
HELLO WORLD

This is a example about how you can develop security for services

I would like to thank Koushik from Java brains for this wonderful knowledge.
