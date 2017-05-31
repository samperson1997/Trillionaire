window.onload = init;

function init() {
    tip = document.getElementById('log-tip');
    tip.innerHTML = "";
}

function emailIsValid() {
    if (!/^[\w\.-_\+]+@[\w-]+(\.\w{2,4})+$/.test($("#log-username").val())) {
        tip.innerHTML = "用户邮箱格式不正确";
    } else {
        tip.innerHTML = "";
    }
}

angular.module("mainapp", [])
    .controller("UserController", function ($scope) {
        $scope.inputUsername = "";
        $scope.inputPassword = "";
        //登录
        $scope.login = function () {
            if (checkFirst() != false) {
                tip.innerHTML = "";
                login_ajax($scope.inputUsername, $scope.inputPassword);
            } else {
                tip.innerHTML = "请把信息填写完整";
            };
        };

        function checkFirst() {
            if ($scope.inputUsername != null && $scope.inputUsername != "" &&
                $scope.inputPassword != null && $scope.inputPassword != "") {
                return true;
            } else {
                return false;
            }
        };

        function login_ajax(username, password) {
            this.username = username;
            this.password = password;
            $.ajax({
                type: "POST",
                url: "/user/login",
                data: {
                    "username": this.username,
                    "password": this.password
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                    console.log(data);
                    $scope.$apply(function () {
                        var str = document.referrer;
                        if (data == "success") {
                            window.sessionStorage.setItem("username", username);
                            if (str == null || str == "") {
                                window.location.href = "/index.html";
                            } else {
                                window.location.href = str;
                            }

                            sessionStorage.setItem("log_state", "true");
                        }
                        if (data == "fail") {
                            $scope.inputPassword = "";
                            tip.innerHTML = "用户名不存在或密码错误";
                        }
                    });
                }
            });
        };
    })
