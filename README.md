# POC Message App
This project is a basic message-sending application designed to showcase a **distributed architecture**. 
It features a **React-based** frontend, an API service build with **Spring Boot** for communication, and a backend service, also built with **Spring Boot**, that interacts with a **Kafka-based** messaging system and a **MySQL** database. 
**Docker** is used to orchestrate the entire system, ensuring modularity and scalability.

<br>

> ## Install & Run
### 📦 Requirements
- Git
- Docker & docker-compose

### 📋 Commands
```bash
git clone https://github.com/GuillaumeLagouy/message-app.git

cd message-app

docker-compose up --build
```
Go to your [http://localhost:3000](http://localhost:3000) *et voilà*, you're ready to send message ! 🚀

<br>

> ## Containers descriptions
| Container | Port | Images used | Notes |
| ----------- | ----------- | ----------- |  ----------- |
| frontend | 3000 | node:22-alpine | |
| api service | 8080 | - maven:3.9.9-amazoncorretto-21-alpine <br> - openjdk:21-jdk-slim |
| backend service | 8081 | - maven:3.9.9-amazoncorretto-21-alpine <br> - openjdk:21-jdk-slim |
| mysql | 3306 | mysql:latest | |
| kafka | 9092 | confluentinc/cp-kafka:latest | |
| zookeeper | 2181 | confluentinc/cp-zookeeper:latest | |
| init kafka | | | only purpose is to create a kafka topic

<br>

> ## Stack

### 🍛 Frontend
- **Framework** : React
- **Form Management** : React Hook Form ([website](https://react-hook-form.com/))
- **Data Validation** : Zod ([website](https://zod.dev/))
- **Styling** : shadcn/ui ([website](https://ui.shadcn.com/)) - TailwindCSS
- **Build Tool** : Vite
- **API Calls** : native Fetch API

### 🤵‍♂️ Api service
- **Framework** : Spring Boot
- **Message Broker** : Apache Kafka

### 👨‍🍳 Backend service
- **Framework** : Spring Boot
- **Message Broker** : Apache Kafka
- **Database** : MySQL

<br>

> ## Notes
- **Kafka** is only used to **create a message**, not to edit it. An early implementation is available on the [fromHttpToKafka](https://github.com/GuillaumeLagouy/message-app/tree/fromHttpToKafka) branch of the Github repository, but the code doesn't work because of a deserialization or serialization problem.