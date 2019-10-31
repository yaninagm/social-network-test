# social-network-test
Any exercise and good practice

![Classes Diagram](https://github.com/yaninagm/social-network-test/blob/develop/src/main/resources/relationship.png)

## EndPoints
### POST /friendship/request
?usernameFrom=#usernameFrom&usernameTo=#usernameTo -H X-Password:

### POST /friendship/accept
?usernameFrom=#usernameFrom&usernameTo=#usernameTo -H X-Password:

### POST /friendship/decline
?usernameFrom=#usernameFrom&usernameTo=#usernameTo -H X-Password:

### GET /friendship/list
?username=#username -H X-Password:

### POST /signup
?username=#username  -H X-Password:

## Build & Run

`./gradlew bootRun`