/*@provengo summon selenium*/


story('Teacher makes forum staff only', function(){
  with (new SeleniumSession().start('http://localhost/')){
    goToCourse({username: 'teacher1', password:'!Teacher123'})
    goToForum()
    navigateToPermissions()
    changePermissions()
  }
})

story('Student add new discussion', function(){
  with (new SeleniumSession().start('http://localhost/')){
    goToCourse({username: 'student1', password:'!Student123'})
    goToForum()
    clickAddDiscussionTopic({title: 'foo',body: 'bar'})
    clickPostToForum()
  }
})