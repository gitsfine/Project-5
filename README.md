# CS 18000 - Project 4

## How to Compile and Run the Project 
1. Import all files
2. Create data.txt user file in specified format (below)
3. Run main method


## Submission 
Olivia Lund - Submitted Vocareum workspace  
Heewon Kim - Submitted report on Brightspace  

## Description of Classes

### 1. Marketplace (main)  

### Methods:  

- createAccount
  - asks for email  
    - try-catch used to make sure input follows standard email format  
    - try-catch used to make sure input hasn’t been used before  

  - asks for a username
    - try-catch used to make sure input follows format  		
    (at least one character and one number)  
    - try-catch used to make sure input isn’t already taken

  - asks for a password
    - try-catch used to make sure input follows format 				
    (at least one character, one number, more than 6 characters)
    - constructs new User object
    - offers option to user to either be a Seller or a Customer
    - constructs either new Seller or Customer object

- loginToAccount
  - asks for email
    - try-catch used to make sure input follows standard email format
  - asks for password
    - try-catch used to make sure there is an existing account with the provided email and password
    - error message used if no account exists

- editAccount
    - prompts user with questions what they would like to edit
    - implements said changes using getters & setters

- deleteAccount
  - asks the user for email and password
    - try-catch used to make sure there is an existing account with the provided email and password
    - error message used if no account exists
  - erases the file contents associated with said User

- listStores
  - prints out current list of stores using Store

- processData
  - creates an arrayList
    - new ArrayList < User > users = new ArrayList<>();
  - instantiates a fileReader object to read in data.txt
    - at NEW USER   							
    (next three lines have the parameters needed to construct a new user)
      - create another new arrayList to help keep track of the stores
    - at NEW STORE   						
    (following lines will contain the store name and product info needed to construct new stores)
      - create another new arrayList to help keep track of the products
  - for-loop used to add the new products to the current store
    - at SALES, CART, and PURCHASES 
      - similar process

- Statistics (based on the user’s boolean seller value):
    - Sellers can view a dashboard that lists statistics for each of their stores
      - data will include a list of customers with the number of items that they have purchased and a list of products with the number of sales
      - sellers can choose to sort the dashboard
      - utilize the sales array lists for each store they have
    - Customers can view a dashboard with store and seller information.
      - data will include a list of stores by number of products sold and a list of stores by the products purchased by that particular customer
      - customers can choose to sort the dashboard
      - utilize the purchased array lists

- exportFile (based on the user’s boolean seller value):
  - Sellers can import or export products for their stores using a csv file
    - all product details should be included, with one row per product
  - Customers can export a file with their purchase history

### Main Method Process:
- calls processData
- provides menu that allows users to create/edit/login/delete an account
  - call associated methods

### Seller Track:
- provides menu that allows user to either create/edit/delete their stores, exportFile, or view their statistics

### Customer Track:
- provides menu that allows user to listStores, exportFile, or view their statistics
  - allows user choose to listProducts
    - allows to buy a product
    - update user’s purchase log
- provide option for users to view/edit their shopping cart  

___

### 2. User

### Fields/Constructor Parameters:
- String username
- String email
- String password  

### Methods:
- getters/setters for fields  

___

### 3. Seller (User subclass)

### Fields/Constructor Parameters:
- super(username, email, password)
- boolean seller (true)
- ArrayList<String> sales containing sales

### Methods:
- createStore 
  - constructs a new store object based on provided information
  - write the new store into the user’s file
  - prompt the user with questions to gather the information needed

- deleteStore 
  - deletes the store requested based on provided information
  - remove the old store from the user’s file
  - prompt the user with questions to gather the information needed

- editStore 
  - allows the seller to edit the store with getters & setters
  - try-catch used for products that does not already exist
  - prompt the user with questions that ask them about the changes they would like to make
___

### 4. Customer (User subclass)
  
### Fields/Constructor Parameters:
- super(username, email, password)
- boolean seller (will be false)
- ArrayList<String> cart
- ArrayList<String> purchased

### Methods:
- getters/setters for fields
- buy a product
  - calls the products sell method
  - updates the purchased array list
- addToCart a product
  - updates the cart array list
- editCart
  - allows user to edit and view the cart

___

### 5. Store

### Fields/Constructor Parameters:
- String storeName
- ArraryList<Product> products
- ArrayList<String> sales

### Methods:
- addProduct 
  - constructs a new product object based on provided information
  - adds object to an array list
  - writes the new product into the user’s file
  - prompts the user with questions to gather the information needed

- editProduct 
  - uses getters/setters to edit an already existing product
  - try-catch used for products that don’t already exist
  - prompts the user with questions to gather the information needed

- deleteProduct 
  - deletes a product object based on provided information
  - removes object from array list
  - removes the old product from the user’s file
  - prompts the user with questions to gather the information needed

- listProducts 
  - prints out the items from this store
  - uses Products to print out the current available products
  - calls product.toString() to get the description

- getters/setters for fields
- toString returns a string formatted as “username’s store: store_name”

___

### 6. Product

### Fields/Constructor Parameters:

- String product_name
- String milk_type (options: cream, 2%, oat, almond, etc.)
- String coffee_type (options: cold brew, latte, cappuccino, etc.)
- String syrup (options: vanilla, caramel, mocha, pumpkin, etc.)
- String special (options: decaf, blonde, extra hot, etc.)
- int price
- int quantity

### Methods:
- toString
  - creates a string description based on the drink’s qualities
  - used as the product’s description in the marketplace and sellers’ files
  - Format:
    - Pumpkin Spice Latte
      Type: Latte | Milk: Oat | Syrup: Pumpkin | Special: Extra Hot
      Price: $5 | Quantity: 5

- getters/setters for fields
- sell
  - called when product is sold
  - update the quantity of product
  - setter used to modify the shop’s sales array list


