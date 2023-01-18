/*@provengo summon selenium*/


/**
 * This story is the Teacher connecting to the system and changing the permissions on a forum.
 * This story will always finishes and once it blocks the Student story it will check that the Student can't post
 * to the forum.
 **/
story('Teacher makes forum staff only', function(){
  with (new SeleniumSession().start('http://localhost/')){
    goToForum({username: 'teacher1', password:'!Teacher123'})
    changePermissions()
    on(Any('EndOfAction').and(Any({eventName: "GoToForum"})),function (){
      justNowOnForum()
    })
  }

})
/**
 * This story is the Student connecting to the system and trying to add a new discussion to a forum.
 * This story will always wait for the Teacher to change permissions so that we can check that the Student can't post
 * to the forum. The two options are that the student was in the forum when the permission was changed and that he wasn't
 * in both cases the Student can't post a new thread but the behaviour is different, and therefore we have 2 'on'
 * statements to catch the scenarios.
 **/
story('Student add new discussion', function(){
  let s = new SeleniumSession().start('http://localhost/')
    s.goToForum({username: 'student1', password:'!Student123'})
    on(Any('EndOfAction').and(Any({eventName: "ChangePermissions"})),function (){
      s.assertCantAddNewDiscussion()
    })
    on(Any('EndOfAction').and(Any({eventName: "JustNowOnForum"})),function (){
      s.assertTryAddDiscussion()
    })
  })
