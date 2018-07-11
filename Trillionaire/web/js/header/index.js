var HEADER_OBJECT = {};

(function () {

    var menuItemList = document.querySelector("#top-bar ul")
    if (menuItemList == null) {
        return
    } else {
        var islogin = sessionStorage.getItem("log_state") === "true"
        if (islogin) {
            showLogoutButton()
        } else {
            showLoginButton()
        }
    }

    var pathName = document.location.pathname
    if (pathName.startsWith('/stock')) {
        menuItemList.querySelectorAll("li")[0].classList.add('item-active')
    } else if (pathName.startsWith('/concern')) {
        menuItemList.querySelectorAll("li")[1].classList.add('item-active')
    } else if (pathName.startsWith('/strategy')) {
        menuItemList.querySelectorAll("li")[2].classList.add('item-active')
    }

    function showLoginButton() {
        var aElement = document.createElement("a")
        aElement.setAttribute("href", "login.html")
        var ulElement = document.createElement("li")
        ulElement.setAttribute("id", "log-button")
        ulElement.setAttribute("class", "log-button")
        ulElement.appendChild(document.createTextNode("登录 | 注册"))
        aElement.appendChild(ulElement)

        menuItemList.removeChild(menuItemList.querySelectorAll("a")[3]);
        menuItemList.appendChild(aElement)
    }

    function showLogoutButton() {
        // var aElement = document.createElement("a")
        // var divElement = document.createElement("div")
        // aElement.appendChild(divElement)
        // divElement.style.position = "relative"
        // divElement.style.display = "inline-block"
        // var ulElement = document.createElement("li")
        // ulElement.setAttribute("id", "log-button")
        // ulElement.setAttribute("class", "log-button")
        // var username = sessionStorage.getItem("username")
        // ulElement.appendChild(document.createTextNode(username))
        // divElement.appendChild(ulElement)
        //
        // var logoutButton = document.createElement("div")
        // logoutButton.appendChild(document.createTextNode("注销"))
        // logoutButton.addEventListener("click", _simpleLogoutHandler)
        // logoutButton.setAttribute("class", 'logout-extend')
        // divElement.appendChild(logoutButton)
        // divElement.addEventListener("mouseover", function(){
        //     logoutButton.style.visibility = "visible"
        // })
        // divElement.addEventListener("mouseleave", function(){
        //     logoutButton.style.visibility = "hidden"
        // })
        //
        // menuItemList.removeChild(menuItemList.querySelectorAll("a")[3]);
        // menuItemList.appendChild(aElement)

        $("#log-button").text("注销");
        document.getElementById("log-button").addEventListener("click", _simpleLogoutHandler);
    }

    function _simpleLogoutHandler(e) {
        sessionStorage.removeItem("log_state")
        sessionStorage.removeItem("userId")
        sessionStorage.removeItem("username")
        // showLoginButton()
        $("#log-button").text("登录 | 注册");
    }


    HEADER_OBJECT.showLogoutButton = showLogoutButton
})()