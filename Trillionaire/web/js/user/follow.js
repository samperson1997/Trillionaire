angular.module("followApp", [])
    .controller("UserController", function ($scope) {
        $scope.inputUsername = "";
        $scope.inputPassword = "";
        $scope.email = "";
        $scope.code = "";
        //关注
        $scope.follow = function () {
            if (checkFirst() != false) {
                follow_ajax($scope.email, $scope.code);
            } else {
            }
            ;
        };
        function checkFirst() {
            return true;
        }

        /*
         if($scope.inputUsername!=null && $scope.inputUsername!=""
         && $scope.inputPassword!=null && $scope.inputPassword!=""){
         return true;
         }else{
         return false;
         }
         };*/
        function follow_ajax(username, code) {
            this.username = "";
            this.code = code;
            $.ajax({
                type: "POST",
                url: "/user/follow",
                data: {"username": this.username, "code": this.code},
                contentType: "application/x-www-form-urlencoded",
                success: function (data, status, XMLHttpRequest) {
                    console.log(data);
                    console.log(XMLHttpRequest);
                    console.log(status);
                    var sessionstatus = XMLHttpRequest.getResponseHeader("sessionstatus");
                    if (sessionstatus === "logout") {
                        //如果未登陆就跳转
                       window.location.href="../../login.html";
                    }
                }
            });
        }
    })
