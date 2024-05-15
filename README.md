# iBank Back End Microservices

Welcome to iBank Application! This is a simple banking application developed using Java and Spring Boot, for managing your finances conveniently. With this application, you can perform various banking operations such as creating accounts, depositing money, withdrawing funds, transferring money between accounts, and checking your account balance. The application has been made with 3 microservice components: `Accounts`, `Loans` and `Cards`. 

## Note: The project is still a WIP.

## Features
- **User Authentication:** Secure login system to ensure only authorized users can access their accounts. (Not complete)
- **Account Management:** Create, view, and manage your bank accounts easily.
- **Deposit and Withdrawal:** Deposit money into your accounts and withdraw funds whenever needed.
- **Transfer Funds:** Transfer money between your own accounts or to other users within the system.
- **Transaction History:** Keep track of your transactions with a detailed transaction history. (Not complete)
- **Security:** Your data is securely stored and encrypted to ensure confidentiality. (Not complete)

## Technologies Used
- **Programming Language:** Java
- **Database:** MySQL
- **Frameworks:** Spring Boot, Hibernate
- **Build Tool:** Maven
- **Features: Docker support, 

## Setup Instructions
1. **Clone the Repository:** Clone this repository to your local machine using `git clone`.
2. **Database Configuration:** Set up a MySQL database and configure the database connection details in the `application.properties` or `application.yml` file.
3. **Build the Project:** Navigate to the project directory and build the project using Maven: `mvn clean install`.
4. **Run the Application:** Run the application using `java -jar loans-0.0.1-SNAPSHOT.jar`.
5. **Access the Endpoints:** Access to endpoints will be available under `http://localhost:8080` for Accounts service, `http://localhost:8090` for Loans service and finally `http://localhost:9000` for Cards service.

## Usage
The simplest way of starting the ibank backend application is by running the following command:
```docker-compose up``` in the root directory of the project. This will start the application and the database(soon) in a docker container.

## License
This project is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
