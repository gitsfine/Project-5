# CS 18000 - Project 5

## How to Compile and Run the Project 
1. Run the MarketplaceServer class
2. Run the Main class


## Submission 
Olivia Lund - Submitted Vocareum workspace  
Heewon Kim - Submitted report on Brightspace  

## Description of Classes

### 1. Main  

### Methods:  

- startUserInput
  - shows “Welcome to Coffee Shop MarketPlace!” message
  - sets status variable to signUpOrLogin
- uses status to determine whether user is a seller or a customer
  	-
- signUpOrLogin
  - do while until newUserPromptInput is null or isEmpty
- if statements for newUserPromptInt being yes or quit
-do while for usernames that do not fit criteria of username or are already taken
-do while for passwords that do not fit criteria of  passwords or are already taken
-do while for emails that do not fit criteria of emails or are already taken
- while loop for if non new user is not able to login
- if statement for login not being able to happen

   - readNumberOption
   	 - while loop for input not being a valid number

- promptDescending
  - asks the user  to selection option to perform for dashboard
- listStores

- runSellerHandler
-gives Seller options
-while loop for option being null
-switch-case for options seller can do
-
 
      -createStore 
- do while for if storeName is null or isEmpty
- if statement for store creation 
 -modifyStore 
- if statement for stores array being empty
-ask seller which store they would like to modify
-for loop for navigating stores
- gives options to seller

-listSales
	-used to list and format sales
-createNewProduct
	- do whiles for names, milk type, coffee type,syrup,special,price and quantity
-modifyProduct
	- switch-case for choices for how to modify a product

-runCustomerHandler
	-switch-case for choices for what a customer can do
-listAllStores
	-it lists all the stores
-searchForProduct
	-it searches for the products
-viewCart
	-it views the cart
-shopFromMarketPlace
	-it allows customers to buy a product
-loadMarketPlace
	-try catch
-creates File and ObjectInput streams, reads Marketplace, closes Fille and ObjectInput streams
-saveMarketplace
	-try catch
		-creates File and ObjectOutput streams, writes Marketplace, closes Fille and ObjectOutput streams

		-

### Main Method Process:
- runs the Marketplace code through GUIs
### Seller Track:
- provides menu that allows user to either create/edit/delete their stores, exportFile, or view their statistics

### Customer Track:
- provides menu that allows user to listStores, exportFile, or view their statistics
  - allows user choose to listProducts
    - allows to buy a product
    - update user’s purchase log
- provide option for users to view/edit their shopping cart  

___
### 2. MarketplaceServer

### Fields/Constructor Parameters:
ServerSocket serverSocket;
 Marketplace marketplace;
Boolean running;


### Methods:
-loadMarketplace
	-try catch
		- creates a new FileInputStream
		- creates a new ObjectInputStream
		-reads in Marketplace
		-closes FileInputStream
		- closes ObjectInputStream
		- return Marketplace
 
-saveMarketplace

	-try catch
		- creates a new FileOutputStream
		- creates a new ObjectOutputStream
		-writes out Marketplace
		-closes FileInputStream
		- closes ObjectInputStream
		- return Marketplace
___
### 3. MarketplaceClient
### Fields/Constructor Parameters:
 private Socket client = null;
   private ObjectOutputStream out = null;
   private ObjectInputStream in = null;
 
  

### Methods:
-disconnectClient
	-try catch used to close out and in 

-killServer
	- calls executeCommand() to quit

-executeCommand
	-try catch used to reset out, write the ServerCommand object, calls flush(), returns in reading ServerCommand

-isLoggedInUserSeller
	-creates a new ServerCommand named isLoggedInUserSeller
	-calls executeCommand
	- returns outBool
-getLoggedInUsername
- returns outString
-createAccount
	-creates a new ServerCommand named createAccount
	-creates a new inStrings array
	-creates a new inBools array
	-calls executeCommand
	-returns outBool
-attemptLogin
	-creates a new ServerCommand named attemptLogin
	-creates a new inStrings array
	-creates a new inBools array
	-calls executeCommand
	-returns outBool
-listAllStores
	-creates a new ServerCommand named listAllStores
	-calls executeCommand
	-returns outStoreList

-viewProducts
	-creates a new ServerCommand named viewProducts
	-calls executeCommand
	-returns outStoreList
searchProducts
-creates a new ServerCommand named searchProducts
	-creates a new inStrings array
	-calls executeCommand
	-returns outProductList

buyNow
	--creates a new ServerCommand named searchProducts
	-creates a new inStrings array
	-calls executeCommand
	-returns outBool

addToCar
--creates a new ServerCommand named searchProducts
	-creates a new inStrings array
	-calls executeCommand
	-returns outBool
 
viewCart
	--creates a new ServerCommand named searchProducts
	-calls executeCommand
	-returns outProductList
 
	-
deleteItemFromCart
--creates a new ServerCommand named searchProducts
	-creates a new inProducts array
 
checkoutCart
--creates a new ServerCommand named searchProducts
	-creates a new inProducts array
 
	-returns outBool
 
dashboardSaleCounterByStore
--creates a new ServerCommand named searchProducts
	-creates a new inBools array
	-calls executeCommand
 
	-returns outString
 
dashboardPurchasesByStore
--creates a new ServerCommand named searchProducts
	-creates a new inBools array
 
	-calls executeCommand
 
	-returns outString
 
