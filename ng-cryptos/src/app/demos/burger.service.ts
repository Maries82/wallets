import { Injectable } from '@angular/core';
import {BunService} from "./bun.service";
import {SteakService} from "./steak.service";

@Injectable()
export class BurgerService {

  constructor(public bunService: BunService,
              public steakservice : SteakService) { }



  getPrice(){
    return 10;
  }
}
