# Math Expert Bot - Readme

This is a Math Expert Bot that utilizes the OpenAI API to provide responses to Math-related questions. The bot is integrated with the Telegram Bot API to receive questions from users and send back expert answers.

## Technologies Used

- Spring Boot: The bot is developed using the Spring Boot framework, which provides an easy-to-use setup for building Java applications.
- Spring Data JPA: This is used for data persistence and managing interactions with the PostgreSQL database.
- PostgreSQL: The bot stores user data and interaction history in a PostgreSQL database.
- Java: The core programming language used for developing the bot's logic and functionalities.

## Setup

1. **OpenAI API Setup**: First, you need to obtain an API key from OpenAI. Go to the OpenAI website and create an account. Obtain the API key and store it securely.

2. **Telegram Bot API Setup**: To interact with Telegram, you need to create a bot and obtain the Telegram Bot Token. Refer to the Telegram Bot documentation for creating a new bot.

3. **PostgreSQL Database Setup**: Install PostgreSQL and create a new database for the bot. Update the application.properties file with the database connection details.

4. **Clone the Repository**: Clone this repository to your local machine using the following command:

   ```bash
   git clone https://github.com/your-username/math-expert-bot.git
