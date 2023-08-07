function logoutRequest() {
    // 执行退出登录的AJAX请求，清除token等操作
    $.ajax({
        url: "/Page/loginRequest",
        type: "POST",
        success: function(response) {
            // 处理成功响应
            // 例如，重定向到登录页面或其他操作
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 处理错误响应
        }
    });
}

function updateStatusRequest() {
    // 执行修改用户状态的AJAX请求，将状态信息值修改为0
    $.ajax({
        url: "/Page/loginRequest",
        type: "POST",
        data: { status: 0 },
        success: function(response) {
            // 处理成功响应
            // 例如，显示成功消息或其他操作
        },
        error: function(jqXHR, textStatus, errorThrown) {
            // 处理错误响应
        }
    });
}