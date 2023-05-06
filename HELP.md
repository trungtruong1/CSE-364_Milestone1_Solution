### Reference Documentation

* [Springboot Project Getting Started](https://spring.io/guides/gs/spring-boot/)

# In this guide we recommend using Spring Initialzr(https://start.spring.io/) to get a fully structured Spring Boot Initial setup.

# To complete setting up the mongoDB and implement RESTful APIs, complete task 2, etc..., we added some dependencies/plugins in pom.xml. Please refer to pom.xml for documentation for each dependencies and plugins. 

# Since there can be some dependencies conflicts depending on which distribution version you get, you can use our the dependencies with the same versions as ours. 

# After setting up the pom.xml and having ONLY App.java in src/main/java/com/example, you should be able to start the server with `mvn spring-boot:run`

* [MongoDB Set Up](https://www.mongodb.com/docs/manual/tutorial/install-mongodb-on-ubuntu/)

# Now we can start setting up mongoDB for our LOCAL machine and connect our Springboot application with it.

# Since we are setting up our application on Ubuntu, please refer to the link above to install MongoDB Community Edition on Ubuntu (Step 1->4)

# After finishing installing MongoDB on your Ubuntu, run `sudo systemctl start mongod` to start MongoDB, and `sudo systemctl status mongod` for checking if MongoDB started. 

# For WSL2 users, you might not have `systemctl`, please refer to this link to solve the issue: https://askubuntu.com/questions/1379425/system-has-not-been-booted-with-systemd-as-init-system-pid-1-cant-operate (make sure you have the latest version of WSL2)

# To connect to MongoDB you need to create an user, so that our application can connect to this user's database. Please refer to this link for User Administrator creation: https://www.mongodb.com/docs/v2.6/tutorial/add-user-administrator/ (access mongo shell with `mongosh` to create an user)

# After creating an user, please configure your `application.properties` file so that your application can connect to this user's database. Running `mvn spring-boot:run` should show that application connected to MongoDB on port 27017(stdout)

* [Import Data into MongoDB](https://hevodata.com/learn/mongoimport/) (https://www.mongodb.com/docs/database-tools/mongoimport/)

# To import the data into MongoDB for part 2, we recommend converting .dat files into .csv files. In our case we converted the .csv files sub-manually(using VSC's parrtern matching) but you can refer to some online guides for it (https://unix.stackexchange.com/questions/711205/how-to-convert-a-dat-files-with-comma-separated-values-into-a-csv-file)

# To upload the corresponding databases, we used the `mongoimport` method with the correct format to load our csv files, you can refer to our commands in `run.sh`. Running the `mongoimport` command on the CLI should show that `n document(s) imported succesfully. 0 document(s) failed to import.` assuming that your csv file is well structured.

* [Setting up DockerFile and MongoDB in Docker Container](https://www.youtube.com/watch?v=eGz9DS-aIeY&ab_channel=NetworkChuck)

# Be careful about setting up your Dockerfile, as well as dependencies in it. 
# About setting up MongoDB in Docker Container, the steps is fairly similar with the process in our local Linux machine with a little twist regarding starting up the MongoDB service. Please refer to our Dockerfile for this matter. 

# After getting the Dockerfile ready with its dependency, with MongoDB running, you should be ready to pull your source code. Remember to create an User Administratior in MongoDB. Here we do it with our shell script `run.sh` with a couple of commands. The import data process is similar to above. 

