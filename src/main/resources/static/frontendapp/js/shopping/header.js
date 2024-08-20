let { createApp, ref, computed, onMounted, toRaw  } = Vue;

var memberId = document.getElementById("memberId").value;
const cartURL = "/api/cart/";
const productURL = "/images/product_pic/";

createApp({
    setup() {
        const carts = ref([{ proAmount: 0, proPrice: 0}]);

        //計算購物車旁數字
        const ttcount  = computed(()=>{
            return carts.value.reduce((acc,item) => acc + item.proAmount, 0);
        })
        const sum = computed(()=>{
            return carts.value.reduce((acc, item) => acc + (item.price * item.proAmount), 0);
        })
        const hideCartDropDown = ()=>{
            const element = $("#cart-dropdown").hide();
        };

        onMounted(async() => {
            try{

                let cartWrapperResp = await axios.get(cartURL + memberId);
                // console.log("memberId=" + memberId);
                // console.log("cartWrapperResp = " + cartWrapperResp.data);

                let { cartPrdList, cartList } = cartWrapperResp.data;
                // console.log("cartPrdList = " + JSON.stringify(cartPrdList));
                // console.log("cartList = " + JSON.stringify(cartList));
                carts.value = cartList;

                // 把 cartPrdList 轉成 cartPrd 所需格式
                cartPrd = cartPrdList.reduce(reduceToCartPrd, {});
                // console.log(cartPrd);

                carts.value = cartList.map(cartWrapperView);

                // console.log(toRaw(carts.value));

            } catch (error) {
                console.error("Error fetching data:", error);
            }
        });


        return {
            ttcount,
            carts,
            sum,
            hideCartDropDown
        }
    }
}).mount('#vue-header')

// window.onclick = clickListenerToCloseCartDropDown;

//自訂義reduce方法: 從cartPrd Entity取出需要的資料, 組成新的物件
function reduceToCartPrd(acc, item) {
    acc[item.prdId] = {

        prdName: item.prdName,
        prdPrice: item.prdPrice,
        picture: productURL + (item.prdId + 6000) + ".jpg"

    }
    return acc;
}

function cartWrapperView(item) {
    return {
        name: cartPrd[item.productId].prdName,
        price: cartPrd[item.productId].prdPrice,
        sum: item.proAmount * cartPrd[item.productId].prdPrice,
        picture : cartPrd[item.productId].picture,
        ...item
    }
}
//點cart-wrapper以外的地方切換關閉
// function clickListenerToCloseCartDropDown(event) {
//     const cartModal = document.getElementById('cart-dropdown');
//     if (event.target !== cartModal) {
//         $("#cart-dropdown").slideToggle("fast");
//         cartModal.setAttribute("style", "display: none");
//     }
// }
