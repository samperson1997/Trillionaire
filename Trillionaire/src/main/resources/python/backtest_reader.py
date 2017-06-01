import pandas as pd
import  sys

result_dir_path = "./"

result_dict = pd.read_pickle(result_dir_path + sys.argv[1] + ".pkl")

#result_dict.keys()

#print(result_dict.keys())
#print(result_dict['summary'])

for k, v in result_dict['summary'].items():
    print(k + ":" + str(v))

print("-"*20)

for i, row in result_dict['portfolio'].iterrows():
    print(i,end=":")
    print(row['unit_net_value'])