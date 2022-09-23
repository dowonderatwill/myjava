select 
 'top physical i/o process' category,
 sid,
 username,
 total_user_io amt_used,
 round(100*total_user_io/total_io,2) pct_used
from
(select
	b.sid sid,
	nvl(b.username,p.name) username,
	sum(value) total_user_io
 from 
	sys.v_$statname c,
	sys.v_$sesstat a,
	sys.v_$session b,
	sys.v_$bgprocess p
 where 
	a.statistics#=c.statistics# and
	p.paddr (+) = b.paddr and
	b.sid = a.sid and
	c.name in  ( 'physical reads','physical reads direct','physical reads (lob)','physical writes','physical writes direct','physical writes direct (lob)')
 group by 
	b.sid, nvl(b.username,p.name)
 order by
	3 desc),
( select 
	sum(value) total_io
 from 
	sys.v_$statname c,
	sys.v_$sesstat a
 where
	a.statistic#=c.statistic# and 
	c.name in  ( 'physical reads','physical reads direct','physical reads (lob)','physical writes','physical writes direct','physical writes direct (lob)'))
where
	rownum < 2

union all
select 
 'top logical i/o process' category,
 sid,
 username,
 total_user_io amt_used,
 round(100*total_user_io/total_io,2) pct_used
from
(select
	b.sid sid,
	nvl(b.username,p.name) username,
	sum(value) total_user_io
 from 
	sys.v_$statname c,
	sys.v_$sesstat a,
	sys.v_$session b,
	sys.v_$bgprocess p
 where 
	a.statistics#=c.statistics# and
	p.paddr (+) = b.paddr and
	b.sid = a.sid and
	c.name in  ( 'consistent gets','db block gets')
 group by 
	b.sid, nvl(b.username,p.name)
 order by
	3 desc),
( select 
	sum(value) total_io
 from 
	sys.v_$statname c,
	sys.v_$sesstat a
 where
	a.statistic#=c.statistic# and 
	c.name in  ( 'consistent gets','db block gets'))
where
	rownum < 2	
	
union all
select 
 'top memory process' category,
 sid,
 username,
 total_user_mem,
 round(100*total_user_mem/total_mem,2) 
from
(select
	b.sid sid,
	nvl(b.username,p.name) username,
	sum(value) total_user_mem
 from 
	sys.v_$statname c,
	sys.v_$sesstat a,
	sys.v_$session b,
	sys.v_$bgprocess p
 where 
	a.statistics#=c.statistics# and
	p.paddr (+) = b.paddr and
	b.sid = a.sid and
	c.name in  ( 'session pga memory','session uga memory')
 group by 
	b.sid, nvl(b.username,p.name)
 order by
	3 desc),
( select 
	sum(value) total_mem
 from 
	sys.v_$statname c,
	sys.v_$sesstat a
 where
	a.statistic#=c.statistic# and 
	c.name in  ( 'session pga memory','session uga memory'))
where
	rownum < 2	

union all
select 
 'top cpu process' category,
 sid,
 username,
 total_user_cpu,
 round(100*total_user_cpu/greatest(total_cpu,1),2) 
from
(select
	b.sid sid,
	nvl(b.username,p.name) username,
	sum(value) total_user_cpu
 from 
	sys.v_$statname c,
	sys.v_$sesstat a,
	sys.v_$session b,
	sys.v_$bgprocess p
 where 
	a.statistics#=c.statistics# and
	p.paddr (+) = b.paddr and
	b.sid = a.sid and
	c.name in  ( 'CPU used by this session')
 group by 
	b.sid, nvl(b.username,p.name)
 order by
	3 desc),
( select 
	sum(value) total_cpu
 from 
	sys.v_$statname c,
	sys.v_$sesstat a
 where
	a.statistic#=c.statistic# and 
	c.name in  ( 'CPU used by this session'))
where
	rownum < 2		