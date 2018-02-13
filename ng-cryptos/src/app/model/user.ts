// Into the model

import { Wallet} from "./wallet";
export class User{


  id: number;
  name : string;

  //protip : always bette to initiate  an array

  wallets:Wallet[] = [];
  users:User[] = [];
}
