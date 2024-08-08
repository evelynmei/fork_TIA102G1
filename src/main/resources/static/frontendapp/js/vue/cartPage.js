import { createApp, ref, computed, onMounted, toRaw } from 'https://unpkg.com/vue@3/dist/vue.esm-browser.prod.js';
import axios from 'https://unpkg.com/axios@1.7.2/dist/esm/axios.min.js';

/*
todo 判斷用戶->
     未登入: 存入sessionStorage
     登入: 透過cookie取得emberId
 */
let memberId = "1";
const apiURL = "http://localhost:8080/cart/";
const productURL = "frontendapp/img/products/";

const dumbProducts = {
    1001: {
        name: "巧克力餅乾",
        price: 300,
        picture: productURL + "04.jpg"
    },
    1002: {
        name: "巧克力蛋糕",
        price: 400,
        picture: productURL + "09.jpg"
    },
    1003: {
        name: "白巧克力餅乾",
        price: 500,
        picture: productURL + "07.jpg"
    },
    1004: {
        name: "白巧克力餅乾",
        price: 500,
        picture: productURL + "08.jpg"
    },
}
const dumbItems = [
    {cartId:1, picture: "圖片", productName: "巧克力餅乾", price: 100, proAmount: 1,},
    {cartId:2, picture: "圖片", productName: "牛奶糖餅乾", price: 200, proAmount: 2,}
];
let products = dumbProducts;


createApp({
    setup() {
        let items = ref(dumbItems);
        const isDiscount = ref(false);
        const codeInput = ref("");
        const coupons = [];


        /**
         前端頁面資料推演，購物車，CartViewObject，需要什麼資料？
         Cart: CartId、MemberId、ProductId、ProAmount、JoinDt
         流水號PK、會員編號FK、商品編號FK、商品數量、加入日期

         CartViewObject:
         購物車項目 Id CartId、商品圖片、商品品項名、商品單價、商品數量、總計
         所屬會員 Id

         **/

        onMounted( async() => {
            let { data } = await axios.get( apiURL + memberId);
            let { productInfos, cartList } = data;   // JavaScript、Python 裡的解構賦值
            // console.log(productInfos);
            coupons.push(...data.coupons);

            // 用 JavaScript 裡 Array.reduce 方法，把 array 資料轉成一個 JavaScript Object
                // const newProducts = productInfos.reduce(reduceCallback, {});
                // console.log(newProducts);
                // products = newProducts;

            items.value = cartList.map(mapCallback);
            console.log(toRaw(items.value));

        })

        //計算屬性: 為使前端頁面更新計算結果, 需使用computed()
        const sum = computed(() => {
            let sumTemp = 0;
            for (let i = 0; i < items.value.length; i++) {
                const subTotal = items.value[i].price * items.value[i].proAmount;
                sumTemp = sumTemp + subTotal;
            }
            return sumTemp;
        })

        //刪除商品
        const deleteItem = index => {
            let array = items.value;
            let itemToDelete = array[index];
            let cartId = itemToDelete.cartId;

            // array 裡需至少有一項才執行，否則短路不繼續運算，不短路則 spice 掉陣列裡第 i 個 item
            array.length > 0 && array.splice(index, 1);

            console.log("item = ", toRaw(itemToDelete));
            console.log("cartId =", cartId);

            // 送出刪除購物車的 api 請求
            axios.delete(apiURL + cartId)
                .then(res => console.log(res))
                .catch(err => console.log(err));
        };


        const discountHandler = () => {

            // 檢查輸入的 codeInput.value 跟 coupons 裡的 couponCode 是否符合
            console.log(codeInput.value);
            console.log(coupons);
            const a = coupons.some(coupon => coupon.couponCode === codeInput.value);
            console.log(a)

            isDiscount.value = !isDiscount.value;
        }

        return {
            items,
            sum,
            isDiscount,
            codeInput,
            deleteItem,
            discountHandler,
        }
    }
}).mount('#shopping-cart');

function reduceCallback(accumulator, current){
    accumulator[current.productId] = {
        name: current.proName,
        picture: productURL + "0" + (current.productId-1000) + ".jpg",
        price: current.proPrice,
        others: current
    };
    return accumulator;
}

function mapCallback(item){
    let id = item.productId;
    item.productName = products[id].name;
    item.price = products[id].price;
    item.picture = products[id].picture;
    item.sum = item.price * item.proAmount;
    item.url = "productDetails/" + item.cartId
    return item;
}