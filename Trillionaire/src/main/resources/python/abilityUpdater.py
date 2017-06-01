#coding=utf-8

import tushare as ts
import sys

reload(sys)
sys.setdefaultencoding('utf8')

year = int(sys.argv[1])
quarter = int(sys.argv[2])

df1 = ts.get_profit_data(year,quarter)
df1.to_csv('src\\main\\resources\\TempFiles\\Ability\\profitability_' + sys.argv[1] + sys.argv[2] + '.csv')

df2 = ts.get_operation_data(2014,3)
df2.to_csv('src\\main\\resources\\TempFiles\\Ability\\operation_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

df3 = ts.get_growth_data(2014,3)
df3.to_csv('src\\main\\resources\\TempFiles\\Ability\\developing_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

df4 = ts.get_debtpaying_data(2014,3)
df4.to_csv('src\\main\\resources\\TempFiles\\Ability\\debt_paying_ability_' + sys.argv[1] + sys.argv[2] + '.csv')

print ('success!!')