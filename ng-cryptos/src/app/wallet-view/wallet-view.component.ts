import {Component, OnInit} from '@angular/core';
import {Wallet} from "../model/wallet";
import {PricingService} from "../pricing.service";

@Component({
  selector: 'app-wallet-view',
  templateUrl: './wallet-view.component.html',
  styleUrls: ['./wallet-view.component.css']
})
export class WalletViewComponent implements OnInit {


  wallet = new Wallet()


  constructor( public pricingService:PricingService) {
    this.wallet.pricingService = pricingService;

    pricingService.loadPrices()
      .then(data => console.log('>>>>>',data))
      .then( () => this.initWallet())
    // function without param; prices are now loaded
  }


  ngOnInit() {
  }

  initWallet(){

     this.wallet.deposit(1000000);
     this.wallet.buy(0,'BTC',0);
     this.wallet.buy(0,'XRP',0)


  }


  walletDeposit(value: string) {

    let money = parseInt(value);

    if (money > 0){
      this.wallet.deposit(money);
    }
    console.log(this.wallet.lines);

  }

  getRandomInt(numb) {
    return Math
      .floor(Math.random() * numb);
  }

  aleatoire(min, max) {
    return Math.floor(Math.random() * (max - min + 1)) + min;
  }

  moneyGame() {
    let value = this.getRandomInt(50);
    let entier = this.aleatoire(-1, 1);
    return this.wallet.deposit(value * entier);
  }

  walletBuy(quantity,symbol,value){
        this.wallet.buy(parseInt(quantity),symbol,value);
  }

  walletSell(quantity,symbol,value){
    this.wallet.sell(parseInt(quantity),symbol,value);
  }

  walletUpdatePrice() {
    console.log("verif si mis a jour des prix");
    return this.pricingService.loadPrices();
  }

  getColorWallet(symbol){
    return this.pricingService.getColor(symbol);
  }

  }


