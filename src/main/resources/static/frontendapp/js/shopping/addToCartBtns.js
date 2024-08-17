document.addEventListener('DOMContentLoaded', function() {
    // 獲取所有加入購物車按鈕
    const addToCartBtns = document.querySelectorAll('.btn-cart');

    console.log('找到的按鈕數量：', addToCartBtns.length);

    // 為每個按鈕添加點擊事件監聽器
    addToCartBtns.forEach(btn => {
        btn.addEventListener('click', function(event) {
            event.preventDefault();
            console.log('按鈕被點擊');

            const productId = this.getAttribute('value');
            const proAmount = 1;
            const memberId = document.getElementById('memberId').value;

            console.log('商品ID:', productId);
            console.log('會員ID:', memberId);

            // 準備發送到後端的數據
            const cartData = {
                cartId: null,
                memberId: parseInt(memberId),
                productId: productId,
                proAmount: proAmount,
                joinDt: null
            };

            // 發送 POST 請求至後端
            const apiUrl = `/api/cart/${memberId}`;
            fetch(apiUrl, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                },
                body: JSON.stringify(cartData)
            })
                .then(response => {
                    if (!response.ok) {
                        throw new Error(`HTTP error! status: ${response.status}`);
                    }
                    return response.json();
                })
                .then(data => {
                    console.log('伺服器回應：', data);
                    alert('商品已成功加入購物車！');
                })
                .catch((error) => {
                    console.error('錯誤：', error);
                    alert('加入購物車失敗，請重試。錯誤：' + error.message);
                });
        });
    });
});