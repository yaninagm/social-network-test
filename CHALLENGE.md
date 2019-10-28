# Schibsted Spain Backend Coding Challenge

Welcome to our coding challenge, hope you enjoy it!

We need your help migrating a legacy social network service.

This service have users that can friend other users and offers an HTTP API to do so.

## Requirements

The use cases that need to be implemented are:

* Sign up
  * A new user requests to register to our service, providing its username and password.
  * Username must be unique, from 5 to 10 alphanumeric characters.
  * Password from 8 to 12 alphanumeric characters.
* Request friendshipRequest
  * A registered user requests friendshipRequest to another registered user.
  * A user cannot request friendshipRequest to himself or to a user that already has a pending request from him.
* Accept friendshipRequest
  * A registered user accepts a requested friendshipRequest.
  * Once accepted both users become friends forever and cannot request friendshipRequest again.
* Decline friendshipRequest
  * A registered user declines a requested friendshipRequest.
  * Once declined friendshipRequest can be requested again.
* Friends
  * List friends of a registered user.
  

There is one drawback, we have to maintain the API of the legacy service, which is awful but nothing we can do 😞.
We hope we can refactor the API in a future iteration, **but not in this challenge**.

So we provide you with an initial implementation of controllers under package `com.schibsted.spain.friends.legacy` that fulfill the legacy API and you can work from there.

The legacy team has provided us with a script that you can execute to check if your implementation is on the right path.
Just execute `bash -c scripts/legacy-test` while your service is running and expect all checks to pass.
This script uses `bash` and should work just fine in macOs and linux. In windows you can use [git-bash](https://gitforwindows.org/).

No database needed, you can persist everything in memory.

We expect from you to apply good practices and be proud of what you do. Good luck!

## Build & Run

`./gradlew bootRun`
