Feature: Student add new discussion and teacher makes the forum staff only

  Background: Forum is not staff only
    Given Admin navigate to premissions
    And Allow student
    Then exit


  Scenario: Student add new discussion
    Given User named "student" on course page Username "student1" and password "!Student123"
    When User named "student" Navigate to Forum
    And User start creation for new discussion
    And User finish creation for new discussion
    Then Discussion is in forum


  Scenario: Teacher makes forum staff only
    Given User named "teacher" on course page Username "teacher1" and password "!Teacher123"
    When User named "teacher" Navigate to Forum
    And User navigate to forum permissions
    And User delete student permission to start new discussion
    Then Forum staff only


  Scenario: Student opens discussion creation window and Teacher makes forum staff only and Student cant create discussion
    Given User named "teacher" on course page Username "teacher1" and password "!Teacher123"
    And User named "student" on course page Username "student1" and password "!Student123"
    When User named "teacher" Navigate to Forum
    And User named "student" Navigate to Forum
    And User navigate to forum permissions
    And User start creation for new discussion
    And User delete student permission to start new discussion
    And User finish creation for new discussion
    Then Forum staff only
    And Discussion not in forum

