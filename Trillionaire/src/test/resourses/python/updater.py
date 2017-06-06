#coding=utf-8

import tushare as ts
import sys

reload(sys)
sys.setdefaultencoding('utf8')

path = sys.argv[1]

df = ts.get_today_all()
df.to_csv(path)

print 'run success!!!'