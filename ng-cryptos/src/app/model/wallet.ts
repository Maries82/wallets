
import {PricingService} from "../pricing.service";
export class Line {
    constructor(public symbol: string, public quantity: number, public value:number) {
    }
}
export class Wallet {
    lines: Line[] = [];
    pricingService : PricingService;

    deposit(dollars: number) {


      let usdLine = this.lines
        .find(line => line.symbol === 'USD');

      // check if (usdLine=== undefined){}
        if (usdLine === undefined){
          this.lines.push(new Line('USD', dollars, dollars));
        } else{
          usdLine.quantity += dollars;
        }

    }



    buy(quantity: number, symbol: string, value : number) {
        let usdLine = this.lines.find(coin => coin.symbol === 'USD');
        let coinAmount = this.pricingService.priceToDollar(quantity, symbol);
        //console.log(coinAmount);

        //new amount en USD
        usdLine.quantity = usdLine.quantity - coinAmount;
        usdLine.value = null;


        // new amound de notre coin

        let symbolLine = this.lines.find(line => line.symbol === symbol);

      if (symbolLine === undefined){
        this.lines.push(new Line(symbol, quantity,value));
      } else{
       symbolLine.quantity += quantity;
       symbolLine.value += coinAmount;
      }

    }

  sell(quantity: number, symbol: string, value : number) {
    let usdLine = this.lines.find(line => line.symbol === 'USD');
    let symbolLine = this.lines.find(coin => coin.symbol === symbol);

    let coinAmount = this.pricingService.priceToDollar(quantity, symbol);
    //console.log(coinAmount);

    //new amount de dollarcle

    usdLine.quantity = usdLine.quantity + coinAmount;
    usdLine.value = null;

    // new amound de nos coins


    if (symbolLine === undefined){
      console.log("Tu n'as pas de" + symbolLine.symbol+"dans ton portefeuille");

    } else{
      symbolLine.quantity -= quantity;
      symbolLine.value -= coinAmount;
    }

  }


    totalDollarValue(): number {

      let total = 0;

      for (let i = 0; i < this.lines.length; i++) {
        let currentLine = this.lines[i];
        console.log(currentLine);

        if (currentLine.symbol === 'USD') {
          total = total + currentLine.quantity;
        }
        else {
          total += this.pricingService.priceToDollar(currentLine.quantity, currentLine.symbol);
        }
      }

      return total;
    }


     /* 2eme methode:
     this.lines.reduce(function (total, line){
        return line.symbol ==='USD’ ?
          total + line.quantity : total+ priceToDollar(line.quantity, line.symbol);
      }, 0);
      console.log(total);
    }*/


    //getLinesOrderesByValue()
}


