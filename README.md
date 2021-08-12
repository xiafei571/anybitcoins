# anybitcoins
Spring boot project

mvn clean

mvn install

cd -> {anybitcoins project}\target

java -jar anybitcoins-0.0.1-SNAPSHOT.jar

visit http://localhost:8080/

use some tools like Postman:
[url]: http://localhost:8080/history
[Content-Type]:application/json
[data]: {"startDatetime": "2021-08-13T13:46:05+07:00","endDatetime": "2021-08-13T17:46:05+07:00"}

get the result:
{
    "status": 0,
    "res": [
        {
            "datetime": "2021-08-13T08:00:00.000+00:00",
            "amount": 13.1
        },
        {
            "datetime": "2021-08-13T09:00:00.000+00:00",
            "amount": 15.2
        }
    ]
}
