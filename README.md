# balance-service

Offers one service

```
/reporting/balancesByAddresses
```

which accepts the following payload.

```
{
	"addresses": [
		"Company",
		"Employee"
	],
	"fromDate": "2019-08-26",
	"toDate": "2019-11-26",
	"balanceTime": "23:59Z"
}
where
addresses=list of addresses we want the report for
fromDate=date the report should start
toDate=date the report should end
balanceTime=what time of the day the report should be made for
```

It will return for each address a list with the balance for each day in the range.

Example curl when the application is running locally:
```
curl -X POST http://localhost:8080/reporting/balancesByAddresses \
-H "Content-Type: application/json" \
-d '{
	"addresses": [
		"Company",
		"Employee"
	],
	"fromDate": "2019-08-26",
	"toDate": "2019-11-26",
	"balanceTime": "23:59Z"
}'
```