exportDashboardPurchasesByStore
--creates a new ServerCommand named searchProducts
	-calls executeCommand
	-creates a new inBools array
 
	-returns outString
 
createStore
-creates a new ServerCommand named searchProducts
	-creates a new inString array
 
	-calls executeCommand
 
	-returns outBool
listStoresForSeller
-creates a new ServerCommand named searchProducts
 
	-calls executeCommand
	-creates a new inString array
 
	-returns outSoreList
listProducts
-creates a new ServerCommand named searchProducts
	-calls executeCommand
 
	-returns outString
 
createProduct
	--creates a new ServerCommand named searchProducts
 
	-calls executeCommand
 
	-returns outBool
removeProduct
-creates a new ServerCommand named searchProducts
	-calls executeCommand
 
	-returns outString
 
editProduct
-creates a new ServerCommand named searchProducts
 
	-calls executeCommand
	-creates a new inProducts array
	-creates a new inString array
	-creates a new inInts array
 
 
	-returns outString
 
customerRevenueSaleList
-creates a new ServerCommand named searchProducts
 
	-calls executeCommand
 
	-returns outString
 
getProductList
-creates a new ServerCommand named searchProducts
-creates a new inStores array

-returns outString
 
exportProductList
-creates a new ServerCommand named searchProducts
creates a new inStores array
creates a new inStrings array
 
	-calls executeCommand
 
	-returns outString
 
importProductList
	-calls executeCommand
creates a new inStores array
creates a new inStrings array
 
	-returns outBool
 
dashboardCustomerNumSale
-creates a new ServerCommand named searchProducts
 
	-calls executeCommand
-creates a new inBoolss array
 
	-returns outString
 
dashboardProductNumSales
-creates a new ServerCommand named searchProducts
 
	-calls executeCommand
creates a new inBools array
 
	-returns outString
 
 

 
 
 






___
### 3. User

### Fields/Constructor Parameters:
- String username
- String email
- String password  

### Methods:
- getters/setters for fields  

___

### 3. Seller (User subclass)

### Fields/Constructor Parameters:
String userName;
 String email;
  String password;
  

### Methods:
-getUsername
	-returns usernames

-getStores
	-returns stores
-getEmails
	-returns emails


### 3. User

### Fields/Constructor Parameters:
- String username
- String email
- String password  

### Methods:
- getters/setters for fields  

___

### 3. Seller (User subclass)

### Fields/Constructor Parameters:
String userName;
 String email;
  String password;
  

### Methods:
-getUsername
	-returns usernames

-getStores
	-returns stores
-getEmails
	-returns emails

___

### 3. Seller (User subclass)

### Fields/Constructor Parameters:
String userName;
 String email;
  String password;
  

### Methods:
-getUsername
	-returns usernames

-getStores
	-returns stores
-getEmails
	-returns emails

___


### 3. User

### Fields/Constructor Parameters:
- String username
- String email
- String password  

### Methods:
- getters/setters for fields  

___

### 3. Seller (User subclass)

### Fields/Constructor Parameters:
String userName;
 String email;
  String password;
  

### Methods:
-getUsername
	-returns usernames

-getStores
	-returns stores
-getEmails
	-returns emails

___

### 4. Customer (User subclass)
  
### Fields/Constructor Parameters:
- super(username, email, password)
- ArrayList<Sale> history
- ArrayList<String> cart

### Methods:
- getCart()
-addtoCart
	-updates cart array list if product is not  in the  cart
- removeFromCart
-removes product from cart   
- addToSaleHistory
  - updates the history array list
- checkout
  - allows users to checkout products from cart
___

### 5. Store

### Fields/Constructor Parameters:
- String storeName
-Seller owner
- ArraryList<Product> products
- ArrayList<String> sales

### Methods:
- addProduct 
  - constructs a new product object based on provided information
  - adds object to an array list
  - writes the new product into the user’s file
  - prompts the user with questions to gather the information needed

- editProduct 
  - uses getters and for loop to see if product exists
  - calls edit() to edit the product

- deleteProduct 
  - deletes a product object based on provided information
  - removes product from product array list
  
addSales
 	  - updates the sales array list

- getSales
	- returns sales

- getNames
	- returns names


- getProducts
	- returns products

__

### 6. Product

### Fields/Constructor Parameters:
Store store
- String name
- String milk_type (options: cream, 2%, oat, almond, etc.)
- String coffee_type (options: cold brew, latte, cappuccino, etc.)
- String syrup (options: vanilla, caramel, mocha, pumpkin, etc.)
- String special (options: decaf, blonde, extra hot, etc.)
- Integer price
- Integer quantity

### Methods:
- toString
  - creates a string based on the name of the store and the value of the toListing() method.

- getters/setters for fields
-toListing
	- creates a string based on name, milkType, coffeeType  , syrup  ,  special  , price, and dollar symbol
- toExport
	- creates a string based on name, milkType,coffeeType  , syrup  ,  special  , price ,  and quantity

- buy
  - called when product is bought
  - remove one unit quantity of product
  - updates shop’s history array list
  - updates shop’s sales array list

-edit
	- sets the name, milkType,coffeeType  , syrup  ,  special  , price ,  and quantity

### 7. ServerCommand
### Fields/Constructor Parameters:
- String commandName
String[] inStrings;
   Boolean[] inBools;
 Store[] inStores;
   Integer[] inInts;
   Product[] inProducts;
 
 Boolean outBool;
  String outString;
   List<Store> outStoreList;
   List<Product> outProductList;
  

