CREATE TABLE orders (
  Order_Key int(11) NOT NULL AUTO_INCREMENT,
  Card_Key int(11) NOT NULL,
  User_Key int(11) NOT NULL,
  Store_Key int(11) NOT NULL,
  Order_Status varchar(25) DEFAULT NULL,
  Order_Amount decimal(7,2) DEFAULT NULL,
  Order_Date date DEFAULT NULL,
  PRIMARY KEY (Order_Key)
) ;


