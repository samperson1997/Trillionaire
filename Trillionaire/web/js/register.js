angular.module("mainapp", [])
    .controller("UserController", function ($scope) {
        $scope.inputUsername = "";
        $scope.inputPassword = "";
        var tip = document.getElementById("reg-tip");
        tip.style.display = "none";

        function checkFirst() {
            if ($scope.inputUsername != null && $scope.inputUsername != "" &&
                $scope.inputPassword != null && $scope.inputPassword != "" && ) {
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
                    url: "/login/register",
                    data: {
                        "username": $scope.inputUsername,
                        "password": $scope.inputPassword,
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
                                window.location.href = "login.html";
                            } else if (data.success == false && data.message == "该用户名已存在...") {
                                $scope.inputUsername = "";
                                $scope.inputPassword = "";
                                $scope.inputEmail = "";
                                $scope.inputTel = "";
                                var tip = document.getElementById("reg-tip");
                                tip.innerHTML = "该账号已被注册,请重试!";
                                tip.style.display = "inline";
                            }
                        });
                    }
                });
            } else {
                var tip = document.getElementById("reg-tip");
                tip.innerHTML = "请将信息填写完整!";
                tip.style.display = "inline";
            };
        };
    })
