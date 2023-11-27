# AdvancedLog 
Spigot plugin for logging events in a server. Useful for vanilla servers to track those who steal, grief, or kill when not allowed.

## Logging output:
A file with the date is created to log to using the directory in the config. At the start of a new day, the file is rolled over to the next days file
example file name: 2023-11-27.log

## Logging format:
1. Timestamp , 2. Log Type ID, 3. Components
1701098079565,0,{specific log type components here}
### Example for player "bbugsco" quit at postition 100, 74, -36 in world "world"
1701098079565,1,bbugsco,100,74,36,world
- A seperate program should be used to analyize logs rather than reading logs themselves

## Default config:
```YML
# Path to log folder
log_path: "/configure/path/here"

# Enable or disable log types
log_types:
  PLAYER_JOIN: true
  PLAYER_QUIT: true
  PLAYER_ADVANCEMENT: true
  PLAYER_LOCATION: true
  PLAYER_COMMAND: true
  PLAYER_DEATH: true
  PLAYER_KILL_PLAYER: true
  PLAYER_PICKUP_ITEM_DROP: true
  PLAYER_PICKUP_ITEM_CHEST: true
  SERVER_START: true
  SERVER_STOP: true

# Configure interval for logs (seconds)
log_intervals:
  PLAYER_LOCATION: 60


```
