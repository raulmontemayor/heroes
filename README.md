# Heroes

Heroes is a rest api for demoing purposes

## Installation

To simply run the api use:

```bash
./gradlew bootRun -p Drivers
```

To generate the excutable jar use:

```bash
./gradlew assemble
```

Then in Drivers folder we can create a docker image:

```bash
docker build --build-arg JAR_FILE=build/libs/\*.jar -t mindata/heroes .
```

And run it with this:

```bash
docker run --name hero -p 8080:8080 mindata/heroes
```

## Usage
To make things easier we have added a Postman collection (Hero.postman_collection.json) with examples of what the API can do.

