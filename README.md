<div id="top"></div>

<br />
<div align="center">
  <img src="https://i.imgur.com/42m4MaA.png" alt="Project Logo" height="150">
  <h1 align="center">Find a Dev - Backend</h1>
  <p align="center">
    A system that connects people in a Tinder-style experience.
  </p>
  <img align="center" src="https://github.com/luiz-matias/find-a-dev-backend/actions/workflows/maven.yml/badge.svg" alt="Tests status">
</div>

# Project Features

The application will have the following features:
## Authentication

### Login
As a user, I want to be able to login into the application, so I can access my account with safety.

### Registration
As a user, I want to register my account into the application, so I can have access to the platform.

### Password Recovery
As a user, I want to be able to recover my password in case I missed it so that I can recover my access to the platform.

## Account Management

### Edit Profile
As a user, I want to be able to edit my account information, so I can keep my profile updated.

### Change Password
As a user, I want to be able to change my password, so I can protect my account whenever I need to.

### Delete Account
As a user, I want to be able to delete my account, so I can remove all my sensitive data from the platform.

## Finding and Matching Users

### List of Devs
As a client, I want to list all devs according to the needs of my project (filters to be defined).

### List of Clients
As a dev, I want to list all clients according to my formation, experience, and desired preferences (filters to be defined).

### Match
As a user, when I give a like to another user and they like me as well, I want to match with that user so I can access their info.

## Messaging

### Chat between Devs and Clients
As a user, I want to chat with another user about the project we will implement.

## Admin Management

### List users
As an admin, I want to fetch a list of users of my platform.
___
# Main Tools

## [Spring Boot](https://spring.io/projects/spring-boot)

<div align="center">
  <img src="https://i.imgur.com/eB4qk7Z.png" alt="Spring Boot" height="150">
</div>

<br />

Spring Boot is an opinionated extension from Spring that allows us to develop backend services in a fast-paced rhythm.

# Roadmap
## Basic Business Rules
- [X] Project Setup
  - [X] Readme About the Project
  - [X] List of epics and stories
  - [X] Environment Preparation
- [X] Authentication
  - [X] Login
  - [X] Email activation
  - [X] Registration
  - [X] Password Recovery
- [X] Account Management
  - [X] Edit Profile
  - [X] Change Password
  - [X] Delete Account
- [ ] Finding and Matching Users
  - [ ] List of Devs
  - [ ] List of Clients
  - [X] Match
- [X] Messaging
  - [X] Chat between Devs and Clients

## Extended Business Rules
- [ ] Media
  - [ ] User profile picture
  - [ ] Chat image upload
  - [ ] Chat video upload
  - [ ] Chat PDF upload
- [ ] Enhanced profiles
  - [ ] Developer
    - [ ] Skills
      - [ ] Area-specific: Mobile, Front-end, Back-end, CLI, Embedded, etc.
      - [ ] Stack-specific: React, Flutter, Android Native, etc.
    - [ ] Formation
    - [ ] Social links
    - [ ] Areas of interest
  - [ ] Client
    - [ ] Skills needed
    - [ ] Areas of interest

## Tech Features Wishlist
- [X] Security
  - [X] Authentication via JWT
  - [X] Strong password requirements
  - [X] Password encryption
  - [X] Role-based routes
- [X] Email services
  - [X] Account activation
  - [X] Password change
  - [X] Password recovery
  - [X] Password changed confirmation
  - [ ] Match notification
- [ ] Listing basics
  - [ ] Pagination
  - [ ] Sorting
  - [ ] Filtering
  - [ ] Search
- [ ] CI/CD
  - [ ] PR pipeline
    - [ ] Run lint
    - [X] Run tests
    - [ ] Code coverage analysis
  - [ ] Staging pipeline
    - [ ] Automated deployment in staging environment
    - [ ] Clone production data everyday
  - [ ] Production pipeline
    - [ ] Automated deployment in production environment
    - [ ] Automated database backups everyday
- [ ] Database
  - [ ] Migrations
  - [ ] Pre-populate data for dev environments
- [ ] Analytics
  - [ ] Logging
  - [ ] Analytics Dashboard
  - [ ] Error Dashboard
- [ ] CDN
  - [ ] User profile picture
  - [ ] Chat images
  - [ ] Chat videos
  - [ ] Thumbnails

# License

Distributed under the MIT License. See `LICENSE` for more information.


# Contact

Luiz Matias - [LinkedIn](https://www.linkedin.com/in/luizmatiasdev/) - contact@luizmatias.com
