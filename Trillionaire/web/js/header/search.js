
(function(){
    // var searchArea = $('search-area')
    // var searchInput = $('search-input')
    var searchArea = document.querySelector('#search-area')
    var searchInput = document.querySelector('#search-input')

    if(!(searchArea && searchInput)) {
        return
    }

    searchInput.addEventListener("input", debounce(pullAssociatedStock, 800));

    function debounce(fn, wait) {
        var timer = null;
        return function () {
            var context = this
            var args = arguments
            if (timer) {
                clearTimeout(timer);
                timer = null;
            }
            timer = setTimeout(function () {
                fn.apply(context, args)
            }, wait)
        }
    }

    function pullAssociatedStock(event) {
        console.log('ajax.js')
        $.ajax({
            type: "GET",
            url: "/stock/associate",
            contentType: "application/x-www-form-urlencoded",
            data: {
                "input": event.target.value
            },
            dataType: "json",
            success: function (data) {
                createAssociatedBoard(data.slice(0, 10))
            },
            error: function (request, status, err) {
                console.log(err)
            }
        })
    }

    function createAssociatedBoard(data) {
        var boardElement = document.createElement('div')
        boardElement.setAttribute('class', 'associate-board')
        for (let stockInfo of data) {
            var itemElement = document.createElement('div')
            itemElement.appendChild(document.createTextNode(stockInfo.name + ' ' + stockInfo.code))
            itemElement.setAttribute('class', 'associate-item')
            itemElement.setAttribute('data-code', stockInfo.code)
            boardElement.appendChild(itemElement)
        }

        boardElement.addEventListener('click', function(e){
            var targetItem = e.target
            window.location.href = "stock.html?code=" + targetItem.dataset.code;
        })

        if (searchArea.querySelector('.associate-board')) {
            searchArea.replaceChild(boardElement, searchArea.querySelector('.associate-board'))
        } else {
            searchArea.appendChild(boardElement)
        }

    }

    document.body.addEventListener('click', function (e) {
        var board = searchArea.querySelector('.associate-board')
        if (board) {
            board.style.visibility = 'hidden'
        }
    })

})()