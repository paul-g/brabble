set term x11
set out

plot "out" using 1:3 title 'Fast' with linespoint, \
     "out" using 1:5 title 'Slow' with linespoint

pause 1
reread
