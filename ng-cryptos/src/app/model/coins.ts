export interface Coin {
    name: string;
    symbol: string;
    price: number;
    up : boolean;

}

//dead code
/*export const coins : Coin[] = [
    {
        name: 'Bitcoin',
        symbol: 'BTC',
        price: 10300
    },
    {
        name: 'Ethereum',
        symbol: 'ETH',
        price: 1100
    },
    {
        name: 'Ripple',
        symbol: 'XRP',
        price: 1.13
    }, {
        name: 'Bitcoin Cash',
        symbol: 'BCH',
        price: 1500
    }, {
        name: 'Cardano',
        symbol: 'ADA',
        price: 0.5
    }, {
        name: 'Stellar',
        symbol: 'XLM',
        price: 0.522
    }, {
        name: 'Néo',
        symbol: 'NEO',
        price: 143.03
    }, {
        name: 'Litecoin',
        symbol: 'LTC',
        price: 163.44
    }
];*/

/*
export function priceToDollar(quantity, symbol) {

    // console.log(quantity, symbol);
    let result = 0;

    for (let i = 0; i < this.coins.length; i++) {

        let coin = this.coins[i];

        if (symbol === coin.symbol) {
            result = coin.price * quantity;
            //console.log('found', result);
            return result;
        }
    }

    // return 0
    throw symbol + 'Money not found';

}
*/
