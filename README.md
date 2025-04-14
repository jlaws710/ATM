# ATM

# Read Me First

* This application is a ATM app that supports account balance inquiry, deposit, and withdrawal operations via REST endpoints.
* This application was created using Java, Spring Boot, Docker, and Postgres.

# Getting Started

### Features
* User authentication is handled via database using Spring Security.
* Implements REST endpoints to support account balance inquiry, deposit, and withdrawal operations.
  
### Endpoint Examples: 
1. GET /atm/balance/{accountNumber}
2. POST /auth/register
   {
      "username": "your_username",
      "password": "your_password"
   }

3. POST /atm/deposit
   {
     "accountNumber": "acct_num",
     "balance": "your_balance"
   }

4. POST /atm/wtithdraw
   {
     "accountNumber": "acct_num",
     "balance": "your_balance"
   }

5. GET /transactions/{accountNumber}
