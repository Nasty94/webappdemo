
# Web app demo

In this web app you can store, change and create employees and bosses. Also clients and products are editable.
Also there is calculation function implemented using GET and POST methods.

Installation:

Eclipse:

- make sure you have gradle installed in eclipse
- download code from this repo
- unpack
- import gradle project
- in gradle tasks - 'init'
- run on jetty - jettyRun
- go to: localhost:8080/webapp

Command line:

-  download code from this repo
-  unpack
-  go to project root diretory
-  open command line: WinKey + R and type "cmd"
-  type "gradle init"
-  if build successfull, type "gradle jettyRun"
-  go to: localhost:8080/webapp

h2 database:

NB! Once new db is installed in you home direcory, there is no tables and, therefore no data. Once you click "View details" on the home page, relevant table is automatically created. However, cause tables have no data, user will not see any details unless information is manually added.

NB! Used database files is copied to /src/main. So you can place this file to you home direcoty and use it. This files already has
necessary tables and data.

name: 'webapp'
location: "~/webapp"
username: "sa"
password: ""

Once project is laucnhed, go to http://localhost:8080/webapp/console to access database.


NB! Connection to database is shown in the following files:

- com/demo/h2/DBUtils.java
- demo/dao/EmployeeDAOImpl.java
- demo/dao/BossDAOImpl.java

Employees and bosses:
- bosses are also employees so same person can appear in both tables
- everyone has boss, there is no employee without boss. However, some employee can his own boss.
- bosses are shown in Boss table
- to view employee click on "View details" under employee table or go to http://localhost:8080/webapp/employee
- to view employee click on "View details" under boss table or go to http://localhost:8080/webapp/boss
- during chaning employee /boss you redirect to link like http://localhost:8080/webapp/editBoss?securitycode=835678
- during creating employee / boss you redirected back to to http://localhost:8080/webapp/boss or to http://localhost:8080/webapp/employee

Calculator:
- standart calculator uses Get method to calculate value. Result is shown under "result" span.
- Post methtod reads file in json format and gives the calculation and result.
- see CalculatorServlet class for GET method calculator implementation
- see CalculatorRESTServiceClient for POST method calculator implementation
- txt file for POST method is in the com.demo.calculator package
- commands:
   * add - addition
   * sub - subtraction
   * mul - multiplication
   * div - division
   
- path for file: 
    String userhome = System.getProperty("user.home");
    InputStream calculateInputStream = new FileInputStream(userhome + "/Documents/GitHub/webapp/src/main/java/com/demo/calculator/calc.txt");
    
    DO NOT FORGET TO CHANGE IT

Other comments:

- when you add order, please makse sure that client security code is in table "Client". Otherwise NullPointerException raised.
- security codes and ids are type of Integer.
- basic functionality implemented in com.demo.dao.*
- screenshots from interface are in /src/main/pic/
- as there was no information about error handling in task description, please note that
     * it is NOT possible to add product if barcode is already in database
     * it is NOT possible to add client if securitycode is already in database
     * it is NOT possible to add order if client does not exist in database
     * no erro are shown, user just redirested no relevant tag and do data is added 
     * to view errors please see log file

