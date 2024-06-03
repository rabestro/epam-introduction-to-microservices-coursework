# EPAM Introduction to Microservices Coursework

This repository contains coursework for EPAM's 'Introduction to Microservices' course. It includes two Java Spring Boot microservices: Resource Service for handling MP3 files and Song Service for managing song metadata.

## Resource Service

The Resource Service is responsible for handling MP3 files. It provides the following endpoints:

- `POST /resources`: Upload a new MP3 file.
- `GET /resources/{id}`: Retrieve the MP3 file with the given ID.
- `DELETE /resources`: Delete one or more MP3 files by their IDs.

When a new MP3 file is uploaded, the service extracts the metadata using Apache Tika and sends it to the Song Service.

## Song Service

The Song Service is responsible for handling song metadata. It provides the following endpoints:

- `POST /songs`: Create a new song metadata record.
- `GET /songs/{id}`: Retrieve the song metadata with the given ID.
- `DELETE /songs`: Delete one or more song metadata records by their IDs.

## Database

Both services use a Postgres database running in a Docker container for data storage.

## Microservices Communication

The services communicate with each other using RESTful APIs. When the Resource Service uploads a new MP3 file, it sends a POST request to the Song Service with the extracted metadata.

## Getting Started

To run the services, you will need to have Docker and Java installed on your machine. Follow these steps:

1. Clone this repository.
2. Start the Postgres database by running `docker-compose up` in the root directory.
3. Start each service by navigating to its directory and running `./mvnw spring-boot:run`.

## Testing

The repository includes comprehensive unit and integration tests for the services. To run the tests, navigate to the service directory and run `./mvnw test`.

## Contributing

Contributions are welcome! Please open an issue or submit a pull request if you would like to contribute.

## License

This project is licensed under the MIT License. See the [LICENSE](LICENSE) file for details.
