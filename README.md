# Page_Recognition
* [General info](#general-info)
* [Technological Stack](#technological-stack)
* [Endpoints](#endpoints)

## General info

The application recognizes the given page and returns its category.
The application is fully available in the docker service.

(The actuator only has default endpoints enabled. The other endpoints are disabled)

## Technological Stack
### Back-End
* Java (11)
* Spring-Boot
* Spring-Web
* Actuator
* ModelMapper
### Database
* PostgreSQL
### Build Tools
* Maven
* Docker

## Endpoints

```
GET -> /api?url=
```
Gets page categories.

```
POST -> /api?url=
```
Saves data about the website in the Database

```
PUTCH -> /api?url=&category=
```
Changes the categories of the saved page.

```
DELETE -> /api?url=
```
Deletes saved pages.
