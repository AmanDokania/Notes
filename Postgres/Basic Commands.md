
## Database related command

### show databases   `\l`
### connect database `\c name of database`
### set a particulat schema `set search_path to sample`


psql -U postgres

```
SELECT pg_terminate_backend(pg_stat_activity.pid) FROM pg_stat_activit WHERE pg_stat_activity.datname = 'database_name' AND pid <> pg_backend_pid();
```

	
