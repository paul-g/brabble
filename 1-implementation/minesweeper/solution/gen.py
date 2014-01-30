import sys, random

MAX_N = 10

print '{} {}'.format(MAX_N, MAX_N)

for i in range(MAX_N):
    for j in range(MAX_N):
        sys.stdout.write('.' if random.randint(0, 1) == 1 else '*')
    print ''

print '0 0'
