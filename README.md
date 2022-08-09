Overview
MockFlix is a Netflix clone platform made for a school project. This platform allows users
to check out actual trending movies, sorted by their genres and with a section dedicated
to the top 10 most popular movies on the platform.

MockFlix does not let users stream the movies, but rather implements a 'mock' feature
where it redirects users to a YouTube video when pressing the Play button for each of the
movies.
The project includes a complete application cycle; from user authentification to
participating in features applied to the shown movies.

Frontend: See other repository's README file... 

Backend
The project backend functionalities were divided into two separate projects:

A JAVA backend, implementing various JAVA libraries such as JPA, JUNIT, and
SPRINGBOOT, as well as a connection to a PostgreSQL Database. This SQL
database handles the user authentification side of the application, as well as storing
the platform's movies. 2 REST APIs were built in this part, with CRUD requests being
implemented and used by the platform: One API for the User features, and one for the
MOVIES.

A NodeJS backend: See other repository's README file...
