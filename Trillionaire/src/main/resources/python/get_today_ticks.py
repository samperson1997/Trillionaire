#coding=utf-8

import tushare as ts
import sys

reload(sys)
sys.setdefaultencoding('utf8')

code = sys.argv[1]

df = ts.get_today_ticks(code)
df = df.sort_values(by = 'time', ascending = True)
df = df[['time','price','volume','amount']]

print
print 'data start!'
for i, row in df.iterrows():
    print row['time'],
    print " ",
    print row['price'],
    print " ",
    print row['volume'],
    print " ",
    print row['amount']
print 'data end'