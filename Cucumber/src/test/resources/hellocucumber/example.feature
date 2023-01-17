Feature: Student add new discussion and teacher makes the forum staff only

#  Scenario: Testing how a case where a user adds a product to the cart
#    Given an example scenario
#    When all step definitions are implemented
#    Then the scenario passes



  Scenario: Student add new discussion
    Given User on course page Username "student1" and password "!Student123"
    When User Navigate to Forum
    And User start new discussion
    Then Discussion is in forum


  Scenario: Teacher makes forum staff only
    Given User on course page Username "teacher1" and password "!Teacher123"
    When User Navigate to Forum
    And User navigate to forum premissions
    And User delete student premission to start new discussion
    Then Forum staff only

