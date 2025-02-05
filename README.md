# Helidon OpenAPI Client Demo

## About This Repository

This repository demonstrates how to use Helidon MP 4.1.6 alongside the OpenAPI Generator (`openapi-generator-maven-plugin`) to rapidly generate client SDKs for synchronous API integration. The purpose of this project is to showcase the power of OpenAPI-driven development and how it can accelerate integration efforts in microservices architectures.

## Motivation

In modern application development, integrating multiple APIs is a common challenge. Creating and maintaining API clients manually can be tedious and error-prone. The OpenAPI Generator simplifies this process by automatically generating client code based on OpenAPI specifications, ensuring consistency and reducing development effort.

This project provides two sample Helidon MP services that demonstrate:

- How to define APIs using OpenAPI specifications.
- How to use the OpenAPI Generator Maven plugin to create client SDKs.
- How to integrate and test synchronous API communication between services.

## How OpenAPI Client Generation Works

The OpenAPI Generator Maven plugin (`openapi-generator-maven-plugin`) is a powerful tool that automates the generation of client SDKs from OpenAPI specifications. By configuring the plugin in your `pom.xml`, you can generate Java clients tailored to the Helidon framework. Here's an example of how the plugin is configured:

```xml
<plugin>
    <groupId>org.openapitools</groupId>
    <artifactId>openapi-generator-maven-plugin</artifactId>
    <executions>
        <execution>
            <goals>
                <goal>generate</goal>
            </goals>
            <configuration>
                <inputSpec>${project.basedir}/src/main/resources/example.yaml</inputSpec>
                <generatorName>java-helidon-client</generatorName>
                <library>mp</library>
                <output>${project.build.directory}/generated-sources/client</output> 
                <addCompileSourceRoot>true</addCompileSourceRoot>
                <configOptions>
                    <groupId>io.helidon.examples</groupId>
                    <artifactId>helidon-openapigen-mp-client</artifactId>
                    <artifactVersion>1.0.0-SNAPSHOT</artifactVersion>
                    <apiPackage>io.helidon.examples.openapigen.mp.client.api</apiPackage>
                    <modelPackage>io.helidon.examples.openapigen.mp.client.model</modelPackage>
                    <invokerPackage>io.helidon.examples.openapigen.mp.client</invokerPackage>
                </configOptions>
                <additionalProperties>
                    <additionalProperty>returnResponse=true</additionalProperty>
                </additionalProperties>
            </configuration>
        </execution>
    </executions>
</plugin>
```

### Key Points of the Configuration

- **`inputSpec`**: Specifies the OpenAPI YAML file that defines the API.
  - **Disclaimer**: It is necessary to keep the file configured in `<inputSpec>${project.basedir}/src/main/resources/example.yaml</inputSpec>` updated with the latest metadata from `service-a` to ensure that the generated client reflects any future modifications accurately.
  - To retrieve the latest OpenAPI specification from `service-a`, you can use the following endpoint:

    ```bash
    curl -v http://localhost:8080/openapi
    ```

    The response will be similar to:

    ```yaml
    components: 
      schemas:
        Message: 
          properties:
            message: 
              type: string
            greeting: 
              type: string
          type: object
    info: 
      title: Generated API
      version: '1.0'
    openapi: 3.0.3
    paths:
      /greet: 
        get: 
          responses:
            '200': 
              content:
                application/json: 
                  schema: 
                    $ref: '#/components/schemas/Message'
              description: OK
      /greet/greeting: 
        put: 
          requestBody: 
            content:
              application/json: 
                schema: 
                  required:
                  - greeting
                  type: object
            required: true
          responses:
            '204': 
              description: Greeting updated
            '400': 
              description: JSON did not contain setting for 'greeting'
      /greet/{name}: 
        get: 
          parameters:
          - 
            in: path
            name: name
            required: true
            schema: 
              type: string
          responses:
            '200': 
              content:
                application/json: 
                  schema: 
                    $ref: '#/components/schemas/Message'
              description: OK
      /simple-greet: 
        get: 
          responses:
            '200': 
              content:
                application/json: 
                  schema: 
                    $ref: '#/components/schemas/Message'
              description: OK
      /simple-greet/{name}: 
        get: 
          parameters:
          - 
            in: path
            name: name
            required: true
            schema: 
              type: string
          responses:
            '200': 
              content:
                application/json: 
                  schema: 
                    $ref: '#/components/schemas/Message'
              description: OK
    ```

