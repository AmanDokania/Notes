### To restore database from an dump file 
```bash
psql -d employee_journal_dev -h localhost -U postgres < '/home/kushal/work-diary-dev-21-mar-2023-backup.dump'

psql -U <username> -W -d postgres -h localhost -p 5432 --set ON_ERROR_STOP=on -f <bckup_filename>.sql

```
- `-d` : Database name
- `-h` : Hostname
- `-U` : Username (Keep Default : **postgres**)

### To create a database's dump
```bash
pg_dump -d postgres://employee_journal_dev_user:zwtgfJ7eY84PEzQ6Zh7Az7AvC3XVbnUsMttChMezbaLGhZFhMHuGuMHEeyyU2AnE@org40-dev.cqr1ocgaeexp.ap-south-1.rds.amazonaws.com:5432/employee_journal_dev > wd-dev.dump
```
