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

        // 準備發送到後端的數據
        const cartData = {
            cartId: null,
            memberId: parseInt(memberId),
            productId: productId,
            proAmount: proAmount,
            joinDt: null
        };

        // 發送 POST 請求至後端
        sendCartDataToServer(memberId, cartData);
    });
});

// 發送 POST 請求至後端
function sendCartDataToServer(memberId, cartData) {
    fetch(`/api/cart/${memberId}`, {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify(cartData)
    })
        .then(response => response.json())
        .then(data => {
            console.log("會員:" + memberId);
            console.log('伺服器回應：', data);
            alert('商品已成功加入購物車！');
        })
        .catch((error) => {
            console.error('錯誤：', error);
            alert('加入購物車失敗，請重試');
        });
}