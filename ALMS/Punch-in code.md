1. get the information of attandance type by short name
2. ge tthe attandance records of current user id
3. get all the availabe shift
4. get all setting on the basis of name like pucn in start time and punch in endtime
5. will check shift is started or not
6. now we will check today is holiday or not (tiday should not be elecctive  and if todat is holiday then we willl check comment if comment is null then we will throw error else we marked today worked on holiday) (table used holiday and holiday group)
7. find the list of elecetive holiday which are in (pending,approved,taken ) state




Doubts
1. Attandancetype Repository 
	1. how to get unique a attandance type
		1. SELECT * FROM public.attendance_types
where short_name = 'WOO' AND status NOT IN ('Inactive','Deactivated')
ORDER BY id ASC 
2. how we are calculating elective holiday 
