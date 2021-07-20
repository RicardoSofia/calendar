# calendar
<p>Calendar Interview API Documentation</p>
<p>To Run the aplication Run the file CalendarApplication.java.</p>
<p>This API schedule 1 hour slot interviews :</p>
<br/><p>Rules : </p>
<p> This api provides to the interviewers and candidates to schedule an interview </p> 
<p> An Interviewer can schedule on their calendar multiple timeslots per day.</p>
<p> An interview slot is a 1-hour period of time that spreads from the beginning of any 
hour until the beginning of the next hour. For example, a time span between 9am and 
 10am is a valid interview slot, whereas between 9:30am and 10:30am it is not.</p> 
<p> Schedule interview steps: </p>
<p> We have two interviewers, one interviewer schedule on Monday from 9AM to 11AM 
this will create 2 timeslots for interviews, 9am-10am and 10am-11am. "
And user Ingrid will schedule on Monday  from 8:30AM to 12:30 this will create 3 timeslots
for interviews, 9am-10am and 10am-11am and 11am-12am. </p>
<p> The candidate will schedule the interview on the slot 9am-10am or 10am-11am. </p>
<p>Other Info : </p>
<p>1 - Local Date and Time (Starting and Ending) format is : LocalDateTime </p>
<p>2 - Available Candidate Users Logins : candidate</p>
<p>3 - Available Interview Users Logins : ines, ingrid</p>
<p>4 - If you need to register a new user please contact our support team.</p>
<p>5 - You will find more information in each Dto/Endpoint documentation.</p>
