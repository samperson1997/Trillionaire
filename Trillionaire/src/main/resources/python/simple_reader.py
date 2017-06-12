import pandas as pd
import  sys

result_file_path = sys.argv[1]
result_dict = pd.read_pickle(result_file_path)


print("-" * 20)
print(result_dict['summary']['total_returns'] - result_dict['summary']['benchmark_total_returns'])
print("-" * 20)

total = 0.0;
win = 0.0;
my = []
stra = []

for i, row in result_dict['portfolio'].iterrows():
    my.append(row['unit_net_value'])

for i, row in result_dict['benchmark_portfolio'].iterrows():
    stra.append(row['unit_net_value'])

for i, j in zip(my, stra):
    if i-j>0 :
        win += 1
    total += 1

print(win/total)


print("-" * 20)