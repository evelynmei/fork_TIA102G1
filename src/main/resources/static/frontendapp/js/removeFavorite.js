async function removeFavorite(button) {
    const productId = button.getAttribute("data-product-id");

    button.disabled = true; // 禁用按鈕以避免重複提交

    try {
        // 發送刪除請求
        const response = await fetch(`/favProduct/remove?productId=${productId}`, {
            method: 'DELETE',
            headers: {
                'Content-Type': 'application/json',
            }
        });

        if (response.ok) {
            alert("已成功從最愛中移除");
            // 可選：從 DOM 中移除相應的行
            button.closest('tr').remove();
        } else {
            alert("移除最愛失敗");
        }
    } catch (error) {
        console.error("錯誤 in removeFavorite:", error);
        alert("發生錯誤，請稍後再試");
    } finally {
        button.disabled = false; // 請求完成後重新啟用按鈕
    }
}
