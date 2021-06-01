# COMP304 PROJECT SPRING 2021
## GROUP 13
### Doğa Demirtürk
### İrem Şahin
### Yiğithan Şanlık
### Umutcan Türkmen

The main purpose of this code is find the most popular person with using of shortest path.
First, we extract all of the connections of our first node
Second, find all of the connections that the persons on our first nodes connections list have
Third, filter the overall information such that only both sides of the all connections consists of the persons from our first nodes personal connections.
Fourth, we find the shortest paths between each pair of our first nodes friends and decides that the most popular person in its connection list is the person who has the most occurrence in these shortest paths.



Approach 1:

Time Complexity: O(V^4)

Result: 
Test1: Most popular person is 0 with 10 occurence
Execution time in milliseconds: 44

Test2: Most popular person is 90 with 3811 occurence
Execution time in milliseconds: 354

Test3: NA

Approach 2:

Time Complexity: O(V^3)

Result:
Test1: Most popular person is 0 with 10 occurence
Execution time in milliseconds: 31

Test2: Most popular person is 90 with 3811 occurence
Execution time in milliseconds: 86

Test3: NA
