#coding=utf-8

import tushare as ts
import sys

# reload(sys)
# sys.setdefaultencoding('utf8')

df = ts.get_today_all()
df = df[['code','name','changepercent','trade','open','high','low','settlement','volume','turnoverratio','amount','per','pb','mktcap','nmc']]


print()
print ('data start!')
for i, row in df.iterrows():
    print (row['code'],row['name'],row['changepercent'],row['trade'],row['open'],row['high'],row['low'],row['settlement'],row['volume'],row['turnoverratio'],row['amount'],row['per'],row['pb'],row['mktcap'],row['nmc'],sep=',')
print ('data end')