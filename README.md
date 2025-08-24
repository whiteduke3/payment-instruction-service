# payment-instruction-service

To build the project: `./gradlew clean build`
To run the project: `./gradlew bootRun`

To access the H2 console: `http://localhost:8080/h2-console`

Build and run frontend:
Make sure you have an up-to-date version of Node.js installed and your current working directory is the one where you intend to create a project.
`cd ./frontend`
`npm install`

`npm run format`
`npm run dev`

Start kafka server on localhost:9092

`brew install kafka`
`export PATH="/opt/homebrew/bin/kafka-server-start:$PATH"`
`kafka-server-start /opt/homebrew/etc/kafka/server.properties`


Examples of valid IBANs to test with:
GB82WEST1234569876543200 (United Kingdom)
DE89370400440532013000 (Germany)