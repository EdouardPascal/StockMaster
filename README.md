# StockMaster
This is my first big Java project. It is a GUI program with Java and Swing framework that emulates the stock market and allows users to buy or  sell ~1000 stocks of their choice. It uses a SQL database for login and user authentification purposes as well as keeping track of user's balances and stock inventory.
<img width="683" alt="image" src="https://user-images.githubusercontent.com/8030550/232573774-d2869fa8-66dd-4724-baec-c58a45348b71.png">

The app support operations such as buying/selling stocks, depositing and retracting funds(not real funds).The app allow user to research stocks and provide the current price as well as a graph(using the JfreeChart library) made of the historical data from up to 1Year. The app use the Jsoup library to parse through the HTML link from the google finance website.
To do this I built a Web-Scrapper and used the JSoup library to automatically fetch and assemble real time data of the stock prices 
from the Google Finance website every 5 seconds
<img width="856" alt="Screenshot 2023-04-07 222202" src="https://user-images.githubusercontent.com/8030550/232573320-f15ce3d5-ca60-47dd-bc74-25994c4f3784.png">
