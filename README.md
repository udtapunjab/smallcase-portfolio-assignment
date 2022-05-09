
## API Reference

API Endpoint: https://smallcase-portfolio-task.herokuapp.com

#### Get portfolio

```http
  GET /portfolio
```


#### Get Info of Specific Security

```http
  GET /portfolio/{ticker}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of Security to fetch |

#### Get all trades info

```http
  GET /trades
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `id`      | `string` | **Required**. Id of Security to fetch |



#### Add a trade

```http
  POST /trade
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `trade`      | `json` | **Required**. Trade in a json format |

```
eg: 
{
    "tradeType": "BUY",
    "ticker": "TCS",
    "quantity": "4",
    "pricePerShare": "100"
}
```

#### Update a trade

```http
  PUT /trade
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `trade`      | `json` | **Required**. Trade with TradeId in a json format |

```
eg: 
{
    "tradeId": -6135333656717001595,
    "tradeType": "BUY",
    "ticker": "TCS",
    "quantity": 5,
    "pricePerShare": 400.0
}
```

#### Delete a trade

```http
  DELETE /trade/{tradeId}
```

| Parameter | Type     | Description                       |
| :-------- | :------- | :-------------------------------- |
| `tradeId` | `long` | **Required**. tradeId of the trade |

#### Fetch the returns

```http
  GET /returns
```

