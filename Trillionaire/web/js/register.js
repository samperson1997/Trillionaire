angular.module("mainapp",[])  
.controller("RegisterController",function($scope){
    $scope.inputUsername = "";  
    $scope.inputPassword = "";  
    $scope.inputEmail = "";  
    $scope.inputTel = "";  
    function checkFirst(){  
        if($scope.inputUsername!=null && $scope.inputUsername!=""  
            && $scope.inputPassword!=null && $scope.inputPassword!=""  
            && $scope.inputEmail!=null && $scope.inputEmail!=""  
            && $scope.inputTel!=null && $scope.inputTel!=""){  
            return true;  
    }else{  
        return false;  
    }  
};  
        //注册  
        $scope.register = function(){  
            if(checkFirst() != false){  
                $scope.inputPassword = hex_md5($scope.inputPassword);  
                $.ajax({  
                    type:"POST",
                    url:"/user/register",
                    data:{"username":$scope.inputUsername,"password":$scope.inputPassword,"tel":$scope.inputTel,"email":$scope.inputEmail},  
                    contentType:"application/x-www-form-urlencoded",  
                    dataType:"json",  
                    success:function(data){  
                        console.log(data);  
                        $scope.$apply(function(){  
                            if(data.success == true && data.message == "注册成功"){  
                                $scope.inputUsername = "";$scope.inputPassword = "";  
                                $scope.inputEmail = "";$scope.inputTel = "";  
                                alert("注册成功!");  
                                window.location.href = "login.html";  
                            }else if(data.success == false && data.message == "该用户名已存在..."){  
                                $scope.inputUsername = "";$scope.inputPassword = "";  
                                $scope.inputEmail = "";$scope.inputTel = "";  
                                alert("该用户名已被注册...");  
                            }  
                        });  
                    }  
                });  
            }else{  
                alert("请将信息填写完整...");  
            };  
        };  
    })  