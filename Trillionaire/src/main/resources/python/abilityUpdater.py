#coding=utf-8

import tushare as ts
import sys



year = int(sys.argv[1])
quarter = int(sys.argv[2])
classPath = sys.argv[3]

df1 = ts.get_profit_data(year,quarter)
df1.to_csv(classPath + 'profitability_' + sys.argv[1] + sys.argv[2] + '.csv')

df2 = ts.get_operation_data(2014,3)
df2.to_csv(classPath + 'operation_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

df3 = ts.get_growth_data(2014,3)
df3.to_csv(classPath + 'developing_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

df4 = ts.get_debtpaying_data(2014,3)
df4.to_csv(classPath + 'debt_paying_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

print ('success!!')