# iBank Application

Welcome to iBank Application! This is a simple banking application developed using Java and Spring Boot, for managing your finances conveniently. With this application, you can perform various banking operations such as creating accounts, depositing money, withdrawing funds, transferring money between accounts, and checking your account balance. The application has been made with 3 microservice components: Accounts, Loans and Cards. 

## Note: The project is still a WIP.

## Features
- **User Authentication:** Secure login system to ensure only authorized users can access their accounts.
- **Account Management:** Create, view, and manage your bank accounts easily.
- **Deposit and Withdrawal:** Deposit money into your accounts and withdraw funds whenever needed.
- **Transfer Funds:** Transfer money between your own accounts or to other users within the system.
- **Transaction History:** Keep track of your transactions with a detailed transaction history.
- **Security:** Your data is securely stored and encrypted to ensure confidentiality.

## Technologies Used
- **Programming Language:** Java
- **Database:** MySQL
- **Frameworks:** Spring Boot, Hibernate
- **Build Tool:** Maven

## Setup Instructions
1. **Clone the Repository:** Clone this repository to your local machine using `git clone`.
2. **Database Configuration:** Set up a MySQL database and configure the database connection details in the `application.properties / application.yml` file.
3. **Build the Project:** Navigate to the project directory and build the project using Maven: `mvn clean install`.
4. **Run the Application:** Run the application using `java -jar ibank-bank.jar`.
5. **Access the Application:** Open your web browser and go to `http://localhost:8080` to access the EasyBytes Bank Application.

## Usage
1. **Login:** Enter your username and password to log in to your account.
2. **Dashboard:** Once logged in, you will be redirected to the dashboard where you can view your account details and perform banking operations.
3. **Create Account:** If you are a new user, you can create a new bank account by providing the necessary details.
4. **Deposit and Withdraw:** Deposit money into your accounts or withdraw funds as needed.
5. **Transfer Money:** Transfer money between your accounts or to other users by providing the recipient's account details.
6. **Transaction History:** View your transaction history to keep track of all your financial activities.

## License
This project is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
