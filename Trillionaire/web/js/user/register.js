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
    .controller("UserController", function ($scope) {
        $scope.inputEmail = "";
        $scope.inputPassword = "";
        $scope.inputPassword2 = "";

        //注册
        $scope.register = function () {
            if (checkFirst() != false) {
                register_ajax($scope.inputEmail, $scope.inputPassword);
            } else {
                tip.innerHTML = "请把信息填写完整";
            }
        };

        function checkFirst() {
            if ($scope.inputEmail != null && $scope.inputEmail != "" &&
                $scope.inputPassword != null && $scope.inputPassword != "" &&
                $scope.inputPassword2 != null && $scope.inputPassword2 != "") {
                return true;
            } else {
                return false;
            }
        };

        $scope.check = function () {
            $.ajax({
                type: "GET",
                url: "/user/check",
                data: {
                    "email": $scope.inputEmail
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "text",
                success: function (data) {
                    if (data == "exist") {
                        tip.innerHTML = "该用户名已被注册";
                    } else if (data == "none") {

                    }
                }
            })
        }

        //注册
        function register_ajax(email, password) {
            this.email = email;
            this.password = password;
            $.ajax({
                type: "POST",
                url: "/user/register",
                data: {
                    "email": this.email,
                    "password": this.password,
                },
                contentType: "application/x-www-form-urlencoded",
                dataType: "json",
                success: function (data) {
                    console.log(data);
                    $scope.$apply(function () {
                        if (data == "success") {
                            $scope.inputEmail = "";
                            $scope.inputPassword = "";
                            tip.innerHTML = "欢迎,注册成功,请查收您的收件箱";
                            window.location.href = "../../login.html";
                        } else if (data == "fail") {
                            $scope.inputEmail = "";
                            $scope.inputPassword = "";
                            tip.innerHTML = "该用户名已被注册";
                        }
                    });
                }
            });
        }
    });
