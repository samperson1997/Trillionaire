import sys
from rqalpha import run_file

strategy_id = sys.argv[1]
starting_cash = int(sys.argv[2])
s_date = sys.argv[3]
e_date = sys.argv[4]
frequency = sys.argv[5]
matching_type = sys.argv[6]
benchmark = sys.argv[7]
commission_multiplier = float(sys.argv[8])
slippage = float(sys.argv[9])
strategy_file_path = sys.argv[10]
out_file_path = sys.argv[11]

config = {
  "extra": {
    "log_level": "error",
  },
  "base": {
    "securities": "stock",
    "matching_type": matching_type,
    "start_date": s_date,
    "end_date": e_date,
    "stock_starting_cash": starting_cash,
    "benchmark": benchmark,
    "frequency": frequency,
    "strategy_file": strategy_file_path,
  },
  "mod": {
    "sys_analyser": {
      "enabled": True,
      "output_file": out_file_path,
    },
    "sys_simulation":{
      "enabled":True,
      "slippage":slippage,
      "commission-multiplier":commission_multiplier,
    },
  },
}

run_file(strategy_file_path, config)
