select 
	b.sid,
	nvl(b.username,c.name) username,
	b.machine,
	a.total_waits,
	round((a.time_waited/100),2)	time_wait_sec,
  a.total_timeouts,
	round((average_wait/100),2) average_wait_sec,
	round((a.max_wait/100),2) max_wait_sec
	
from
	sys.v_$session_event a,
	sys.v_$session b,
	sys.v_$bgprocess c
where
	a.sid=b.sid
and c.paddr (+) = b.paddr
order by
	3 desc,
	1 asc;