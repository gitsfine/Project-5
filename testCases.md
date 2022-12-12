# Test Cases
## Test 1: Seller1 Create Account 
1.	User runs server
2.	User runs main method
3.	Types ‘yes’ to new user prompt
4.	Enters seller1 as username
5.	Enters password123 as password
6.	Enters seller1@gmail.com as email
7.	Types ‘yes’ to new seller prompt
Should see message: “Welcome seller: seller1”
8.	Views Homepage
9.	Selects “5. Log out”
10.	Logs out, quits program

Expectations: Program should run smoothly & user information should be saved.
## Test 2: Seller1 Login & Creates Stores
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters seller1 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
Should see message: “Welcome seller: seller1” 
6.	Creates store “Coffee Shop 1”
Should see message: “Store Created!”
7.	Selects “View or modify your stores”
8.	Selects “Coffee Shop 1” to edit
9.	Selects “Create new product” & creates the following:
•	Pumpkin Spice Latte, Oat, Latte, Pumpkin, Whipped Cream, 6, 10
•	Blonde Americano, None, Americano, None, Blonde Shots, 5, 10
Should see message: “Done!” both times
10.	Logs out

Expectations: User should be able to login and create store & products without issues.
## Test 3: Seller1 Views Sales
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters seller1 as username
4.	Enters password1234 as password
Should see message: “We could not find an account matching the provided information☹ Please try again!”
5.	Enters seller1 as username
11.	Enters password123 as password; login succeeds
Should see message: “Welcome seller: seller1”
6.	Selects “Dashboard of sale count by customer”
7.	Selects “Ascending”
Should see message: “You have no sales ☹”
8.	Creates new store “Coffee Shop 2”
9.	Selects “View or modify your stores” & “Coffee Shop 2” to edit
10.	Selects “Create new product” & creates the following:
•	Blonde Vanilla Latte, 2%, Latte, Vanilla, Blonde Shots, 6, 10
•	Peppermint Mocha, 2%, Mocha, Peppermint, Whipped Cream, 7, 10
Should see message: “Done!” both times
11.	Returns to homepage
12.	Clicks X in corner instead of logging out 

Expectations: No error messages, no sales yet, smooth creation of new store and products
## Test 4: Customer1 Create Account
1.	User launches application
2.	Types ‘yes’ to new user prompt
3.	Enters customer1 as username
4.	Enters password123 as password
5.	Enters customer1@gmail.com as email
6.	Types ‘no’ to new seller prompt
12.	Views Homepage
Should see message: “Welcome customer: customer1”
7.	Logs out

