function dataAnalyze(data,board) {
    var array = data.up;//领涨的数组
    var finalList;
    $("#"+board+"-spin").html(''); //撤销加载动画
    if (array!=null){
        for (var i = 0; i < array.length; i++) {
            //对数组遍历
            finalList = '<li style="text-align: center"><div class="rank-top"><div class="rank-head">'+array[i].name+'</div><div class="rank-margin">+'+array[i].margin+'</div><div class="rank-sta"><i class="fa fa-arrow-up"></i>'+array[i].up+'&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>'+array[i].remain+'&nbsp;&nbsp;<i class="fa fa-arrow-down"></i>'+array[i].down+'</div> </div> <div class="rank-bottom"><div class="rank-stock">'+array[i].stock+'</div><div class="rank-stock-margin">+'+array[i].stockMargin+'</div></div></li>';
            $("#up"+board+"-List").append(finalList);
        }
    }else {
        finalList = '无股票上涨';
        $("#up"+board+"-List").append(finalList);
    }
    array = data.down;//获取领跌的数组
    if (array!=null){
        for (var i = 0; i < array.length; i++) {
            //对数组遍历
            finalList = '<li style="text-align: center"><div class="rank-top"><div class="rank-head">'+array[i].name+'</div><div class="rank-margin">+'+array[i].margin+'</div><div class="rank-sta"><i class="fa fa-arrow-up"></i>'+array[i].up+'&nbsp;&nbsp;<i class="fa fa-arrow-right"></i>'+array[i].remain+'&nbsp;&nbsp;<i class="fa fa-arrow-down"></i>'+array[i].down+'</div> </div> <div class="rank-bottom"><div class="rank-stock">'+array[i].stock+'</div><div class="rank-stock-margin">+'+array[i].stockMargin+'</div></div></li>';
            $("#down"+board+"-List").append(finalList);
        }
    }else {
        finalList = '无股票下跌';
        $("#down"+board+"-List").append(finalList);
    }

}
function loadIndustry() {
    var load=$.ajax({
        type: "GET",
        url: "/market/category",
        data: {"category": "industry"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"Industry");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadIndustry();
            }
        }
    })
}
function loadArea() {
    var load =$.ajax({
        type: "GET",
        url: "/market/category",
        data: {"category": "area"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"Area");
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadArea();
            }
        }
    })
}
function loadConcept() {
    var load=$.ajax({
        type: "GET",
        url: "/market/category",
        data: {"category": "concept"},
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            dataAnalyze(data,"Concept");
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
    loadIndustry();
    loadArea();
    loadConcept();
}