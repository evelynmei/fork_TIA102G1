document.addEventListener('DOMContentLoaded', function() {

    // 加入購物車按鈕
    const addToCartBtn = document.querySelector('.btn-cart button');

    // 按鈕事件監聽
    addToCartBtn.addEventListener('click', function(event) {
        event.preventDefault(); // 防止表單提交

        console.log('按鈕被點擊');
        console.log('商品 ID:', this.getAttribute('value'));
        console.log('會員 ID:', document.getElementById('memberId').value);

        // 取得商品數量
        const proAmount = parseInt(document.querySelector('input[type="number"]').value);

        // 取得productId
        const productId = this.getAttribute('value');

        // 取得memberId
        const memberId = document.getElementById('memberId').value;

        // 更新localStorage
        const updatedAmount = updateLocalStorage(productId, proAmount);

        // 準備發送到後端的數據
        const cartData = {
            cartId: null,
            memberId: parseInt(memberId),
            productId: productId,
            proAmount: updatedAmount,
            joinDt: null
        };

        // 發送 POST 請求至後端
        sendCartDataToServer(memberId, cartData);
    });
});

function updateLocalStorage(productId, proAmount) {

    // 取得localStorage內的購物車資料
    let localCartItems = JSON.parse(localStorage.getItem("localCartItems")) || [];

    // 查找是否已存在相同商品
    const existingItemIndex = localCartItems.findIndex(item => item.productId === productId);

    if (existingItemIndex >= 0) {
        // 如果商品已存在，增加數量
        localCartItems[existingItemIndex].proAmount += proAmount;
        proAmount = localCartItems[existingItemIndex].proAmount; // 更新為總數量
    } else {
        // 如果商品不存在，添加新項目
        localCartItems.push({ productId, proAmount });
    }
    // 存入localStorage
    localStorage.setItem("localCartItems", JSON.stringify(localCartItems));
    console.log('本地購物車更新：', localCartItems);

    return proAmount; // 返回更新後的數量
}


function sendCartDataToServer(memberId, cartData) {
    fetch(`/cart/${memberId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cartData)
    })
        .then(response => response.json())
        .then(data => {
            console.log('伺服器回應：', data);
            alert('商品已成功加入購物車！');
        })
        .catch((error) => {
            console.error('錯誤：', error);
            alert('加入購物車失敗，請重試');
        });
}