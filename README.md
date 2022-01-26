## Pokémon Repository Testing Project

This is a project dedicated for learning integration testing. It's pretty simple, only really containing repository 
and DAO classes. The application itself does not serve much function, since the only purpose of the project is to 
learn automated testing. 

### Running the tests

As of now, there is no implementation of an automation to run the database in docker. So, to run the interated tests, 
you gotta run the `docker-compose.yml` file inside the test resources folder. 

To run it:
1. Have `docker` and `docker-compose` installed on your machine
2. Open a terminal on the target folder
3. Run `docker-compose up`
4. If set up correctly, a functioning MySql database will start running on `localhost:3306`