Expectations: Program should run smoothly & user information should be saved.
## Test 5: Customer1 Shops Checks Out
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters customer1 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
6.	Selects “List all stores”, chooses “Coffee Shop 1”
7.	Selects “Blonde Americano”, and selects “Add to cart”
8.	Select “view cart” and “checkout”
9.	Logs out
## Test 6: Seller2 Create Account & Stores; imports the products
1.	User creates file named “imports.txt”
File contains:
French Vanilla Latte, Soy, Latte, Vanilla & Hazelnut, None, 10, 3
Maple Cold Brew, None, Cold Brew, Maple, None, 9, 3
2.	User launches application
3.	Types ‘yes’ to new user prompt
4.	Enters seller1 as username; username is already taken
5.	Enters seller2 as username
6.	Enters password123 as password
7.	Enters seller2@gmail.com as email
8.	Types ‘yes’ to new seller prompt
9.	Views Homepage
13.	Creates store “Fancy Coffee Shop”
14.	Selects “View or modify your stores”
15.	Selects “Fancy Coffee Shop” to edit
16.	Selects “Imports products as csv” & types “imports.txt” as file path
17.	Views a success message
18.	Logs out
## Test 7: Customer2 Create Account
1.	User launches application
2.	Types ‘yes’ to new user prompt
3.	Enters customer2 as username
4.	Enters password as password; fails as doesn’t contain number
5.	Enters 1234567 as password; fails as doesn’t contain letter
6.	Enters password123 as password
7.	Enters customer1@gmail.com as email; sees email is already taken
8.	Enters customer2@gmail.com as email
9.	Types ‘no’ to new seller prompt
10.	Views Homepage
11.	Logs out
## Test 8: Customer2 Shops & Searches, Buys multiple drinks
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters customer2 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
6.	Selects “Search for product” and searches “Latte”
7.	Views three options, selects “Pumpkin Spice Latte”, and adds to cart
8.	Selects “Search for product” and searches “Blonde”
9.	Views two options, selects “Blonde Americano, and adds to cart
10.	Selects “List all stores”
11.	Selects “Coffee Shop 2”
12.	Selects “Peppermint Mocha”, and adds to cart
13.	Logs out
## Test 9: Customer2 Logs in, Checks out Cart, Views purchase history
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters customer2 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
6.	Selects “View cart”
7.	Selects “Checkout”
8.	Returns to homepage, 
9.	Selects “View dashboard of purchase”
10.	Selects “Descending”
11.	Sees “2 – Coffee Shop 1” and “1 – Coffee Shop 2”
12.	Returns to homepage
13.	Selects “List all stores”
14.	Selects “Coffee Shop 2”
15.	Selects “Peppermint Mocha”, and adds to cart
16.	Logs out
## Test 10: Seller1 Login & Views New Sales
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters seller1 as username
4.	Enters password1234 as password; login fails
5.	Enters seller1 as username
6.	Enters password123 as password; login succeeds
7.	Selects “Dashboard of sale count by customer”
8.	Selects “Ascending”
9.	Views “1 – customer1” & “3 – customer2”
10.	Selects “Dashboard of sale count by product”
11.	Selects “Descending”
12.	Views “2 – Blonde Americano” & “1 – Pumpkin Spice Latte” & “1 – Peppermint Mocha”
13.	Logs out
## Test 11: Seller2 Modifies and Adds Products
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters seller2 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
6.	Selects “View or modify your stores”
7.	Selects “Fancy Coffee Shop”
8.	Selects “List/Modify existing products”
9.	Selects “Maple Cold Brew” & “Edit Product”
10.	Changes it to: 
-	Chai Cold Brew, None, Cold Brew, Chai, None, 9, 3
11.	Returns to homepage
12.	Selects “View or modify your stores”
13.	Selects “Fancy Coffee Shop” to edit
14.	Creates new product:
-	Iced Chai Latte, Oat, Latte, Chai, Iced, 12, 5
-	Golden Cappuccino, Whole, Cappuccino, None, Gold Flakes, 100, 1
15.	Returns to homepage & logs out
## Test 12: Customer2 Logs in, buys new products, exports purchases
1.	User launches application
2.	Types ‘no’ to new user prompt
3.	Enters customer2 as username
4.	Enters password123 as password
5.	Logs in & Views Homepage
6.	Selects “View Cart”
7.	Deletes the peppermint mocha from cart
8.	Returns to homepage
9.	Selects “Search for product” and searches “Latte”
10.	Views four products, selects “Iced Chai Latte”
11.	Selects “Purchase Now”
12.	Returns to homepage
13.	Selects “List all stores”
14.	Selects “Fancy Coffee Shop”
15.	Selects “Golden Cappuccino”, and purchases now
16.	Returns to homepage
17.	Selects “List all stores”
18.	Selects “Fancy Coffee Shop”
19.	Selects “Golden Cappuccino”, and tries to purchase now; fails as is out of stock
20.	Returns to homepage
21.	Selects “Export csv dashboard of purchases by store”
22.	Enters “myPurchases” as export file path
23.	Sees a success message
24.	Logs out

Expectations: after going through this sequence the user should see a new file named “myPurchases” that contains the following:
Number of your purchases by store
1 - Coffee Shop 2
2 - Fancy Coffee Shop
2 - Coffee Shop 1
