# spring-boot-chatbot

A full-stack web application implement chatbot using OpenAI REST API.

This project use SpringBoot and Hibernate to build the project. Here are the major components:
* Config: Use to adding project level config, e.g., SecurityConfig.
* Controller: All `@RestController` to implement corresponding feature, like register, authentication, etc.
* Model: Database tables, entities using Hibernate.
* Repository: Database operations using `@Repository`.
* Service: Implement the actual `@Service`

## Build 
* Update the `application.properties` based on your personal settings. 
* Using IDE (e.g. Intellij) to build and run the SpringBoot project. 

## Testing

###Register 

```BASH

POST http://localhost:8080/register
{
    "username": "test123",
    "password": "test123"
}
```
### Token Authenticate

```bash
POST http://localhost:8080/authenticate
{
    "username": "test123",
    "password": "test123"
}
```

### Chat

Provide your Bearer Token 

```bash
POST http://localhost:8080/chat
{
    "text": "who are you?",
    "model": "gpt-3.5-turbo-instruct"
}
```