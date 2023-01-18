# Assignment 3: Software Quality Engineering
This is a repository for assignment 3 of the Software Quality Engineering course at the [Ben-Gurion University](https://in.bgu.ac.il/), Israel.

## Assignment Description
In this assignment, we tested an open-source software called Moodle(https://address-of-the-project.com).

Moodle is a free and open-source learning management system written in PHP and distributed under the GNU General Public 
License. Moodle is used for blended learning, distance education, flipped classroom and other online learning projects 
in schools, universities, workplaces and other sectors.

## Installation
Fork this repository.
Host Moodle on Docker.
For usage of Cucumber or Provengo, see README.md in the respective files.

## What we tested
We tested the forum module that allows creating and commenting on discussion. We chose to test the following user stories: 

*User story:* A teacher connecting to the system and changing the permissions on a forum to disallow students from
   posting new discussions.

*Preconditions:* There is a course with a teacher and a forum

*Expected outcome:* The permissions for the forum are changed

*User story:* A student connecting to the system and trying to add a new discussion to a forum.

*Preconditions:* There is a course with a forum, the student has permissions to post to the forum.

*Expected outcome:* The student posts to the forum unless the teacher changes his permissions before he posts.
$$

## How we tested
We used two different testing methods:
1. [Cucumber](https://cucumber.io/), a BDD testing framework.
2. [Provengo](https://provengo.tech/), a story-based testing framework.

Each of the testing methods is elaborated in its own directory. 

## Results
Update all README.md files (except for d-e, see Section 1). Specifically, replace all $$*TODO*…$$ according to the instructions inside the $$.

