set term x11
set out

set y2tics
set tics out

plot "out" using 1:2 title 'Fast' with linespoint axes x1y1, \
     "out" using 1:3 title 'Slow' with linespoint axes x1y1

pause 1
reread

