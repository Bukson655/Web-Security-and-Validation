# Web app with Spring Security and Validation

It's just simple mechanism of registration, logging in/out, 
editing existing user data in db. There's also validation of fields in entity and dto objects 
supported by basic validation in HTML and db. 

## Usage

You can start it with You IDE. App works on a localhost.
This code has been written just for exercising.

```
H2 database is already configured for:
url: jdbc:h2:mem:test
user: sa
leave password field empty

Database and test data are created with Liquibase.
```

## Features

* Register user (+ validation)
* Log in/out 
* Admins can promote users for admin or degrade admins.
* User can edit personal info (+ validation)

## Author

Sławomir Błaszkiewicz