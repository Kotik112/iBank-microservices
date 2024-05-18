# iBank Back End Microservices

Welcome to iBank Application! This is a simple banking application developed using Java and Spring Boot, for managing your finances conveniently. With this application, you can perform various banking operations such as creating accounts, depositing money, withdrawing funds, transferring money between accounts, and checking your account balance. The application has been made with 3 microservice components: `Accounts`, `Loans` and `Cards`.

Note that this project is a work in progress and is being actively developed and is still a work in progress.


## Features
- **Account Management:** Create, view, and manage your bank accounts easily.
- **Deposit and Withdrawal:** Deposit money into your accounts and withdraw funds whenever needed.
- **Transfer Funds:** Transfer money between your own accounts or to other users within the system.


## Upcoming Features
- **User Authentication:** Secure login system to ensure only authorized users can access their accounts. (Not complete)
- **Transaction History:** Keep track of your transactions with a detailed transaction history. (Not complete)
- **Security:** Your data is securely stored and encrypted to ensure confidentiality. (Not complete)

## Technologies Used
- **Programming Language:** Java
- **Database:** MySQL
- **Frameworks:** Spring Boot, Hibernate
- **Build Tool:** Maven
- **Containerization:** Docker
- **API Documentation:** SpringDoc OpenAPI
- **Orchestration:** Docker Compose (for local dev) and Kubernetes (not implemented yet)
- **Configuration:** Spring Cloud Config (using [github repo](https://github.com/Kotik112/ibank-config)) and Spring Cloud Bus

## Setup Instructions
1. **Clone the Repository:** Clone this repository to your local machine using `git clone`.
2. **Database Configuration:** Set up a MySQL database and configure the database connection details in the `application.properties` or `application.yml` file.
3. **Build the Project:** Navigate to the project directory and build the project using Maven: `mvn clean install`.
4. **Run the Application:** Run the application using `docker-compose up -d` in the root directory of the project. This will start the microservices in a Docker container locally. (You need Docker installed)
5. **Access the Endpoints:** Access to endpoints will be available under `http://localhost:8080` for Accounts service, `http://localhost:8090` for Loans service and finally `http://localhost:9000` for Cards service.


## License
This project is licensed under the [Apache License 2.0](http://www.apache.org/licenses/LICENSE-2.0).
