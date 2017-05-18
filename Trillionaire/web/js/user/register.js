window.onload = init;

function init() {
    tip = document.getElementById('reg-tip');
    tip.innerHTML = "";
}

function emailIsValid() {
    if (!/^[\w\.-_\+]+@[\w-]+(\.\w{2,4})+$/.test($("#reg-username").val())) {
        tip.innerHTML = "用户邮箱格式不正确";
    } else {
        tip.innerHTML = "";
    }
}

function passwordIsSame() {
    if ($("#reg-password").val() != $("#reg-password2").val()) {
        tip.innerHTML = "两次输入密码不一致";
    } else {
        tip.innerHTML = "";
    }
}

angular.module("mainapp", [])
    .controller("RegisterController", function ($scope) {
        $scope.inputUsername = "";
        $scope.inputPassword = "";
        $scope.inputPassword2 = "";

        function checkFirst() {
            if ($scope.inputUsername != null && $scope.inputUsername != "" &&
                $scope.inputPassword != null && $scope.inputPassword != "" &&
                $scope.inputPassword2 != null && $scope.inputPassword2 != "") {
                return true;
            } else {
                return false;
            }
        };
        //注册  
        $scope.register = function () {
            if (checkFirst() != false) {
                $scope.inputPassword = hex_md5($scope.inputPassword);
                $.ajax({
                    type: "POST",
                    url: "/user/register",
                    data: {
                        "username": $scope.inputUsername,
                        "password": $scope.inputPassword,
                        "tel": $scope.inputTel,
                        "email": $scope.inputEmail
                    },
                    contentType: "application/x-www-form-urlencoded",
                    dataType: "json",
                    success: function (data) {
                        console.log(data);
                        $scope.$apply(function () {
                            if (data.success == true && data.message == "注册成功") {
                                $scope.inputUsername = "";
                                $scope.inputPassword = "";
                                $scope.inputEmail = "";
                                $scope.inputTel = "";
                                tip.innerHTML = "欢迎,注册成功";
                                window.location.href = "../../login.html";
                            } else if (data.success == false && data.message == "该用户名已存在...") {
                                $scope.inputUsername = "";
                                $scope.inputPassword = "";
                                $scope.inputEmail = "";
                                $scope.inputTel = "";
                                tip.innerHTML = "该用户名已被注册";
                            }
                        });
                    }
                });
            } else {
                tip.innerHTML = "请把信息填写完整";
            };
        };
    })
