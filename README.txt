Step 1: Download the project into Ubuntu.
Step 2: Make sure that the path in the following files is set coorectly:
Calendar/src/com/harshitha/Calendar/operation/application.properties
Calendar/Test/com/harshitha/calendar/operation/CalendarServiceImplTest.java
Step 3: Now go to the dist folder and type the following command.
java -jar -cp "./:./lib/*" Calendar.jar.
Step 4:
Now we can see the menu:
1.Add Appointment - add~name~starttime~endtime~email~desc
add~sai~2014-06-09-12:00:00~2014-06-10-12:00:00~saii@saii.com~sai
2.Edit Appointment - edit~name~starttime~endtime~email~desc
edit~eeee~2014-06-09-12:00:00~2014-06-10-12:00:00~saii@saii.com~sai
3.Delete Appointment - delete~name~starttime
delete~eeee~2014-06-09-12:00:00
4.Search Appointment - search~field~searchterm
search~description~req
5.Import Appointment - import~filename
import~test
6.Export Appointment - export~filename (or) export~field~searchterm~filename
export~test
7.Exit
step 5:
For test cases ,use the folowing command:
java -cp .:./lib/*:Calendar.jar org.junit.runner.JUnitCore com.harshitha.calendar.test.CalendarServiceImplTest 
