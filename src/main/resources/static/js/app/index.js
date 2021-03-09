var main= { //왜 이거 썼냐면 index로 scope를 한정시키기 위해 사용한다.중복되는거 있으면 브라우저의 scope는 공용이므로 난리가 난다.
    init : function (){
        var _this = this;
        $('#portfolio-save').on('click',function(){
            _this.portfolio_save();
        })
        $('#portfolio-update').on('click',function(){
            _this.portfolio_update();
        })
        $('#portfolio-delete').on('click',function(){
            _this.portfolio_delete();
        })
        $('#receipt-save').on('click',function(){
            _this.receipt_save();
        })
        $('#receipt-update').on('click',function(){
            _this.receipt_update();
        })
        $('#receipt-delete').on('click',function(){
            _this.receipt_delete();
        })
        $('#stock-search').on('click',function(){
            _this.stock_search();
        })
        $(document).on('click','#select-stock',function (){
            _this.select_stock();
        })
        $('#stockWord').on('keyup',function() {
            _this.stock_word();
        })
    },
    portfolio_save : function(){
        var data ={
            name : $('#name').val(),
            user_id : $('#user_id').val()
        };
        console.log(data.user_id);
        $.ajax({
            type: 'POST',
            url: '/api/v1/portfolio',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('포트폴리오가 등록되었습니다');
            window.location.href='/portfolio';
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    portfolio_update : function(){
        var data ={
            name: $('#name').val(),
            user_id : $('#user_id').val()
        };
        var id = $('#id').val();

        $.ajax({
            type:'PUT',
            url : '/ap1/v1/portfolio/'+id,
            dataType: 'json',
            contentType:'application/json; charset=utf-8',
            data : JSON.stringify(data)
        }).done(function(){
            alert('글이 수정되었습니다.');
            window.location.href ='/portfolio';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    portfolio_delete : function(){
        var id = $('#portfolio_id').val();
        $.ajax({
            type: 'DELETE',
            url: '/api/v1/portfolio/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/';
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    receipt_save : function(){
        var data ={
            stock_id : $('#stockId').val(),
            stockCnt : $('#stockCnt').val(),
            stockAvr : $('#stockAvr').val(),
            portfolio_id : $('#portfolio_id').val()
        };
        console.log(data);
        $.ajax({
            type: 'POST',
            url: '/api/v1/receipt',
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('자산이 등록되었습니다');
            window.location.href='/portfolio/detail/'+data.portfolio_id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    receipt_update : function(){
        var data ={
            stock_id : $('#stockId').val(),
            stockCnt : $('#stockCnt').val(),
            stockAvr : $('#stockAvr').val(),
            portfolio_id : $('#portfolio_id').val()
        };
        var id = $('#receiptId').val();
        $.ajax({
            type: 'PUT',
            url: '/api/v1/receipt/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8',
            data: JSON.stringify(data)
        }).done(function(){
            alert('자산이 수정되었습니다');
            window.location.href='/portfolio/detail/'+data.portfolio_id;
        }).fail(function(error){
            alert(JSON.stringify(error));
        })
    },
    receipt_delete : function(){
        var id = $('#receiptId').val();

        $.ajax({
            type: 'DELETE',
            url: '/api/v1/receipt/'+id,
            dataType:'json',
            contentType:'application/json; charset=utf-8'
        }).done(function(){
            alert('글이 삭제되었습니다');
            window.location.href='/portfolio/detail/'+$('#portfolio_id').val();
        }).fail(function(error){
            alert(JSON.stringify(error));
        });
    },
    stock_search : function (){
        var data ={
            name : $('#stockWord').val(),
            ticker : $('#stockWord').val()
        };
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/api/v1/stock/search',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
        }).done(function(){
            alert('자산이 등록되었습니다');
            window.location.href='/stock/search/result'
        }).fail(function(error){
            alert(JSON.stringify(error));
        })

    },
    select_stock : function(){
        // var stockName = $('th[name=stockNameAry]').eq('0').val();
        // var stockTicker = $('th[name=stockTickerAry]').eq('0').val();
        // var stockName =
        // console.log(stockName);
        // $("#stockName").val(stockName);
        // $("#stockTicker").val(stockTicker);
        console.log("클릭했다고 제발");
        var checkBtn = $(this);
        var tr = checkBtn.parent().parent();
        var td = tr.children();

        var stockTicker = td.eq(0).text();
        var stockName = td.eq(1).text();
        console.log("왜 선택이 안돼"+stockTicker);
        $("#stockName").val(stockName);
        $("#stockTicker").val(stockTicker);
    },
    stock_word : function () {
        var data = {
            name: $('#stockWord').val(),
            ticker: $('#stockWord').val()
        }
        var template = $("#template").html();
        //Mustache.parse(template);
        $.ajax({
            type: 'POST',
            dataType: 'json',
            url: '/api/v1/stock/search',
            dataType: 'json',
            contentType: 'application/json; charset=utf-8',
            data: JSON.stringify(data),
            error: function (err) {
                console.log("오류");
            },
            success: function (data) {
                //console.log(data);
                if(data.length>0) {
                    $("#stockList").empty();
                    $("#stockId").val(data[0].id);
                    $("#stockName").val(data[0].name);
                    $("#stockTicker").val(data[0].ticker);
                    for(i=0;i<data.length;i++) {
                        var stockName = data[i].name;
                        var stockTicker = data[i].ticker;
                        var stockPrice = data[i].price;
                        $("#stockList").append(
                            // "<li>"
                            // +"<input type = 'button' class = 'btn btn-primary' id = 'select_stock' name ='stockNameAry'"
                            // +" value="+stockName+">"
                            // +"<input type = 'hidden' class = 'btn btn-primary' id = 'select_stock' name ='stockTickerAry'"
                            // +" value="+stockTicker+">"
                            // +"</li>"
                            "<tr>"
                            +"<td>"+stockTicker+"</td>"
                            +"<td>"+stockName+"</td>"
                            +"<td>"+stockPrice+"</td>"
                            // +"<td><button type = \"button\" class = \"btn btn-primary\" id =\"receipt-save\">등록</button>" +
                            // "<button type = \"button\" class = \"btn btn-primary\" id =\"select-stock\">등록</button></td>"
                            +"</tr>"
                        );
                    }
                }
                //var rendered = Mustache.render(template,data);
                // var html =Mustache.to_html(template,data);
                //}
            }
        });
    }
};

main.init();