import pandas as pd
import  sys

result_file_path = sys.argv[1]

result_dict = pd.read_pickle(result_file_path)

print("-" * 20)
print(result_dict['summary']['total_returns'])
print(result_dict['summary']['annualized_returns'])
print(result_dict['summary']['benchmark_total_returns'])
print(result_dict['summary']['benchmark_annualized_returns'])
print(result_dict['summary']['alpha'])
print(result_dict['summary']['beta'])
print(result_dict['summary']['sharpe'])
print(result_dict['summary']['sortino'])
print(result_dict['summary']['information_ratio'])
print(result_dict['summary']['volatility'])
print(result_dict['summary']['max_drawdown'])
print(result_dict['summary']['tracking_error'])
print(result_dict['summary']['downside_risk'])
print("-" * 20)
for i, row in result_dict['portfolio'].iterrows():
    print(i)
print("-" * 20)
for i, row in result_dict['portfolio'].iterrows():
    print(row['unit_net_value'])
print("-" * 20)
for i, row in result_dict['benchmark_portfolio'].iterrows():
    print(row['unit_net_value'])
print("-" * 20)