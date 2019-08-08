# Eatfood
## Calculates daily ration
Project for consolidating skills, but despite that non-production aim, is viable
### Goals
##### Project itself:
* Make calculation of basic nutrients easier
* Give pleasant interface
* Save state on server
##### Selfstudying:
* Get expierence of usage *Spring MVC, Hibernate, ReactJS*
### Arhitecture
Project is based on **Spring MVC**, which shifts transactional responsabilities to **Hibernate ORM**.
*Hibernate ORM* itself deals with *PostgreSQL* on deploy, and with *H2* database on testing.
Basic service classes are covered by tests under **Junit Jupiter Framework**.
Front-end part has been wrotten within **ReactJS** library. 
There is **no** *JSP* or *Thymeleaf* at all. Instead all rendering responsibilitie sis laied on *ReactJS*.
Hence communication between server-side and client-side based upon REST with JSON as base message data format.
![entity chart](https://github.com/belenot/eatfood/blob/master/misc/fa_diagram "Entities")

Front-end part is located in own [repository](https://github.com/belenot/eatfood-frontend). In that repository is README.md which holds screenshots
