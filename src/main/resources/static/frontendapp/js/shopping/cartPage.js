let { createApp, ref, computed, onMounted, toRaw } = Vue;

var memberId = document.getElementById("memberId").value;
const cartURL = "/api/cart/";
const couponURL = "/api/coupon/";
const productURL = "images/product_pic/";
const mockProducts = {
    1001: {
        name: "假資料原味餅乾",
        price: 100,
        picture: productURL + "7001.jpg"
    },
    1002: {
        name: "假資料牛奶糖餅乾",
        price: 200,
        picture: productURL + "7002.jpg"
    }
}
const mockItems = [
    {cartId:1, picture: productURL + "images/product_pic/7001", productName: "假資料原味餅乾", price: 100, proAmount: 1,},
    {cartId:2, picture: productURL + "images/product_pic/7002", productName: "假資料牛奶糖餅乾", price: 200, proAmount: 2,}
];
let products = mockProducts;

createApp({
    setup() {
        let items = ref(mockItems);
        const isDiscount = ref(false);
        const codeInput = ref("");
        const discountAmount = ref(0);
        const coupons = ref([]);
        const selectedItems = ref([]);
        const isCheckoutDisabled = computed(() => {
            return selectedItems.value.length === 0;
        });
        //取得localStorage內的購物車資料
        let localCartItems = JSON.parse(localStorage.getItem("localCartItems")) || [];


        // 全選狀態
        const allSelected = computed({
            get() {
                return selectedItems.value.length === items.value.length && items.value.length > 0;
            },
            set(value) {
                if (value) {
                    selectedItems.value = items.value.map(item => item);
                } else {
                    selectedItems.value = [];
                }
            }
        });

        // 選中商品的總金額計算
        const selectedSum = computed(() => {
            return selectedItems.value.reduce((acc, item) => acc + (item.price * item.proAmount), 0) || 0;
        });

        //刪除商品
        const deleteItem = index => {
            let array = items.value;
            let itemToDelete = array[index];
            let cartId = itemToDelete.cartId;

            // array 裡需至少有一項才執行，否則短路不繼續運算，不短路則 spice 掉陣列裡第 i 個 item
            array.length > 0 && array.splice(index, 1);
            array.length > 0 && selectedItems.value.splice(index, 1);

            console.log("item = ", toRaw(itemToDelete));
            console.log("cartId =", cartId);

            // 送出刪除購物車的api請求
            axios.delete(cartURL + cartId)
                .then(res => console.log(res))
                .catch(err => console.log(err));
        };

        /* 套用優惠券, 目前只判斷打折 */
        let couponSelected;

        //按下套用優惠券按鈕
        const discountHandler = () => {

            // 檢查輸入的 codeInput.value 跟 coupons 裡的 couponCode 是否符合
            const isCodeInCoupons = coupons.value.some(coupon => coupon.couponCode === codeInput.value);

            // TODO 加上沒勾選 不觸發以下邏輯
            if(isCodeInCoupons) {
                couponSelected= coupons.value.find(coupon => coupon.couponCode === codeInput.value);
                console.log(couponSelected);

                //判斷優惠類型
                //discType = 2, 計算折扣百分比
                if(couponSelected.discType === 2){
                    // 計算打折後金額
                    discountAmount.value = Math.round(selectedSum.value * (1 - couponSelected.discPercentage));
                    console.log(discountAmount.value);
                    isDiscount.value = !isDiscount.value;
                }
                //discType = 1, 計算現金抵用
                else if(couponSelected.discType === 1){
                    discountAmount.value = couponSelected.discAmount;
                    console.log(discountAmount.value);
                    isDiscount.value = !isDiscount.value;
                }
            }
             else {
                alert("優惠券不存在 :(");
            }
        };

        //掛載後才執行
        onMounted( async() => {
            try {
                //對後端發送get請求取得的回應
                let cartResponse = await axios.get(cartURL + memberId);
                console.log("memberId=" + memberId);

                let couponResponse = await axios.get(couponURL);

                //解構賦值: 將resp裡面的data, 重新給兩個key變數: cartPrdList, cartList
                let { cartPrdList, cartList } = cartResponse.data;

                coupons.value.push(...couponResponse.data);
                console.log("couPonResponse = " + JSON.stringify(couponResponse));
                // console.log("cartPrdList = " + JSON.stringify(cartPrdList));
                // console.log("cartList = " + JSON.stringify(cartList));

                // 把 cartPrdList 轉成 products所需格式
                products = cartPrdList.reduce(reduceToProducts, {});
                // (reduce:重新組裝成一個新的形式物件; 自定義重組方式->reduceToProducts)
                console.log("products = " + JSON.stringify(products));

                if(cartResponse.data !== null)
                    console.log("api串接成功, cartList資料為:" + JSON.stringify(cartResponse.data));

                items.value = cartList.map(cartView);
                console.log("=============================================");
                console.log(toRaw(items.value));

            } catch (error) {
                console.error("Error fetching data:", error);
            }
        });


        //按下去買單
        function checkOutHandler() {
            localStorage.clear();
            try{

                // 確保只選擇了被勾選的商品
                const selectedCartItems = selectedItems.value.map(item => ({
                    productId: item.productId,
                    productName: item.productName,
                    price: item.price,
                    quantity: item.proAmount,
                    sum: item.price * item.proAmount,
                    cartId: item.cartId
                }));

                // 計算總金額
                const totalAmount = selectedSum.value - discountAmount.value + 80;
                console.log("sum = " + selectedSum.value);
                console.log("discountAmount = " + discountAmount.value);
                console.log("totalAmount = " + totalAmount);

                // 要儲存的購物車資料
                const cartData = {
                    buyItemList: selectedCartItems,
                    totalAmount: totalAmount,
                    discount: discountAmount.value,
                    shipping: 80
                };

                // 將購物車存入localStorage
                localStorage.setItem('cartData', JSON.stringify(cartData));

                //將最新的localStorage資料發送至後端
                selectedItems.value.map( item => {
                    console.log("item = " + JSON.stringify(item));
                    console.log(cartURL + item.cartId)
                    const response = axios.put(cartURL + item.cartId, item.proAmount, {
                        headers: {
                            'Content-Type': 'application/json'
                        }
                    });
                    console.log(response);
                })
                console.log("購物車資料已存入localStorage並更新到後端");
                alert("即將跳轉到結帳頁面");

                // 跳轉到結帳頁面
                window.location.href = "/checkout";

            } catch (error) {
                console.error("更新購物車時出錯:", error);
                alert("更新購物車時出錯，請稍後再試");
            }
        }

        return {
            items,
            sum: selectedSum, //選中商品的總金額
            isDiscount,
            discountAmount,
            codeInput,
            deleteItem,
            discountHandler,
            checkOut: checkOutHandler,
            selectedItems,
            allSelected,
            memberId,
            isCheckoutDisabled
        }
    }
}).mount('#shopping-cart');


//自定義reduce方法
function reduceToProducts(acc, item) {
    let id = item.prdId;
    acc[item.prdId] = {
        name: item.prdName,
        picture: productURL + (item.prdId + 6000) + ".jpg",
        price: item.prdPrice,
        others: item
    };
    return acc;
}

//購物車畫面所需資訊
function cartView(item){
    let id = item.productId;
    item.productName = products[item.productId].name;
    item.price = products[item.productId].price;
    item.picture = products[item.productId].picture;
    item.sum = item.price * item.proAmount;
    item.url = "product/" + item.productId;
    return item;
}

/**
 CartViewObject:
 購物車項目 Id CartId、商品圖片、商品品項名、商品單價、商品數量、總計
 所屬會員 Id
 {
 response:{
 data: {
 productsInfos: [],
 cartList: [],
 coupons:[]
 }
 }
 }
 **/
