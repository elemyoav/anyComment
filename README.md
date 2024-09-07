Here’s the `.md` file in a format you can copy easily:

# AnyComment Application

## Prerequisites

Before running the application, make sure you have the following installed:

- [Docker](https://docs.docker.com/get-docker/)
- [Docker Compose](https://docs.docker.com/compose/install/)

## How to Run

1. **Build the Backend:**

   Run the following command to build the Docker image for the backend:

   ```
   docker build . -t {userName}/anyComment:latest
   ```

2. **Run the Application:**

   Use Docker Compose to start the application:

   ```
   docker-compose up
   ```

3. **Access the Application:**

   The service should now be available on port `8080`.

## Postman Collection

To test the API, import the Postman collection file provided in the repository.