# Student Score Analyzer

An Spring Boot REST api that calculates student results in various subjects and retrieve the mean, median and mode score and send to an Angular app at https://student-score-analyzer.vercel.app/ 

### API can be previewed at

https://desolate-harbor-08314.herokuapp.com/api/v1/

### Frontend repo:

https://github.com/volunux/student-score-analyzer

#### List of API Route and Definition
**NOTE:** All Request should have an Accepts and Content-Type header with the value application/json and some
Also API might take time to respond due to hibernation and idleness. API is hosted with Heroku.

Api Url is **(Student Score Analyzer)[https://desolate-harbor-08314.herokuapp.com/api/v1/]**

**_{id}_ is a placeholder or variable that should be replace with an Long value during request**

#### Student Sample JSON	
NB: {id} will be replace by student Id for Student Entity

| Route                   | Http Method    | Description            | Sample Body                           | Options      |
| ----------------------- | -------------- | ---------------------- | ------------------------------------- | -----------  |
| /      				  | GET            | API Entry              |                                       |  			   |
| api/v1/student/entries     | GET         | Retrieve all records and can be narrow by emailAddress  search |              | ?search=rick@gmail.com |
| api/v1/student/detail/{id} | GET         | Retrieve single record |                                       |              |
| api/v1/student/delete/{id} | DELETE      | Remove record          |                                       |              |
| api/v1/student/create      | POST        | Add new record         | {"firstName": "Ayeniyi", <br /> "lastName": "Adebiyi", <br /> "emailAddress": "odunlade@gmail.com", <br /> "homeAddress" : "Ado Ekiti, Ekiti State" } <br/> |               |
| api/v1/student/update/{id} | PUT         | Update a record        | {"firstName": "Ayomide", <br /> "lastName": "Oyeloke", <br /> "emailAddress": "ayoloke@gmail.com", <br /> "homeAddress" : "Owo, Ondo State" } <br/> | |
| api/v1/student/remove/many | PUT         | Remove some record         | {"ids": [1, 2, 3]} |               |


#### Subject Sample JSON
NB: {id} will be replace by subject Id for Subject Entity	

| Route                   | Http Method    | Description            | Sample Body                           | Options      |
| ----------------------- | -------------- | ---------------------- | ------------------------------------- | -----------  |
| /      				  | GET            | API Entry              |                                       |  			   |
| api/v1/subject/entries     | GET         | Retrieve all records and can be narrow by subjectCode search |                | ?search=BIO111 |
| api/v1/subject/detail/{id} | GET         | Retrieve single record |                                       |              |
| api/v1/subject/delete/{id} | DELETE      | Remove record          |                                       |              |
| api/v1/subject/create      | POST        | Add new record         | {"title": "Machine Learning", <br /> "code": "CPT123", <br /> "year": 2011 } <br /> |               |
| api/v1/subject/update/{id} | PUT         | Update a record        | {"title": "Machine Learning and Aritificial Intelligence", <br /> "code": "CPT123", <br /> "year": 2011 } <br /> | |
| api/v1/subject/remove/many | PUT         | Remove some record     | {"ids": [1, 2, 3]} |               |


#### Score Sample JSON
NB: {id} will be replace by student Id from Student Entity

| Route                   | Http Method    | Description            | Sample Body                           | Options      |
| ----------------------- | -------------- | ---------------------- | ------------------------------------- | -----------  |
| /      				  | GET            | API Entry              |                                       |  			   |
| api/v1/score/student/entries     | GET   | Retrieve all records and can be narrow by emailAddress search  |                | ?search=rick@gmail.com |
| api/v1/score/student/detail/{id} | GET | Retrieve student score records |                                 |              |
| api/v1/score/student/analysis/{id} | GET | Retrieve student score records with calculated, mean, median and mode |                               |              |
| api/v1/score/update/{id} | PUT           | Add or Update a record | {"studentId": 1, <br /> "subjectScores": [{"scoreId" : 1, <br /> "subjectId": 2, <br /> "overallMark": 34}, <br />{"scoreId" : 2, <br /> "subjectId": 1, <br /> "overallMark": 30}, <br />{"scoreId" : 3, <br /> "subjectId": 3, <br /> "overallMark": 47}, <br /> {"scoreId" : 4, <br /> "subjectId": 5, <br /> "overallMark": 87}] } <br /> | |
| api/v1/score/delete/entries/all| DELETE  | Remove all records     |                                       |               |