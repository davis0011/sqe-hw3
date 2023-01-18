/*@provengo summon selenium*/

let PRECHECK = [true,false]
story('Teacher makes forum staff only', function(){
  with (new SeleniumSession().start('http://localhost/')){
    goToForum({username: 'teacher1', password:'!Teacher123'})
    changePermissions()
    block(Any("AddDiscussionTopic"))
  }
})

story('Student add new discussion', function(){
  with (new SeleniumSession().start('http://localhost/')){
    goToForum({username: 'student1', password:'!Student123'})
    waitFor(Any("GoToForum"))
    addDiscussionTopic({title: 'foo',body: 'bar'})
  }
})

// story('Student posted before teacher', function (){
//   with (new SeleniumSession().start('http://localhost/')){
//     interrupt(any('ClickPostToForum'),function (){
//       goToCourse({username: 'admin', password:'!Admin123'})
//       goToForum()
//       assertExistsForumPost()
//     })
//   }
// })
//
// story('Teacher changed permissions before Student posted', function () {
//   with (new SeleniumSession().start('http://localhost/')) {
//     on(any('GoToCourse'),function ())
//     interrupt(any('ChangePremissions'), function () {
//       goToCourse({username: 'admin', password: '!Admin123'})
//       goToForum()
//       assertNotExistsForumPost()
//     })
//   }
// })
//
// story('Admin debugger running', function () {
//   with (new SeleniumSession().start('http://localhost/')){
//     confirmDebug()
//     waitFor(any('GoToCourse'),function (){
//       goToCourse({username: 'admin', password:'!Admin123'})
//       goToForum()
//     })
//   }
// })