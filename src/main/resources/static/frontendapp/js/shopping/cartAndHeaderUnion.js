let { createApp, ref, computed, onMounted, toRaw  } = Vue;

// var memberId = document.getElementById("memberId").value;
const memberId = 1;

const cartURL = "/api/cart/";
const productURL = "/images/product_pic/";
const couponURL = "/api/coupon/";

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
const mockShoppingCart = [
    {cartId:1, picture: productURL + "images/product_pic/7001", productName: "假資料原味餅乾", price: 100, proAmount: 1,},
    {cartId:2, picture: productURL + "images/product_pic/7002", productName: "假資料牛奶糖餅乾", price: 200, proAmount: 2,}
];
let products = mockProducts;
const cartViewData = ref(mockShoppingCart);

// const cartViewForDropDown = ref([{ proAmount: 0, proPrice: 0}]);
const coupons = [];

let cartPrdList, cartList, cartPrd;

fetchCartAPI().then(
    cartData => {
        cartPrdList = cartData.cartPrdList;
        cartList = cartData.cartList;
        // console.log(cartPrdList);
        // console.log(cartList);

        products = cartPrdList.reduce(reduceToProducts, {})
        cartViewData.value = cartList.map(cartView);


        // console.log("cartViewForDropDown : ", toRaw(cartViewForDropDown.value));
        console.log("Items : ", toRaw(cartViewData.value));
    }
)
fetchCouponAPI().then(
    couponData => coupons.push(...couponData)
)

//計算購物車旁數字
const ttcount  = computed(()=>{
    return cartViewData.value.reduce((acc,item) => acc + item.proAmount, 0);
})
const allShoppingCartSum = computed(()=>{
    return cartViewData.value.reduce((acc, item) => acc + (item.price * item.proAmount), 0);
})

const headerComponent = createApp({
    setup() {
        const hideCartDropDown = ()=>{
            const element = $("#cart-dropdown").hide();
        };

        return {
            ttcount,
            carts: cartViewData,
            sum: allShoppingCartSum,
            hideCartDropDown
        }
    }
}).mount('#vue-header')

const shoppingCartComponent = createApp({
    setup() {
        const isDiscount = ref(false);
        const codeInput = ref("");
        const discountAmount = ref(0);
        const selectedItems = ref([]);
        const isCheckoutDisabled = computed(() => {
            return selectedItems.value.length === 0;
        });
        //取得localStorage內的購物車資料
        let localCartItems = JSON.parse(localStorage.getItem("localCartItems")) || [];


        // 全選狀態
        const allSelected = computed({
            get() {
                return selectedItems.value.length === cartViewData.value.length && cartViewData.value.length > 0;
            },
            set(value) {
                if (value) {
                    selectedItems.value = cartViewData.value.map(item => item);
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
            let array = cartViewData.value;
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
            const isCodeInCoupons = coupons.some(coupon => coupon.couponCode === codeInput.value);

            // TODO 加上沒勾選 不觸發以下邏輯
            if(isCodeInCoupons) {
                couponSelected= coupons.find(coupon => coupon.couponCode === codeInput.value);
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
            items: cartViewData,
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


function fetchCartAPI(){
    return  fetchAPI( cartURL + memberId);
}
function fetchCouponAPI(){
    return fetchAPI(couponURL);
}

async function fetchAPI(url){
    const response = await axios.get(url);
    return response.data;
}

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