/*@provengo summon selenium*/

/**
 * The first event is for both Student and Teacher, since the login and navigation to the forum page is identical
 * for both of them. Login details are passed by the event(e).
 */
defineEvent(SeleniumSession, "GoToForum", function(session, e) {
  //start at moodle landing page
  session.click("//*[@id=\"usernavigation\"]/div/div/span/a");
  // enter user name and password and then log in
  session.writeText("//*[@id='username']", e.username);
  session.writeText("//*[@name='password' and @type='password']", e.password);
  session.click("//*[@id=\"loginbtn\"]");
  // navigate to course
  session.click("//*[contains(text(),'My courses') and @role='menuitem']");
  session.click("//*[@class='multiline' and contains(text(),'course1')]");
  session.click("//*[@id=\"module-4\"]/div/div[1]/div/div[1]/div/div[2]/div[2]/a");
})

/**
 * changePermissions will be called by teacher to change the permission to start new discussions
 * in the forum. This should always execute properly
 * */

defineEvent(SeleniumSession,"ChangePermissions",function (session,e){
  session.click("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[6]/a");
  session.click("/html/body/div[3]/div[4]/div/div[2]/nav/ul/li[6]/ul/li[2]");
  session.writeText("//*[@id=\"permissionscapabilitysearch\"]","start");
  session.click("//*[@class=\"custom-select singleselect\" and @name=\"roleid\"]/option[3]");
  session.click("//tr[td[label[input[@type=\"radio\" and @name=\"mod/forum:startdiscussion\" and @value=\"-1\"]]]]/td[3]/label/input");
  session.click("//*[@name=\"savechanges\" and @value=\"Save changes\"]");
})

/**
 * The precondition here is that the Teacher already changed the permissions before the Student entered the forum.
 * This method will make sure that the 'Add discussion topic' button is not reachable by the Student.
 * The method throws an error on the condition that the button was pressed. Equivalent to an "AssertNotClickable".
 * */
defineEvent(SeleniumSession,"AssertCantAddNewDiscussion",function (session,e){
  session.click("//*[contains(text(),'Add discussion topic')]");
  // write subject
  session.writeText("//*[@id=\"id_subject\"]", e.title);
  // write body
  session.writeText("//*[@id=\"id_messageeditable\"]", e.body);
  // press the "post to forum" button
  session.click("//*[@id=\"id_submitbutton\"]");
  session.assertText("//*[@id=\"region-main\"]/div/div[1]/p[1]","Sorry, you are not allowed to post to this forum")
})

/**
 * trigger method for Teacher to synchronize the drivers for this test.
 * */
defineEvent(SeleniumSession,"JustNowOnForum",function (session,e){

})

/**
 * The precondition here is that the Teacher did NOT already change the permissions before the Student entered the forum.
 * This method will make sure that the 'Add discussion topic' button redirects the Student to an error page from Moodle.
 * The method throws an error on the condition that the Student does not reach the error page.
 * */
defineEvent(SeleniumSession,"AssertTryAddDiscussion",function (session,e){
    try{
        session.waitForVisibility("//*[@class=\"container-fluid tertiary-navigation\"]/div/div[2]",500)
    }
    catch(e){
        return
    }
    throw new Error('Student managed add new discussion after having permissions removed.')
})
