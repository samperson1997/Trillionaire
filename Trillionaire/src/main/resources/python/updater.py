#coding=utf-8

import tushare as ts
import sys

reload(sys)
sys.setdefaultencoding('utf8')

df = ts.get_today_all()
df.to_csv('src/main/resources/TempFiles/RealTime/realtime.csv')

print 'run success!!!'