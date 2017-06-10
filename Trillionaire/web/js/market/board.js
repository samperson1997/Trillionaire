function boardDataAnalyze(data,board) {
    var array = data;// 版块数组
    var finalList;
    $("#"+board+"-spin").html(''); //撤销加载动画
    if (array!=null){
        for (var i = 0; i < array.length; i++) {
            if (i==10){ //取前10名
                break;
            }
            //对数组遍历
            finalList = '<tr><td>'+(i+1)+'</td><td>'+array[i].code+'</td><td>'+array[i].name+'</td><td>'+array[i].changepercent+'</td></tr>';
            $("#up"+board+"-List").append(finalList);
        }
        for (var i = array.length-1; i >=0 ; i--) {
            if ((array.length-i)==11){  //取前10名
                break;
            }
            //对数组遍历
            finalList = '<tr><td>'+(array.length-i)+'</td><td>'+array[i].code+'</td><td>'+array[i].name+'</td><td>'+array[i].changepercent+'</td></tr>';
            $("#down"+board+"-List").append(finalList);
        }
    }else {
        finalList = '无股票上涨';
        $("#up"+board+"-List").append(finalList);
        finalList = '无股票下跌';
        $("#down"+board+"-List").append(finalList);
    }
}

function loadRank() {
    var load=$.ajax({
        type: "GET",
        url: "/market/board",
        timeout:180000,
        contentType: "application/x-www-form-urlencoded",
        dataType: "json",
        success: function (data) {
            boardDataAnalyze(data.ss,"SS");
            boardDataAnalyze(data.sz,"SZ");
            boardDataAnalyze(data.gem,"GEM");
            boardDataAnalyze(data.sme,"SME");
            console.log(data);
        },
        error: function (request, status, err) {
            if (status=="timeout"){
                load.abort();
                loadRank();
            }
        }
    })
}