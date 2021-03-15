# assignmentSevenSenders
Seven Senders Assignment Implementation
Looks like first provided link has a differend API now. XKCD now provide their JSON comics one per request e.g https://xkcd.com/614/info.0.json. 
However they provide xml representation of their comics feed https://xkcd.com/atom.xml. So I implemented JAXB xml intergration for XKCD and for PDL as well.
In addition I implemented one JSON call from their API if the reason of the task was to see how am I able to work with JSON incoming data ^^. 
As well the APIs do not provide 10 pictures per call, it's 4 from XKCD and 8 from PDL website. So I just sorted them by a publication date. 
Hope ypu find my solution fine. If there is a need to redo something please contact me :) 

