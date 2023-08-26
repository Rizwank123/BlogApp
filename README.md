# Blog Backend System

Welcome to the Blog Backend System project! This project serves as the backend for a blog management system.

## Table of Contents

- [Introduction](#introduction)
- [Technologies](#technologies)
- [Features](#features)
- [Prerequisites](#prerequisites)
- [Installation](#installation)
- [Usage](#usage)
- [API Endpoints](#api-endpoints)
- [Database](#database)
- [Contributing](#contributing)
- [License](#license)

## Introduction

The Blog Backend System is designed to provide a backend solution for managing a blog. It offers user management, post creation and management, comment functionality, and more.

## Technologies

- Java
- Spring Boot
- MySQL
- Maven

## Features

- User Management: Create, update, and delete users.
- Post Management: Create, update, and delete posts.
- Comment Management: Create comments for posts.
- Advanced Queries: Get posts by user ID, category, or all posts.
- Security: Utilizes Spring Security for secure authentication and authorization.
- Data Transfer Objects (DTOs): Use DTOs to decouple data between layers.
- ModalMapper: A library used for mapping objects efficiently.

## Prerequisites

- Java Development Kit (JDK)
- MySQL Database
- Maven

## Installation

1. Clone the repository: `git clone https://github.com/yourusername/blog-backend.git`
2. Open the project in Spring Tool Suite (STS) or your preferred IDE.
3. Configure the MySQL database connection in `application.properties`.
4. Run the application using Maven: `mvn spring-boot:run`

## Usage

1. Use Postman to interact with the API endpoints.
2. Register users and obtain JWT tokens for authentication.
3. Create, update, or delete posts.
4. Add comments to posts.

## API Endpoints

- `POST /api/auth/signup`: Register a new user.
- `POST /api/auth/signin`: Sign in and get JWT token.
- `POST /api/posts`: Create a new post.
- `GET /api/posts`: Get all posts.
- `GET /api/posts/{postId}`: Get post by ID.
- `PUT /api/posts/{postId}`: Update post.
- `DELETE /api/posts/{postId}`: Delete post.
- ... and more

## Database

The project uses MySQL for data storage. You can find the database schema in the `schema.sql` file.

## Contributing

Contributions are welcome! If you have improvements or bug fixes, feel free to submit a pull request.

## License

This project is licensed under the [MIT License](LICENSE).

---

Feel free to customize and expand upon this template according to your project's specific details. A comprehensive `README.md` will help others understand and use your project effectively.
