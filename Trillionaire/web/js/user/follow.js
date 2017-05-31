jQuery(function() {
    checkFollow();
});

angular.module("followApp", [])
    .controller("UserController", function ($scope) {
        $scope.inputUsername = "";
        $scope.email = window.sessionStorage.getItem("username");
        //关注
        $scope.follow = function () {
            if (checkFirst() != false) {
                follow_ajax($scope.email);
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
            this.username = username;
            this.code = getParam('code');
            $.ajax({
                type: "POST",
                url: "/user/follow",
                data: {"email": this.username, "code": this.code},
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
    });

var getParam = function (name) {
    var search = document.location.search;
    var pattern = new RegExp("[?&]" + name + "\=([^&]+)", "g");
    var matcher = pattern.exec(search);
    var items = null;
    if (null != matcher) {
        try {
            items = decodeURIComponent(decodeURIComponent(matcher[1]));
        } catch (e) {
            try {
                items = decodeURIComponent(matcher[1]);
            } catch (e) {
                items = matcher[1];
            }
        }
    }
    return items;
};

function checkFollow() {
    var email = window.sessionStorage.getItem('username');
    var code = getParam('code');
    var check =$.ajax({
        type: "POST",
        url: "/user/ifFollow",
        data: {
            "email": email,
            "code": code
        },
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            console.log('123');
            alert('已关注');
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                check.abort();
                checkFollow();
            }
        }
    })
}