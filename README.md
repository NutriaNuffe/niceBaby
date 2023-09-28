# niceBaby

## 文件
> Postman Collection：[nice.postman_collection.json](_doc/nice.postman_collection.json)

## 產生 SSL 憑證
> 1. 新增 openssl.cnf
> 2. 在 Terminal 輸入指令取得私鑰：openssl req -nodes -newkey rsa:2048 -sha256 -keyout myserver.key -out server.csr -config D:\niceBaby\openssl.cnf