# Lloyd's Bank Interview --> Remove Duplicate List<Transactions> before returning to user

##To Start the server: 

mvn spring-boot:run

## Server Local Host URL: 
    http://127.0.0.1:8080/api/v1/transactions

##**Problem Statement <br>**
Exclude duplicates when ?unique=true is appended to the URI <br>
// 11 Data is inserted (data.sql) when spring application is started.  <br>

Use the GET (transactions) endpoint which retrieves all 11 duplicates data at the moment.  <br>
    • The task to remove duplicates when ?unique=true is appended to the URI <br>
    - A transaction is considered a duplicate iff the following fields all match: <br>
                //   * date time <br>
                //   * transaction type <br>
                //   * amount <br>
                //   * text <br>

<br>

## Approach 1:
1. Override equals and hashcode method in TransactionDTO class
2. Add below lines in the end of a "GET (transactions) endpoint" method in Controller class
   • HashSet<List<TransactionDTO>> setResponse= new HashSet<>()
   • response.addAll(transactionDTOList);
3. return the setResponse;