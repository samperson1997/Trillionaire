function dataAnalyze(data,board) {
    var array = data.up;//领涨的数组
    var finalList;
    if (array!=null){
        for (var i = 0; i < array.length; i++) {
            if (i==10){ //取前10名
                break;
            }
            //对数组遍历
            finalList = '<tr><td>'+(i+1)+'</td><td>'+array[i].code+'</td><td>'+array[i].name+'</td><td>'+array[i].margin+'</td></tr>';
            $("#up"+board+"-List").append(finalList);
        }
    }else {
        finalList = '无股票上涨';
        $("#up"+board+"-List").append(finalList);
    }
    array = data.down;//领跌的数组
    if (array!=null){
        for (var i = 0; i < array.length; i++) {
            if (i==10){  //取前10名
                break;
            }
            //对数组遍历
            finalList = '<tr><td>'+(i+1)+'</td><td>'+array[i].code+'</td><td>'+array[i].name+'</td><td>'+array[i].margin+'</td></tr>';
            $("#down"+board+"-List").append(finalList);
        }
    }else {
        finalList = '无股票下跌';
        $("#down"+board+"-List").append(finalList);
    }

}

function loadSS() {
    var load=$.ajax({
        type: "GET",
        url: "/market/board",
        data: {"board": "SS"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"SS");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadConcept();
            }
        }
    })
}

function loadSZ() {
    var load=$.ajax({
        type: "GET",
        url: "/market/board",
        data: {"board": "SZ"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"SZ");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadConcept();
            }
        }
    })
}

function loadGEM() {
    var load=$.ajax({
        type: "GET",
        url: "/market/board",
        data: {"board": "GEM"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"GEM");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadConcept();
            }
        }
    })
}

function loadSME() {
    var load=$.ajax({
        type: "GET",
        url: "/market/board",
        data: {"board": "SME"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"SME");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadConcept();
            }
        }
    })
}

function load() {
    loadSS();
    loadSZ();
    loadGEM();
    loadSME();
}