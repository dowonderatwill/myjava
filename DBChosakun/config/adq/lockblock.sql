select 
	a.username blocked_user,
	b.username blocking_user,
	w.sid waiting_session,
	h.sid holding_session,
	w.type,
	decode (h.lmode, 1,'no lock', 2,'row share',3,'row exclusive', 4,'share',5,'share row exclusive',6,'exclusive','none') lmode,
	decode (w.request, 1,'no lock', 2,'row share',3,'row exclusive', 4,'share',5,'share row exclusive',6,'exclusive','none') request,
	a.row_wait_row# row_waited_on,
	w.id1,
	w.id2,
	w.ctime blocked_user_wait_secs,
	u1.name || '.' || t1.name locked_object
from
	sys.v_$lock w,
	sys.v_$lock h,
	sys.v_$session a,
	sys.v_$session b,
	sys.v_$locked_object o,
	sys.user$ u1,
	sys.obj$ t1
where
	h.lmode != 0 
and w.request != 0
and w.type = h.type
and w.id1 = h.id1 and w.id2 = h.id2
and b.sid = h.sid and a.sid = w.sid and h.sid = o.session_id
and o.object_id = t1.obj#
and u1.user# = t1.owner# 
order by
	4,3