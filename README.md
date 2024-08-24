# Chat Application

A Java-based online chat application that allows users to sign up, sign in, chat, send and accept friend requests, and create chat groups. The application also includes a mechanism to detect and block fake accounts.

## Features

- *User Registration & Login:* 
  - Sign up and log in with username and password.
  - Secure password storage with hashing.
- *Chat System:* 
  - One-to-one chat with friends.
  - Group chat feature for chatting with multiple users.
- *Friend Request System:* 
  - Send and accept friend requests.
  - List all pending friend requests.
- *Fake Account Detection:* 
  - Detects and blocks suspicious/fake accounts based on predefined criteria.
- *Simple User Interface:* 
  - Developed using Java Swing and AWT for a responsive GUI.

## Technologies Used

- *Java:* The core programming language.
- *Java Swing:* Used for building the graphical user interface (GUI).
- *Java AWT:* For event handling and other GUI components.
- *JDBC:* Java Database Connectivity for interacting with the MySQL database.
- *MySQL:* The relational database for storing user data, messages, and friend requests.

## Database Schema

The application uses a MySQL database with the following schema:

- *Users Table:*
  - id (INT, Primary Key)
  - username (VARCHAR)
  - password (VARCHAR)
  - email (VARCHAR)

- *Messages Table:*
  - id (INT, Primary Key)
  - sender_id (INT, Foreign Key referencing Users.id)
  - receiver_id (INT, Foreign Key referencing Users.id)
  - message (TEXT)
  - timestamp (DATETIME)

- *Friend Requests Table:*
  - id (INT, Primary Key)
  - sender_id (INT, Foreign Key referencing Users.id)
  - receiver_id (INT, Foreign Key referencing Users.id)
  - status (VARCHAR: 'PENDING', 'ACCEPTED', 'REJECTED')

## Usage
- Register: Create a new account by providing a unique username and password.
- Login: Log in to your account using your credentials.
- Chat: Start a chat with your friends or create/join a group chat.
- Friend Requests: Send, accept, or reject friend requests.
- Group Chat: Create or join a group chat to communicate with multiple users.
