<!-- ![PEN-NY Logo](images/PEN-NY.png) -->
<img src="images/PEN-NY.png" alt="Logo"
	title="PEN-NY logo" width="500" height="300" />
## A Penny for Your Thoughts...
Welcome to PEN-NY, a place for New Yorkers to share their thoughts and connect with other locals in the concrete jungle.

### Technologies Used

* **HTML/CSS** - PEN-NY is based on [this project](https://github.com/rosendopili/PEN-NY-CHAT), with an updated front end design. We used HTML & CSS to build  a new single page version of the site.

* **JavaScript** - We used JS to create an engaging and responsive user interface and add functionality to elements such as user signup, login and posting.

* **Java/PSQL** - The PEN-NY API is built with Java and a Postgres relational database which houses all user data and input.

* **Spring Boot** - This easy to use Spring framework allowed us to quickly develop and run the PEN-NY app on the embedded Tomcat server.


### Our Approach
We began this iteration of PEN-NY by building the backend with Spring Boot. The first couple of days were spent pair-programming and debugging any issues that arose as we went along. We started the backend code from scratch a few times before getting all of our needed dependencies, folders and classes working as they should so that the code would compile.

After building all of the necessary Java classes and mapping them to our Postgres database, we began populating the database and testing the functionality of our API with Postman. Testing revealed more bugs, particularly with JSON web tokens.

### Major Hurdles
As with any project, there were a few stumbling blocks. Our first round of pair programming went smoothly, but we had to restart due to some initial errors/dependency issues. We also experienced challenges with getting our user sign up and login functions to persistently work. Overall the project was a challenge, but it was also fun and fascinating to learn the ins and outs of an API by building our own.

#### User Stories
[Click here](https://docs.google.com/document/d/1AYhVIiWtoqI3dcMKcJkWv5oTYJyKhCW23ezuSDG5weM/edit?usp=sharing) to read the PEN-NY app user stories.

#### Other Documentation
Photos and diagrams of our work throughout the app building journey can be viewed [here](https://docs.google.com/presentation/d/1xYE1g4x3fGVbz-FgHlk0I7qb7T9dl-qQMwtvlxA6TNo/edit?usp=sharing). Examples of our PostMan tests can be viewed in the [PostManTesting](https://github.com/rosendopili/apiProject2/tree/master/PostManTesting)folder above.
