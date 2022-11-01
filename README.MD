http://127.0.0.1:8080/api/v1/transactions
Exclude duplicates when ?unique=true is appended to the URI
// 11 Data is inserted (data.sql) when spring application is started. 

Use the GET (transactions) endpoint which retrieves all 11 duplicates data at the moment. 
    • The task to remove duplicates when ?unique=true is appended to the URI
    - A transaction is considered a duplicate iff the following fields all match:
                //   * date time
                //   * transaction type
                //   * amount
                //   * text


Approach 1:
1. Override equals and hashcode method in TransactionDTO class
2. Add below lines in the end of a "GET (transactions) endpoint" method in Controller class
   • HashSet<List<TransactionDTO>> setResponse= new HashSet<>()
   • response.addAll(transactionDTOList);
3. return the setResponse;