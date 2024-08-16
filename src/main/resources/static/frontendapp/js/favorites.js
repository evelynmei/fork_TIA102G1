async function toggleFavorite(button) {

    const productId = button.getAttribute("data-product-id");

    button.disabled = true; // 禁用按鈕以避免重複提交

    try {
        // 檢查當前是否已加入最愛
        const isFavoriteResponse = await fetch(`/favProduct/isFavorite?productId=${productId}`);
        const isFavorite = await isFavoriteResponse.json(); // 假設返回的是布林值

        let response;
        if (isFavorite) {
            // 如果已經在最愛中，則移除
            response = await fetch(`/favProduct/remove?productId=${productId}`, {
                method: 'DELETE',
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            if (response.ok) {
                alert("已從最愛移除");
                button.textContent = "加入最愛"; // 更新按鈕文本
            } else {
                alert("移除最愛失敗");
            }
        } else {
            // 如果不在最愛中，則添加
            response = await fetch(`/favProduct/add?productId=${productId}`, {
                method: 'POST',
                headers: {
                    'Content-Type': 'application/json',
                }
            });

            if (response.ok) {
                alert("已成功加入最愛");
                button.textContent = "移除最愛"; // 更新按鈕文本
            } else {
                alert("加入最愛失敗");
            }
        }
    } catch (error) {
        console.error("錯誤 in toggleFavorite:", error);
        alert("發生錯誤，請稍後再試");
    } finally {
        button.disabled = false; // 請求完成後重新啟用按鈕
    }
}
