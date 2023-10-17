# Back-end with Java - What I Learned

## August 30-31
Here, I focused on learning the basics about how Spring and Spring boot work, along with the most popular tools like Maven, Hibernate, Spring Security, etc.

- Learned the basics about spring and spring boot
- Learned how to configure dependencies
    - Importing dependencies via pom.xml
- Learned about Lombok
    - Generation of getters, setters, constructors, etc.
- Learned about how to configure CI with GitHub Actions
    - For every PR opened, build and run tests on every commit.
    - Only allow merges when tests are passing.


## September 2
I dug into how JSON serialization works and understood how JPA and Hibernate generate database interactions under the hood.

- Learned about JSON serialization and how to configure snake case formatting (with Jackson)
- Learned about configuring routes
- Learned about implementing JPA repositories with Hibernate
    - It works like magic, which is incredible, but confusing to understand what is happening under the hood.
- I got trapped in my first recursion problem with Hibernate
    - Hibernate was trying to map both parent and child elements every time.
## September 11
I decided to use Clean Architecture out of curiosity to see the trade-offs of following this approach in a new spring boot project.

- Started refactoring the application to respect Clean Architecture and Domain Driven Design concepts.
    - The good:
        - My domain turned into pure Java and its standard libraries —zero risks by depending on third-party libraries on my domain.
        - The core of the application was decoupled from the implementation details.
        - Business rules were preserved in use cases.
    - The bad:
        - Many mappers needed to convert between DTOs, Database Models, and Domain Entities, thus giving me the trade-off of writing boilerplate code and affecting performance with the benefit of decoupling layers.
        - Overengineering for such a simple software, but that was expected since it's for learning purposes. If that was an actual project, deciding to decouple those layers could be something for the future.
## September 17
- Learned about checking secure and strong passwords by using the Passay library.
- Learned about creating custom constraint validations.
    - Validates DTO fields right before starting business rules.
- Learned more about dependency injection on spring and how to configure Configuration classes with beans.
## September 25
This week, I focused on security.

- Created user role into user entity to start creating role-specific routes.
- Added password encryption with Bcrypt.
- Implemented Spring Security.
- Added JWT as an authentication solution.
- Protected all routes besides auth ones with JWT.
## October 6
Here, I focused on implementing sending e-mail features.

- Used SendGrid library to send e-mails.
- Created interface contract to define how e-mail sending libraries should behave.
- Implemented password recovery flow via e-mail with temporary tokens.
- Implemented mandatory e-mail activation flow with temporary tokens right after registering.
## October 7-8
Here, I focused on implementing chatting features after a match happens.

- Designed chatting models
- Added creating chatrooms after a match happens
- Added sending message feature
## October 9
This day, I focused on more security.

- Refactored routes to always get user information by JWT tokens.
- Refactored route paths to be more concise.
- Add business rules to avoid unauthorized operations.
- Added password change flow when logged in.