- **`generatorName`**: Indicates the target client type, in this case, a Java Helidon client.
- **`output`**: Defines where the generated client code will be placed.
  - **Disclaimer**: Strongly recommend `<output>target/generated-sources</output>` or a subdirectory below there.
- **`configOptions`**: Allows customization of package names, artifact details, and versioning.
- **`additionalProperties`**: Includes extra settings, such as enabling response wrapping.

When executed, this configuration generates type-safe client code, API models, and supporting files. The generated client is added to the project's source root, making it immediately usable in your application.

## Prerequisites

- **JDK 21** or later
- **Maven 3.8+**
- **Docker** (optional, for containerized deployment)
- Familiarity with Helidon MP and OpenAPI specifications

## Project Structure

The repository contains two main modules:

1. **`service-a`**: A Helidon MP service exposing a REST API.
2. **`clients/service-b`**: A Helidon MP service that consumes `service-a`'s API using a client generated with the OpenAPI Generator plugin.

## How to Use This Repository

### 1. Cloning the Repository

```bash
git clone https://github.com/aguirre-jes/helidon-openapi-client-demo.git
cd helidon-openapi-client-demo
```

### 2. Building the Project

```bash
cd service-a
mvn clean install
```

```bash
cd service-b
mvn clean install
```

### 3. Running the Services

To start `service-a`:

```bash
cd service-a
helidon dev
```

To start `service-b`:

```bash
cd clients/service-b
helidon dev
```

Both services will start on their respective ports (configured in the `microprofile-config.properties` files).

#### 3.5 Testing

You can test the integration by calling the endpoints of `service-b`, which internally use the generated client to communicate with `service-a`. Use the following `curl` command:

```bash
curl -v -H "x-api: app1" http://localhost:8081/greet
```

The response will be similar to:

```bash
{"message":"Hello World!"}%
```

This will send a request to `service-b`, which will use the generated client to call the corresponding endpoint on `service-a` and return the result.

### 4. Generating Client Code

The OpenAPI Generator Maven plugin is configured in the `pom.xml` of `service-b`. To regenerate the client code for `service-a`, run:

```bash
cd service-b
mvn openapi-generator:generate
```

This will generate a client SDK in the `target/generated-sources/openapi` directory, which can be used to interact with `service-a`'s API.

### 5. Testing Integration

You can test the integration by calling the endpoints of `service-b`, which internally use the generated client to communicate with `service-a`. Use a tool like `curl` or Postman, or invoke the endpoints directly from a browser.

## Key Features of the OpenAPI Generator

1. **Code Generation**: Automatically generates robust and type-safe client SDKs for APIs.
2. **Customizable Templates**: Allows you to customize the generated code to suit your project needs.
3. **Multiple Languages**: Supports a wide range of programming languages and frameworks.
4. **Consistency**: Ensures that clients are always in sync with the API specification.

## Abstraction Benefits

By using the OpenAPI Generator Maven plugin, developers can:

- Avoid writing boilerplate code for API clients.
- Ensure high-quality, maintainable, and consistent client implementations.
- Reduce the risk of integration errors due to misaligned API contracts.

In this demo, `service-b` acts as a consumer of `service-a`'s API. The generated client abstracts all HTTP communication, allowing developers to focus on business logic rather than low-level integration details.

## Contributing

Contributions are welcome! If you encounter any issues or have suggestions for improvement, feel free to open an issue or submit a pull request.

## License

This project is licensed under the [Apache-2.0](LICENSE).

## Acknowledgments

- [Helidon Project](https://helidon.io/) for providing a lightweight framework for microservices.
- [OpenAPI Generator](https://openapi-generator.tech/) for making API integration simple and efficient.
