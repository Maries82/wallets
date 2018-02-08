import { Injectable } from '@angular/core';
import {Coin} from "./model/coins";
import {HttpClient} from "@angular/common/http";

@Injectable()
export class PricingService {

   //Asynchrone
  coins: Coin[];

  constructor(public http:HttpClient) { }

  getColor(symbol){
  let coin = this.coins.find(coin => coin.symbol === symbol);
    if (symbol === 'USD'){
      return 'black';
    }else if (coin.up == true){
      return 'green';
    } else if (coin.up == false){
     return 'red'
}

  }

  loadPrices() {
    let url = 'https://api.coinmarketcap.com/v1/ticker/?limit=10';

    function mapper(coin){

      return {
        name : coin.name,
        symbol:coin.symbol,
        price:coin.price_usd,
        up :  coin.percent_change_1h > 0 ? true : false
      }
    }

    /*function mapper me permet de renvoyer dans un format que l'on veut avec les noms qu'on veut*/
    return this.http.get(url)
      .toPromise()
      .then( internetCoins => (internetCoins as any).map(  mapper ) )
      .then( joliCoins =>{
        this.coins = joliCoins;
        return joliCoins;
      });

    // Ctl + Alt + L

  }


  priceToDollar(quantity,symbol){

    for (let i = 0; i < this.coins.length; i++) {
      let coin = this.coins[i];
      if (symbol === coin.symbol) {
        //console.log('found', result);
        return coin.price * quantity;
      }
    }

    // return 0
    throw symbol + 'Money not found';
  }


}
