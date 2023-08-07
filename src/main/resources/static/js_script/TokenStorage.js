// 从set-cookie中获取令牌
function getTokenFromSetCookie(setCookieHeader) {
    const cookies = setCookieHeader.split(';');
    for (let i = 0; i < cookies.length; i++) {
        var cookie = cookies[i].trim();
        if (cookie.indexOf("token=") === 0) {
            return cookie.substring("token=".length, cookie.length);
        }
    }
    return null;
}

// 从本地存储中移除令牌
function removeTokenFromLocalStorage() {
    localStorage.removeItem('token');
}
// 假设在退出登录时，你可以调用 removeTokenFromLocalStorage 函数来从本地存储中移除令牌
removeTokenFromLocalStorage();
